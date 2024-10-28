package leetcode.design.medium.ratelimiter;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import lombok.Data;
import lombok.ToString;

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
