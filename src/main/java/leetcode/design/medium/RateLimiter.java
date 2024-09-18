package leetcode.design.medium;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
class Window {
  private int count;
  private long startTime;

  public Window(int count) {
    this.count = count;
    this.startTime = System.currentTimeMillis();
  }

  public void decrementCount() {
    this.count--;
  }
}

public class RateLimiter {

  private Map<String, Queue<Window>> rateLimiterConfig = new HashMap<>();

  private static final int MAX_REQUESTS_PER_WINDOW = 10;

  private static final long MAX_EXPIRY_PER_WINDOW = 20 * 1_000;

  public boolean limit(String clientId) {
    Queue<Window> windows =
        rateLimiterConfig.compute(clientId, (c, window) -> window == null ? new LinkedList<>() : window);
    while (!windows.isEmpty() && isInvalid(windows.peek())) {
      windows.poll();
    }
    if (!windows.isEmpty()) {
      Window currentWindow = windows.peek();
      if (currentWindow.getCount() == 0) {
        return false;
      }
      currentWindow.decrementCount();
    } else {
      System.out.println("");
      windows.offer(new Window(MAX_REQUESTS_PER_WINDOW - 1));
    }
    return true;
  }

  public boolean limitWithCreditCarryForward(String clientId) {
    Queue<Window> windows =
        rateLimiterConfig.compute(clientId, (c, window) -> window == null ? new LinkedList<>() : window);
    int carryForwardCredits = 0;
    while (!windows.isEmpty() && isInvalid(windows.peek())) {
      carryForwardCredits += windows.poll().getCount();
    }
    System.out.println("carryForwardCredits : " + carryForwardCredits);
    if (!windows.isEmpty()) {
      Window currentWindow = windows.peek();
      currentWindow.setCount(carryForwardCredits + currentWindow.getCount());

      if (carryForwardCredits + currentWindow.getCount() == 0) {
        System.out.println("currentWindow limit breached : " + currentWindow);
        return false;
      }
      System.out.println("currentWindow : " + currentWindow);
      currentWindow.decrementCount();
    } else {
      Window newWindow = new Window(carryForwardCredits + MAX_REQUESTS_PER_WINDOW - 1);
      System.out.println("Created newWindow : " + newWindow);
      windows.offer(newWindow);
    }
    return true;
  }

  private boolean isInvalid(Window window) {
    return System.currentTimeMillis() - MAX_EXPIRY_PER_WINDOW > window.getStartTime();
  }

  public static void main(String[] args) throws InterruptedException {
    final RateLimiter limiter = new RateLimiter();
    Thread test = new Thread(() -> {
      try {
        for (int i = 0; i < 5; i++) {
          String clientId = "rahul";
          Thread.sleep(1_000);
          System.out.println(
              clientId + " : " + new Date() + ", allowed: " + limiter.limitWithCreditCarryForward(clientId));
        }

        Thread.sleep(20_000);
        System.out.println("===============================================================================");
        for (int i = 0; i < 25; i++) {
          String clientId = "rahul";
          Thread.sleep(1_000);
          System.out.println(
              clientId + " : " + new Date() + ", allowed: " + limiter.limitWithCreditCarryForward(clientId));
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    test.run();
    test.join();
  }
}
