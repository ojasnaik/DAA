package DAAAssignments;

public class WhereZero {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 6, 7, 8, 9, 20};
        int[] arr2 = {0, 1, 3, 4, 6, 7, 8, 9, 20};

        System.out.println(getIndexOfZero(arr1, arr2));
    }

    private static int getIndexOfZero(int[] arr1, int[] arr2)
    {
        int length = arr2.length;
        int start = 0;
        int end = length-1;

        while(start <= end)
        {
            int mid = (start+end)/2;
            if (arr2[mid] == 0)
            {
                return mid;
            }
            if(arr1[mid] == arr2[mid])
            {
                start = mid+1;
            }
            else {
                end = end-1;
            }
        }
        return -1;
    }
}
