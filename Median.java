package DAAAssignments;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Median {

    public static void main(String[] args) {

        char[] array = {'G', 'F', 'R', 'R', 'C'};
        char[] array2 = {'F', 'A', 'D', 'A', 'B', 'A'};
        System.out.println(getMedian(array));
        System.out.println(getMode(array2));

    }

    private static Character getMedian(char[] array)
    {
        Map<Character, Integer> countMap = new TreeMap<>();
        int length = array.length;
        for (int i = 0; i< length; i++)
        {
            countMap.put(array[i], countMap.getOrDefault(array[i], 0) + 1);
        }
        int n = (length % 2 == 0)? (length/2) - 1 : (length)/2;

        Character median = null;
        for (char key : countMap.keySet())
        {
            median = key;
            n=n-countMap.get(key);
            if (n<0){
                break;
            }
        }
        return median;
    }

    private static Character getMode(char[] array)
    {
        Map<Character, Integer> countMap = new HashMap<>();
        int length = array.length;
        Character maxChar = null;
        int maxCount = 0;
        for (int i = 0; i< length; i++)
        {
            int value = countMap.getOrDefault(array[i], 0) + 1;
            countMap.put(array[i], value);
            if (value > maxCount){
                maxCount = value;
                maxChar = array[i];
            }
        }
        return maxChar;
    }

}
