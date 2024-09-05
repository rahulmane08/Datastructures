package design.lld;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

enum ButtonType {
  EMERGENCY, UP, DOWN, NUMERIC
}

interface ButtonCommand {
  void execute();

  void undo();
}

interface ElevatorAllocationStrategy {
  void allocateElevator(int floor);
}

@Data
class Button {
  private final ButtonType type;
  private final String value;
  protected boolean pressed;

  public Button(ButtonType type) {
    this.type = type;
    this.value = "";
  }

  public Button(ButtonType type, String value) {
    this.type = type;
    this.value = value;
  }

  protected void press() {
    pressed = true;
  }

  protected void unpress() {
    pressed = false;
  }
}

@Data
abstract class Panel {
  public abstract void display(String message);
}

@Data
class FloorUpDownPanel extends Panel {
  private final Button upButton;
  private final Button downButton;

  public FloorUpDownPanel() {
    this.upButton = new Button(ButtonType.UP);
    this.downButton = new Button(ButtonType.DOWN);
  }

  public void pressUp() {
    this.upButton.press();
  }

  public void pressDown() {
    this.downButton.press();
  }

  @Override
  public void display(String message) {
    System.out.println("up lift requested");
  }
}

@Data
class FloorUpPanel extends FloorUpDownPanel {
  @Override
  public void pressDown() {
    throw new RuntimeException("operation not supported");
  }
}

@Data
class FloorDownPanel extends FloorUpDownPanel {
  @Override
  public void pressUp() {
    throw new RuntimeException("operation not supported");
  }
}

@Data
class ElevatorPanel extends Panel {
  private final Button[] buttons;
  private final Button emergencyButton;

  public ElevatorPanel(int floors) {
    buttons = new Button[floors];
    for (int i = 0; i < buttons.length; i++) {
      buttons[i] = new Button(ButtonType.NUMERIC, String.valueOf(i));
    }
    this.emergencyButton = new Button(ButtonType.EMERGENCY);
  }

  @Override
  public void display(String message) {

  }
}

@Data
class ElevatorDoor {
  private boolean opened;

  public void open() {
    opened = true;
  }

  public void close() {
    opened = false;
  }
}

@Data
class Floor {
  private final int number;
  private final FloorUpDownPanel panel;

  public Floor(int number, FloorUpDownPanel panel) {
    this.number = number;
    this.panel = panel;
  }
}

@Data
class Zone {
  private final String name;
  private final Map<Integer, Floor> floors;

  public Zone(String name, Set<Floor> floors) {
    this.name = name;
    this.floors = floors.stream().collect(Collectors.toMap(Floor::getNumber, Function.identity()));
  }
}

@Data
class ElevatorCar {
  private final UUID id = UUID.randomUUID();
  private final ElevatorPanel panel;
  private final int load;
  private final ElevatorDoor door;

  private int currentFloor;
  private Zone zone;
  private boolean moving;
  private boolean underMaintenance;

  public ElevatorCar(ElevatorPanel panel, int load) {
    this.panel = panel;
    this.load = load;
    this.door = new ElevatorDoor();
  }

  public void moveTo(int floor) {
    if (zone != null && !zone.getFloors().containsValue(floor)) {
      return;
    }
    this.moving = true;
    // logic to move the lift
    this.currentFloor = floor;
  }

  public void openDoor() {
    this.door.open();
  }

  public void closeDoor() {
    this.door.open();
  }
}

@Data
@Builder
class ElevatorConfiguration {
  private int totalFloors;
  private int totalElevators;
  private int load;
  private Zone zone;
}

class FcfsElevatorAllocationStrategy implements ElevatorAllocationStrategy {

  @Override
  public void allocateElevator(int floor) {

  }
}

class ElevatorDispatcher {
  private final UUID id = UUID.randomUUID();
  private final Map<Integer, Floor> floors;
  private final Map<UUID, ElevatorCar> elevators;
  private ElevatorAllocationStrategy strategy;

  public ElevatorDispatcher(ElevatorConfiguration elevatorConfiguration) {
    this.floors = new HashMap<>();
    for (int i = 1; i < elevatorConfiguration.getTotalFloors() - 1; i++) {
      this.floors.put(i, new Floor(i, new FloorUpDownPanel()));
    }
    this.elevators = new HashMap<>();
    for (int i = 0; i < elevatorConfiguration.getTotalElevators(); i++) {
      ElevatorCar elevatorCar = new ElevatorCar(
          new ElevatorPanel(elevatorConfiguration.getTotalFloors()),
          elevatorConfiguration.getLoad());
      this.elevators.put(elevatorCar.getId(), elevatorCar);
    }

  }

}

public class ElevatorSystem {
}
