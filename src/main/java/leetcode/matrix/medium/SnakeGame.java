package leetcode.matrix.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SnakeGame {
  private final int width, height;

  private final int[][] food;

  private final int[][] grid;

  private final Map<String, int[]> directions = new HashMap<>();

  private int snakeLength;
  private int foodIndex;
  private final int[] head;
  private final int[] tail;
  private boolean alive;

  public SnakeGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    this.grid = new int[height][width];
    this.food = food;
    directions.put("R", new int[] {0, 1});
    directions.put("L", new int[] {0, -1});
    directions.put("D", new int[] {1, 0});
    directions.put("U", new int[] {-1, 0});
    // place the snake starting position.
    snakeLength = 1;
    grid[0][0] = 2;
    head = new int[2];
    tail = new int[2];
    alive = true;
    // place the first food.
    placeFood();
  }

  public int move(String direction) {
    if (!alive) {
      return -1;
    }
    int[] moves = directions.get(direction);
    int newX = head[0] + moves[0];
    int newY = head[1] + moves[1];
    if (!isValidHeadMove(newX, newY)) {
      alive = false;
      return -1;
    }

    if (hasFood(newX, newY)) {
      eatFood(newX, newY);
      placeFood();
    } else {
      moveSnake(newX, newY);
    }
    return -1;
  }

  private void moveSnake(int x, int y) {
    if (snakeLength == 1) {
      moveHead(x, y);
      moveTail(x, y);
    } else {
      int[] move = new int[2];
      for (int[] direction : directions.values()) {
        int newX = tail[0] + direction[0];
        int newY = tail[1] + direction[1];
        if (isValidTailMove(newX, newY)) {
          move = new int[] {newX, newY};
        }
      }
      moveTail(move[0], move[1]);
      moveHead(x, y);
    }
  }

  private void moveHead(int x, int y) {
    head[0] = x;
    head[1] = y;
    grid[x][y] = 2;
  }

  private void moveTail(int x, int y) {
    grid[tail[0]][tail[1]] = 0;
    tail[0] = x;
    tail[1] = y;
  }

  private void eatFood(int x, int y) {
    grid[x][y] = 2;
    head[0] = x;
    head[1] = y;
    snakeLength++;
  }

  private boolean hasFood(int x, int y) {
    return grid[x][y] == 1;
  }

  private void placeFood() {
    if (foodIndex < food.length && isValid(food[foodIndex][0], food[foodIndex][1])) {
      grid[food[foodIndex][0]][food[foodIndex][1]] = 1;
      foodIndex++;
    }
  }

  private boolean isValid(int newX, int newY) {
    return 0 <= newX && newX < height && 0 <= newY && newY < width && grid[newX][newY] < 2;
  }

  private boolean isValidHeadMove(int newX, int newY) {
    return isValid(newX, newY) && grid[newX][newY] < 2;
  }

  private boolean isValidTailMove(int newX, int newY) {
    return isValid(newX, newY) && grid[newX][newY] == 2;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < height; i++) {
      str.append(String.format("%s,%n", Arrays.toString(grid[i])));
    }
    return str.toString();
  }

  public static void main(String[] args) {
    SnakeGame snakeGame = new SnakeGame(3, 3, new int[][] {{1, 2}, {0, 1}});
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("R"));
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("D"));
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("R"));
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("U"));
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("L"));
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("L"));
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("L"));
    System.out.println(snakeGame);
    System.out.println(snakeGame.move("D"));
  }
}
