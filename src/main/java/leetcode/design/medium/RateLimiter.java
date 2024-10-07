package leetcode.design.medium;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import lombok.Data;
import lombok.ToString;

interface RateLimitingPolicy {
  boolean limit(String clientId);
}

class FixedWindowRateLimitingPolicy implements RateLimitingPolicy {

  // Each clientId has a queue containing window objects.
  private Map<String, Queue<FixedWindowRateLimitingPolicy.Window>> rateLimiterConfig = new HashMap<>();

  private static final int MAX_REQUESTS_PER_WINDOW = 10;

  private static final long MAX_EXPIRY_PER_WINDOW = 20 * 1_000;

  private final boolean useCredits;

  public FixedWindowRateLimitingPolicy() {
    this(false);
  }

  public FixedWindowRateLimitingPolicy(boolean useCredits) {
    this.useCredits = useCredits;
  }

  public boolean limit(String clientId) {
    if (useCredits) {
      return limitWithCreditCarryForward(clientId);
    }
    return limitWithoutCredits(clientId);
  }

  private boolean limitWithoutCredits(String clientId) {
    Queue<Window> windows =
        rateLimiterConfig.compute(clientId, (c, window) -> window == null ? new LinkedList<>() : window);

    // remove all the expired windows from front of the queue.
    while (!windows.isEmpty() && isExpired(windows.peek())) {
      windows.poll();
    }

    // valid window will be at the front of the queue.
    if (!windows.isEmpty()) {
      Window currentWindow = windows.peek();
      // check if the count is 0, then dis-allow.
      if (currentWindow.getCount() == 0) {
        return false;
      }
      // allow and decrement the count.
      currentWindow.decrementCount();
    } else {
      // no current window, hence create a new window.
      windows.offer(new Window(MAX_REQUESTS_PER_WINDOW - 1));
    }
    return true;
  }

  public boolean limitWithCreditCarryForward(String clientId) {
    Queue<FixedWindowRateLimitingPolicy.Window> windows =
        rateLimiterConfig.compute(clientId, (c, window) -> window == null ? new LinkedList<>() : window);
    int carryForwardCredits = 0;
    while (!windows.isEmpty() && isExpired(windows.peek())) {
      // gather the ununsed counts from previous windows as credits.
      carryForwardCredits += windows.poll().getCount();
    }

    if (!windows.isEmpty()) {
      FixedWindowRateLimitingPolicy.Window currentWindow = windows.peek();
      // add the credits to current window.
      currentWindow.setCount(carryForwardCredits + currentWindow.getCount());

      if (currentWindow.getCount() == 0) {
        System.out.println("currentWindow limit breached : " + currentWindow);
        return false;
      }
      System.out.println("currentWindow : " + currentWindow);
      currentWindow.decrementCount();
    } else {
      FixedWindowRateLimitingPolicy.Window
          newWindow = new FixedWindowRateLimitingPolicy.Window(carryForwardCredits + MAX_REQUESTS_PER_WINDOW - 1);
      System.out.println("Created newWindow : " + newWindow);
      windows.offer(newWindow);
    }
    return true;
  }

  private boolean isExpired(FixedWindowRateLimitingPolicy.Window window) {
    return System.currentTimeMillis() - MAX_EXPIRY_PER_WINDOW > window.getStartTime();
  }


  @Data
  @ToString
  static
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
}

class SlidingWindowRateLimitingPolicy implements RateLimitingPolicy {

  private Map<String, Queue<Long>> rateLimiterConfig = new HashMap<>();

  private static final int MAX_REQUESTS_PER_WINDOW = 10;

  private static final long MAX_EXPIRY_PER_WINDOW = 20 * 1_000;

  @Override
  public boolean limit(String clientId) {
    Queue<Long> windows =
        rateLimiterConfig.compute(clientId, (c, window) -> window == null ? new LinkedList<>() : window);
    while (!windows.isEmpty() && isInvalid(windows.peek())) {
      windows.poll();
    }
    if (windows.size() == MAX_REQUESTS_PER_WINDOW) {
      return false;
    }
    windows.offer(System.currentTimeMillis());
    return true;
  }

  private boolean isInvalid(long token) {
    return System.currentTimeMillis() - MAX_EXPIRY_PER_WINDOW > token;
  }
}

public class RateLimiter {

  private final RateLimitingPolicy policy;

  public RateLimiter(RateLimitingPolicy policy) {
    this.policy = policy;
  }

  public boolean limit(String clientId) {
    return policy.limit(clientId);
  }

  public static void main(String[] args) throws InterruptedException {
    final RateLimiter limiter = new RateLimiter(new FixedWindowRateLimitingPolicy());
    Thread test = new Thread(() -> {
      try {
        for (int i = 0; i < 5; i++) {
          String clientId = "rahul";
          Thread.sleep(1_000);
          System.out.println(
              clientId + " : " + new Date() + ", allowed: " + limiter.limit(clientId));
        }

        Thread.sleep(20_000);
        System.out.println("===============================================================================");
        for (int i = 0; i < 25; i++) {
          String clientId = "rahul";
          Thread.sleep(1_000);
          System.out.println(
              clientId + " : " + new Date() + ", allowed: " + limiter.limit(clientId));
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    test.run();
    test.join();
  }
}
