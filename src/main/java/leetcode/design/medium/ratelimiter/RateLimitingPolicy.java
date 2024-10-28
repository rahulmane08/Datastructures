package leetcode.design.medium.ratelimiter;

interface RateLimitingPolicy {
  boolean limit(String clientId);
}
