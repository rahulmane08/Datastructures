package datastructures.array;

import static datastructures.array.ArrayUtils.ArrangementUtils.rearrageAlternateMaxAndMin;
import static datastructures.array.ArrayUtils.ArrangementUtils.rearrageAlternatePositiveAndNegative;

import java.util.Arrays;

public class ArrangementTest {
  public static void main(String[] args) {
    System.out.println("=====Arranging max and min alternatively====");
    int[] arr = {1, 2, 3, 4, 5, 6};
    int[] copy = Arrays.copyOf(arr, arr.length);
    //ArrayArrangementUtils.rearrageAlternateMaxAndMin(copy);
    System.out.println(Arrays.toString(arr) + ": after min max rearrangement: " + Arrays.toString(copy));
    arr = new int[] {1, 2, 3, 4, 5, 6, 7};
    copy = Arrays.copyOf(arr, arr.length);
    rearrageAlternateMaxAndMin(copy);
    System.out.println(Arrays.toString(arr) + ": after min max rearrangement: " + Arrays.toString(copy));

    System.out.println("=====Arranging -ve and +ve alternatively preserving order====");
    arr = new int[] {1, 2, 3, -4, -1, 4};
    copy = Arrays.copyOf(arr, arr.length);
    rearrageAlternatePositiveAndNegative(copy);
    System.out.printf("%s after rearrangement: %s%n", Arrays.toString(arr), Arrays.toString(copy));
    arr = new int[] {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
    copy = Arrays.copyOf(arr, arr.length);
    rearrageAlternatePositiveAndNegative(copy);
    System.out.printf("%s after rearrangement: %s%n", Arrays.toString(arr), Arrays.toString(copy));
  }
}
