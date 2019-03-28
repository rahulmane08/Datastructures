package array;

import static array.ArrayArrangementUtils.moveAllZeroesAtEnd;
import static array.ArrayArrangementUtils.sortAsPerIndexedArray;
import static array.ArrayUtils.alternateMinMax;
import static array.ArrayUtils.concatenateArrayToLargestNumber1;
import static array.ArrayUtils.doubleValidNumberAndReplaceWith0AtTheEnd;
import static array.ArrayUtils.findMajorityElement1;
import static array.ArrayUtils.findMaxJMinusI;
import static array.ArrayUtils.findMinDistBetweenTwoElements;
import static array.ArrayUtils.largestContiguousSubArray;
import static array.ArrayUtils.leftRotate;
import static array.ArrayUtils.leftRotateByGCD;
import static array.ArrayUtils.leftRotateByReversal;
import static array.ArrayUtils.minSwapsToGetElementsLessThanOrEqualToKTogether;
import static array.ArrayUtils.printRepeatingElementsBetween0AndN;
import static array.ArrayUtils.reverse;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
		/*Arrays.printNonRepeatingElement(new int[]{7,4,5,6,5,6,4});
		Arrays.printXOR(new int[]{2, 1, 5, 9});
		*/
        // System.out.println(array.Arrays.concatenateArrayToLargestNumber(new Integer[]{709999, 708888, 9}));
        // subsetWhoseSumMatchesN(new int[] {3, 34, 4, 12, 5, 2, 11}, 9);

        System.out.println("=====RIGHT ARRAY ROTATION====");
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        leftRotate(arr, 2);
        System.out.println("rotate by 2: " + java.util.Arrays.toString(arr));
        leftRotate(arr, 12);
        System.out.println("rotate by 12: " + java.util.Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        leftRotate(arr, 3);
        System.out.println("rotate by 3: " + java.util.Arrays.toString(arr));

        System.out.println("=====RIGHT ARRAY ROTATION GCD METHOD====");
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        ArrayUtils.leftRotateByGCD(arr, 2);
        System.out.println("rotate by 2: " + java.util.Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        ArrayUtils.leftRotateByGCD(arr, 12);
        System.out.println("rotate by 12: " + java.util.Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrayUtils.leftRotateByGCD(arr, 3);
        System.out.println("rotate by 3: " + java.util.Arrays.toString(arr));

        System.out.println("=====SUB ARRAY LEFT ROTATION GCD METHOD====");
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        leftRotateByGCD(arr, 2, 2, 7);
        System.out.println("sub array (2,7) rotate by 2: " + java.util.Arrays.toString(arr));

        System.out.println("=====LEFT ARRAY ROTATION REVERSAL METHOD====");
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        ArrayUtils.leftRotateByReversal(arr, 2);
        System.out.println("rotate by 2: " + java.util.Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        ArrayUtils.leftRotateByReversal(arr, 12);
        System.out.println("rotate by 12: " + java.util.Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrayUtils.leftRotateByReversal(arr, 3);
        System.out.println("rotate by 3: " + java.util.Arrays.toString(arr));

        System.out.println("=====SUB ARRAY LEFT ROTATION REVERSAL METHOD====");
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        leftRotateByReversal(arr, 2, 2, 7);
        System.out.println("sub array (2,7) rotate by 2: " + java.util.Arrays.toString(arr));

        System.out.println("=====RIGHT ARRAY ROTATION REVERSAL METHOD====");
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        ArrayUtils.rightRotateByReversal(arr, 2);
        System.out.println("rotate by 2: " + java.util.Arrays.toString(arr));
        System.out.println("=====SUB ARRAY RIGHT ROTATION REVERSAL METHOD====");
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        ArrayUtils.rightRotateByReversal(arr, 2, 2, 7);
        System.out.println("sub array (2,7) rotate by 2: " + java.util.Arrays.toString(arr));

        System.out.println("=====REVERSE ARRAY====");
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        reverse(arr);
        System.out.println("reverse array: " + java.util.Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        reverse(arr);
        System.out.println("reverse array: " + java.util.Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        reverse(arr, 2, 6);
        System.out.println("reverse array (2,6): " + java.util.Arrays.toString(arr));

        System.out.println("=====LARGEST CONTIGUOUS SUBARRAY====");
        largestContiguousSubArray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        largestContiguousSubArray(new int[]{-2, -3, -4, -1, -2, 1, 5, -3});
        largestContiguousSubArray(new int[]{-2, -3});
        largestContiguousSubArray(new int[]{11, -2, -3, -10});
        largestContiguousSubArray(new int[]{-2, 11, -3, -10});
        largestContiguousSubArray(new int[]{-2, -1});
        largestContiguousSubArray(new int[]{-2, -1, 1, 2, 3, 10});
        largestContiguousSubArray(new int[]{10, 7, 8, -2, -1, 1, 2});
        largestContiguousSubArray(new int[]{10, 7, 8, -2, -1, 2});

        try {
            System.out.println("=====FIND MAJORITY ELEMENT====");
            arr = new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4};
            System.out.print("Majority element of array: " + Arrays.toString(arr) + "= ");
            System.out.println(findMajorityElement1(Arrays.copyOf(arr, arr.length)));
            arr = new int[]{3, 3, 4, 2, 4, 4, 2, 4};
            System.out.print("Majority element of array: " + Arrays.toString(arr) + "= ");
            System.out.println(findMajorityElement1(Arrays.copyOf(arr, arr.length)));

            arr = new int[]{3, 3, 3, 2, 4, 4, 2, 4};
            System.out.print("Majority element of array: " + Arrays.toString(arr) + "= ");
            System.out.println(findMajorityElement1(Arrays.copyOf(arr, arr.length)));

            System.out.println("=====FIND REPEATING ELEMENTS BETWEEN 0, N====");
            arr = new int[]{1, 2, 3, 1};
            printRepeatingElementsBetween0AndN(arr);

            arr = new int[]{1, 3, 3, 1};
            printRepeatingElementsBetween0AndN(arr);

            arr = new int[]{1, 2, 3, 1, 3, 6, 6};
            printRepeatingElementsBetween0AndN(arr);

            System.out.println("=====MOVE ALL 0'S TO END====");
            arr = new int[]{1, 2, 0, 4, 3, 0, 5, 0};
            int[] copy = Arrays.copyOf(arr, arr.length);
            moveAllZeroesAtEnd(copy);
            System.out.printf("%s, after moving all 0's to end:%s%n", Arrays.toString(arr), Arrays.toString(copy));
            arr = new int[]{1, 2, 0, 0, 0, 3, 6};
            copy = Arrays.copyOf(arr, arr.length);
            moveAllZeroesAtEnd(copy);
            System.out.printf("%s, after moving all 0's to end:%s%n", Arrays.toString(arr), Arrays.toString(copy));

            System.out.println("=====Concatenate to largest number====");
            Integer[] arr1 = new Integer[]{0, 9, 42};
            System.out.println("largest number = " + concatenateArrayToLargestNumber1(arr1));
            arr1 = new Integer[]{10, 9, 42};
            System.out.println("largest number = " + concatenateArrayToLargestNumber1(arr1));
            arr1 = new Integer[]{8010, 9, 809};
            System.out.println("largest number = " + concatenateArrayToLargestNumber1(arr1));
            arr1 = new Integer[]{54, 546, 548, 60};
            System.out.println("largest number = " + concatenateArrayToLargestNumber1(arr1));
            arr1 = new Integer[]{1, 34, 3, 98, 9, 76, 45, 4};
            System.out.println("largest number = " + concatenateArrayToLargestNumber1(arr1));

            System.out.println("=====Bring elements <=K together====");
            int k = 5;
            arr = new int[]{2, 7, 9, 5, 8, 7, 4};
            copy = Arrays.copyOf(arr, arr.length);
            System.out.printf("Total swaps in %s, to bring elements <= %d together = %d %n",
                    Arrays.toString(arr), k, minSwapsToGetElementsLessThanOrEqualToKTogether(copy, k));
            k = 3;
            arr = new int[]{2, 1, 5, 6, 3};
            copy = Arrays.copyOf(arr, arr.length);
            System.out.printf("Total swaps in %s, to bring elements <= %d together = %d %n",
                    Arrays.toString(arr), k, minSwapsToGetElementsLessThanOrEqualToKTogether(copy, k));

            System.out.println("=====Alternate min max====");
            arr = new int[]{2, 7, 9, 5, 8, 7, 4};
            copy = Arrays.copyOf(arr, arr.length);
            alternateMinMax(copy);
            System.out.printf("Array: %s, after alternate min max sort: %s%n",
                    Arrays.toString(arr), Arrays.toString(copy));

            System.out.println("=====Double valid element and add 0 at end====");
            arr = new int[]{2, 2, 0, 4, 0, 8};
            copy = Arrays.copyOf(arr, arr.length);
            doubleValidNumberAndReplaceWith0AtTheEnd(copy);
            System.out.printf("%s after op: %s%n ",
                    Arrays.toString(arr), Arrays.toString(copy));
            arr = new int[]{0, 2, 2, 2, 0, 6, 6, 0, 0, 8};
            copy = Arrays.copyOf(arr, arr.length);
            doubleValidNumberAndReplaceWith0AtTheEnd(copy);
            System.out.printf("%s after op: %s%n ",
                    Arrays.toString(arr), Arrays.toString(copy));

            System.out.println("=====Sort based on indexed array====");
            arr = new int[]{50, 40, 70, 60, 90};
            int[] indexes = new int[]{3, 0, 4, 1, 2};
            sortAsPerIndexedArray(arr, indexes);
            System.out.printf("After indexed sort- arr: %s, indexes: %s%n",
                    Arrays.toString(arr), Arrays.toString(indexes));


            System.out.println("=====Find max(j-i) such than arr[j] > arr[i]===");
            //findMaxJMinusI(new int[] {34, 34, -10, 3, 2, 80, 30, 33, 1});
            findMaxJMinusI(new int[]{34, 35, 10, 3, 2, 80, 30, 33, 1});
            findMaxJMinusI(new int[]{5, 4, 3, 2, 1});
            findMaxJMinusI(new int[]{1, 2, 3, 4, 5});

            System.out.println("=====Find i=arr[i] in sorted array===");
            arr = new int[] {1,2,2,4};
            System.out.printf("For array:%s, Index:%d%n",
                    Arrays.toString(arr), new ArrayUtils.FindIndexEqualsElementInSortedArrayUtil().find(arr));
            arr = new int[] {1,1,2,3};
            System.out.printf("For array:%s, Index:%d%n",
                    Arrays.toString(arr), new ArrayUtils.FindIndexEqualsElementInSortedArrayUtil().find(arr));
            arr = new int[] {11,12,13,14};
            System.out.printf("For array:%s, Index:%d%n",
                    Arrays.toString(arr), new ArrayUtils.FindIndexEqualsElementInSortedArrayUtil().find(arr));
            arr = new int[] {1,2,3,4,5,6,7,7,9,20};
            System.out.printf("For array:%s, Index:%d%n",
                    Arrays.toString(arr), new ArrayUtils.FindIndexEqualsElementInSortedArrayUtil().find(arr));
            arr = new int[] {1,2,3,4,5,6,7,7,9,20, 21};
            System.out.printf("For array:%s, Index:%d%n",
                    Arrays.toString(arr), new ArrayUtils.FindIndexEqualsElementInSortedArrayUtil().find(arr));

            System.out.println("=====Find MinDist(x,y)===");
            arr = new int[]{1, 2};
            int x = 2;
            int y = 1;
            System.out.printf("For array:%s, MinDist(%d, %d) = %d%n",
                    Arrays.toString(arr), x, y, findMinDistBetweenTwoElements(arr, x, y));
            arr = new int[]{3, 4, 5};
            x = 3;
            y = 5;
            System.out.printf("For array:%s, MinDist(%d, %d) = %d%n",
                    Arrays.toString(arr), x, y, findMinDistBetweenTwoElements(arr, x, y));
            arr = new int[]{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
            x = 3;
            y = 6;
            System.out.printf("For array:%s, MinDist(%d, %d) = %d%n",
                    Arrays.toString(arr), x, y, findMinDistBetweenTwoElements(arr, x, y));
            arr = new int[]{2, 5, 3, 5, 4, 4, 2, 3};
            x = 3;
            y = 2;
            System.out.printf("For array:%s, MinDist(%d, %d) = %d%n",
                    Arrays.toString(arr), x, y, findMinDistBetweenTwoElements(arr, x, y));
            x = 5;
            y = 2;
            System.out.printf("For array:%s, MinDist(%d, %d) = %d%n",
                    Arrays.toString(arr), x, y, findMinDistBetweenTwoElements(arr, x, y));



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
