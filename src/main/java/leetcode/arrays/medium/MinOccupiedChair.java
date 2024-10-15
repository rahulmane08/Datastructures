package leetcode.arrays.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinOccupiedChair {

  class Friend {
    int friendNumber;
    int arrivalTime;
    int leavingTime;

    public Friend(int friendNumber, int arrivalTime, int leavingTime) {
      this.friendNumber = friendNumber;
      this.arrivalTime = arrivalTime;
      this.leavingTime = leavingTime;
    }

    public int getFriendNumber() {
      return friendNumber;
    }

    public int getArrivalTime() {
      return arrivalTime;
    }

    public int getLeavingTime() {
      return leavingTime;
    }
  }

  class Chair {
    int number;
    int occupiedTill;

    public Chair(int number, int occupiedTill) {
      this.number = number;
      this.occupiedTill = occupiedTill;
    }

    public int getNumber() {
      return number;
    }

    public int getOccupiedTill() {
      return occupiedTill;
    }
  }

  /**
   * Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
   * Output: 1
   * Explanation:
   * - Friend 0 arrives at time 1 and sits on chair 0.
   * - Friend 1 arrives at time 2 and sits on chair 1.
   * - Friend 1 leaves at time 3 and chair 1 becomes empty.
   * - Friend 0 leaves at time 4 and chair 0 becomes empty.
   * - Friend 2 arrives at time 4 and sits on chair 0.
   * Since friend 1 sat on chair 1, we return 1.
   *
   * @param times
   * @param targetFriend
   * @return
   */
  public int smallestChair(int[][] times, int targetFriend) {
    // sort the friends by arrival times.
    PriorityQueue<Friend> friends = new PriorityQueue<>(Comparator.comparingInt(Friend::getArrivalTime));

    for (int i = 0; i < times.length; i++) {
      friends.offer(new Friend(i, times[i][0], times[i][1]));
    }

    // This map stores the occupied chairs sorted as per the free times of chair and then by chairNumber asc.
    PriorityQueue<Chair> currentChairs =
        new PriorityQueue<>(Comparator.comparingInt(Chair::getOccupiedTill).thenComparing(Chair::getNumber));

    int chairNumber = 0;
    Chair targetChair = null;
    while (!friends.isEmpty()) {
      Friend friend = friends.poll();
      Chair chair;
      if (!currentChairs.isEmpty() && currentChairs.peek().occupiedTill <= friend.arrivalTime) {
        // check if any of the existing occupied chairs are getting free, if yes allocate that chair to current friend.
        chair = currentChairs.poll();
        chair.occupiedTill = friend.leavingTime; // update the occupiedTill
      } else {
        chair = new Chair(chairNumber++, friend.leavingTime); // assign a new chair
      }
      if (friend.friendNumber == targetFriend) {
        targetChair = chair;
        break;
      }
    }
    return targetChair.number;
  }

  /**
   * {{3, 10}, {1, 5}, {2, 6}}
   * friends : {1, 1, 5} {2, 2, 6} {0, 3, 10}
   *
   * friend 1 : chairs : {}, new chair, chairs : {0, 5}
   * friend 2 : new chair, chairs : {0, 5} {1, 6}
   * friend 0 : new chair, chairs : {0, 5} {1, 6} {2, 10}
   *
   *  {{3, 10}, {1, 3}, {2, 6}}
   *    * friends : {1, 1, 3} {2, 2, 6} {0, 3, 10}
   *    *
   *    * friend 1 : chairs : {}, new chair, chairs : {0, 3}
   *    * friend 2 : new chair, chairs : {0, 3} {1, 6}
   *    * friend 0 : chair0, chairs :  {1, 6} {0, 10}
   * @param args
   */
  public static void main(String[] args) {
    MinOccupiedChair util = new MinOccupiedChair();
    System.out.println(util.smallestChair(new int[][] {{1, 4}, {2, 3}, {4, 6}}, 1));
    System.out.println(util.smallestChair(new int[][] {{3, 10}, {1, 5}, {2, 6}}, 0));
  }
}
