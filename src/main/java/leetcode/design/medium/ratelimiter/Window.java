package leetcode.design.medium.ratelimiter;

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

  public  boolean isExpired(long maxExpiryPerWindow) {
    return System.currentTimeMillis() - maxExpiryPerWindow > this.startTime;
  }
}
