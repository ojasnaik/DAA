package DAAAssignments;

import java.util.Collections;
import java.util.PriorityQueue;

public class NaikHercules {

    public static int maxHeads(int[] birds, int m) {
        int totalHeads = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int bird : birds) {
            maxHeap.add(bird);
        }
        while (m > 0) {
            int max = maxHeap.remove();
            totalHeads += max;
            maxHeap.add(max / 2);
            m--;
        }
        return totalHeads;
    }

    public static void main(String args[]) {
        int arr1[] = {20, 1, 15};
        int arr2[] = {2, 1, 7, 4, 2};
        int arr3[] = {4, 10, 6, 7, 3, 1};

        System.out.println("Test 1, Total Heads 20 = " + maxHeads(arr1, 1)); //m =1, bird 1 cut 20 heads, arr1 is now {10,1,15}
        System.out.println("Test 2, Total Heads 35 = " + maxHeads(arr1, 2)); //m=2, bird 3 cut 15 head, arr1 is now {10,1,7}
        System.out.println("Test 3, Total Heads 45 = " + maxHeads(arr1, 3)); //m=3, bird 1 cut 10 heads, arr1 is now {5,1, 7}

        System.out.println("Test 4, Total Heads 7 = " + maxHeads(arr2, 1)); //m=1
        System.out.println("Test 5, Total Heads 11 = " + maxHeads(arr2, 2)); //m=2
        System.out.println("Test 6, Total Heads 14 = " + maxHeads(arr2, 3)); //m=3

        System.out.println("Test 7, Total Heads 10 = " + maxHeads(arr3, 1)); //m=1
        System.out.println("Test 8, Total Heads 17 = " + maxHeads(arr3, 2)); //m=2
        System.out.println("Test 9, Total Heads 23 = " + maxHeads(arr3, 3)); //m=3
        System.out.println("Test 10, Total Heads 28 = " + maxHeads(arr3, 4));//m=4
        System.out.println("Test 11, Total Heads 32 = " + maxHeads(arr3, 5));//m=5
        System.out.println("Test 12, Total Heads 35 = " + maxHeads(arr3, 6));//m=6
    }
}

