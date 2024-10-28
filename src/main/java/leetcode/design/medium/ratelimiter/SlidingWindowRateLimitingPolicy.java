package leetcode.design.medium.ratelimiter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class SlidingWindowRateLimitingPolicy implements RateLimitingPolicy {

  private static final int MAX_REQUESTS_PER_WINDOW = 10;
  private static final long MAX_EXPIRY_PER_WINDOW = 20 * 1_000;
  private final Map<String, Queue<Long>> rateLimiterConfig = new HashMap<>();

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
