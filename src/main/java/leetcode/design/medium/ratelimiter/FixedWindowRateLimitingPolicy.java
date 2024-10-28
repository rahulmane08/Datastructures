package leetcode.design.medium.ratelimiter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class FixedWindowRateLimitingPolicy implements RateLimitingPolicy {

  private static final int MAX_REQUESTS_PER_WINDOW = 10;
  private static final long MAX_EXPIRY_PER_WINDOW = 20 * 1_000;
  private final boolean useCredits;
  // Each clientId has a queue containing window objects.
  private final Map<String, Queue<Window>> rateLimiterConfig = new HashMap<>();

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
    while (!windows.isEmpty() && windows.peek().isExpired(MAX_EXPIRY_PER_WINDOW)) {
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
    Queue<Window> windows =
        rateLimiterConfig.compute(clientId, (c, window) -> window == null ? new LinkedList<>() : window);
    int carryForwardCredits = 0;
    while (!windows.isEmpty() && windows.peek().isExpired(MAX_EXPIRY_PER_WINDOW)) {
      // gather the ununsed counts from previous windows as credits.
      carryForwardCredits += windows.poll().getCount();
    }

    if (!windows.isEmpty()) {
      Window currentWindow = windows.peek();
      // add the credits to current window.
      currentWindow.setCount(carryForwardCredits + currentWindow.getCount());

      if (currentWindow.getCount() == 0) {
        System.out.println("currentWindow limit breached : " + currentWindow);
        return false;
      }
      System.out.println("currentWindow : " + currentWindow);
      currentWindow.decrementCount();
    } else {
      Window
          newWindow = new Window(carryForwardCredits + MAX_REQUESTS_PER_WINDOW - 1);
      System.out.println("Created newWindow : " + newWindow);
      windows.offer(newWindow);
    }
    return true;
  }
}
