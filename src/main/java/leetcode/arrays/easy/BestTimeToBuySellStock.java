package leetcode.arrays.easy;

public class BestTimeToBuySellStock {
  public static void main(String[] args) {
    int[] prices = new int[] {7, 2, 5, 1, 7, 4};
    BestTimeToBuySellStock util = new BestTimeToBuySellStock();
    System.out.println(util.maxProfit(prices));
    prices = new int[] {7, 5, 4, 3, 2};
    System.out.println(util.maxProfit(prices));
    prices = new int[] {7, 7, 7, 7, 7};
    System.out.println(util.maxProfit(prices));
    prices = new int[] {7, 7, 6, 7, 7};
    System.out.println(util.maxProfit(prices));
  }

  public int maxProfit(int[] prices) {
    int maxProfit = 0;
    int buy = 0;
    int sell = 1;
    while (sell < prices.length) {
      if (prices[buy] >= prices[sell]) {
        buy = sell;
      } else {
        maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);
      }
      sell++;
    }
    return maxProfit;
  }
}
