package array;

import static array.ArrayUtils.*;

public class Test {
    public static void main(String[] args) {
		/*Arrays.printNonRepeatingElement(new int[]{7,4,5,6,5,6,4});
		Arrays.printXOR(new int[]{2, 1, 5, 9});
		*/
        // System.out.println(array.Arrays.concatenateArrayToLargestNumber(new Integer[]{709999, 708888, 9}));
        // subsetWhoseSumMatchesN(new int[] {3, 34, 4, 12, 5, 2, 11}, 9);

        System.out.println("=====ARRAY ROTATION====");
        int [] arr = {1,2,3,4,5,6,7, 8};
        rotate(arr, 2);
        System.out.println("rotate by 2: "+ java.util.Arrays.toString(arr));
        rotate(arr, 12);
        System.out.println("rotate by 12: "+ java.util.Arrays.toString(arr));

        System.out.println("=====ARRAY ROTATION GCD METHOD====");
        arr = new int[]{1,2,3,4,5,6,7, 8};
        rotateEfficiently(arr, 2);
        System.out.println("rotate by 2: "+ java.util.Arrays.toString(arr));
        rotateEfficiently(arr, 12);
        System.out.println("rotate by 12: "+ java.util.Arrays.toString(arr));
    }
}
