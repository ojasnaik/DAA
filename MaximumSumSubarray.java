package DAAAssignments;

public class MaximumSumSubarray {
    public static void main(String[] args) {
        int n = 8;
        int[] nums = {-9,10,-8,10,5,-4,-2,5};
        maximumSumBrute(n, nums);
        maxSumDivideAndConquer(n, nums);
        maximumSumKadane(n, nums);
    }

    public static void maximumSumBrute(int n, int[] input){
        int globalMax = 0;
        int globalStart = 0 , globalEnd = 0;
        for(int i = 0; i<n; i++)
        {
            int localMax = input[i];
            int sum = input[i];
            int end = i;
            for(int j = i+1; j<n ;j++)
            {
                sum = sum+input[j];
                if(sum > localMax){
                    localMax = sum;
                    end = j;
                }
            }
            if(localMax > globalMax){
                globalMax = localMax;
                globalStart = i;
                globalEnd = end;
            }

        }
        System.out.println("Max Sum = " + globalMax);
        System.out.println("Start day = " + globalStart+1);
        System.out.println("End day = " + globalEnd+1);
    }

    public static void maxSumDivideAndConquer(int n, int[] input){
        int answer = divideAndConquer(0, n-1, input);
        System.out.println("Max Sum = " + answer);
    }
    public static int divideAndConquer(int start, int end, int[] input)
    {
        if(start == end)
        {
            return input[start];
        }
        int mid = start + (end-start)/2;
        int left = divideAndConquer(start, mid, input);
        int right = divideAndConquer(mid+1, end, input);
        int crossing = maxCrossingSum(start, end, mid, input);
        return Math.max(Math.max(left, right), crossing);
    }
    public static int maxCrossingSum(int start, int end, int mid, int[] input) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= start; i--) {
            sum = sum + input[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum = sum + input[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }
    public static void maximumSumKadane(int n, int[] input) {
        int tempSum = 0;
        int maxSum = 0;
        int maxStart = -1,maxEnd = -1;
        int start = -1;
        for(int i = 0; i < n; i++)
        {
            if(input[i] + tempSum > 0){
                tempSum = tempSum + input[i];
                if(tempSum > maxSum)
                {
                    maxSum = tempSum;
                    maxStart = start;
                    maxEnd = i;
                }
            }
            else{
                tempSum = 0;
                start=i+1;
            }
        }
        System.out.println("Max Sum = " + maxSum);
        System.out.println("Start day = " + (maxStart+1));
        System.out.println("End day = " + (maxEnd+1));
    }
}
