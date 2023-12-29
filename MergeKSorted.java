package DAAAssignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeKSorted {
    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {2, 4, 6};
        int[] array3 = {0, 8, 9};

        List<int[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        // int[] result = kWayMergeIterative(arrays);
        int[] result = kWayMergeDivideAndConquer(arrays, 0, arrays.size()-1);
        System.out.println("Merged array: " + Arrays.toString(result));
    }


    public static int[] kWayMergeIterative(List<int[]> input) {
        int k = input.size();
        if (k == 0){
            return new int[0];
        }
        int[] currentArray = input.get(0);

        for (int i = 1; i < k; i++) {
            int[] nextArray = input.get(i);
            int[] temp = new int[currentArray.length + nextArray.length];
            int p1 = 0, p2 = 0, p = 0;

            while (p1 < currentArray.length && p2 < nextArray.length) {
                if (currentArray[p1] < nextArray[p2]) {
                    temp[p++] = currentArray[p1++];
                } else {
                    temp[p++] = nextArray[p2++];
                }
            }

            while (p1 < currentArray.length) temp[p++] = currentArray[p1++];
            while (p2 < nextArray.length) temp[p++] = nextArray[p2++];

            currentArray = temp;
        }

        return currentArray;
    }

    public static int[] kWayMergeDivideAndConquer(List<int[]> arrays, int start, int end) {
        if (start == end) return arrays.get(start);
        if (start + 1 == end) return merge(arrays.get(start), arrays.get(end));

        int mid = (start + end) / 2;
        int[] left = kWayMergeDivideAndConquer(arrays, start, mid);
        int[] right = kWayMergeDivideAndConquer(arrays, mid + 1, end);

        return merge(left, right);
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int p1 = 0, p2 = 0, p = 0;

        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] < arr2[p2]) {
                result[p++] = arr1[p1++];
            } else {
                result[p++] = arr2[p2++];
            }
        }

        while (p1 < arr1.length) result[p++] = arr1[p1++];
        while (p2 < arr2.length) result[p++] = arr2[p2++];

        return result;
    }

}
