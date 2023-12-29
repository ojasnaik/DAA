package DAAAssignments;

import java.util.*;
public class GarageSale {
    static Map<Set<Integer>, int[]> cache;
    static int n = 5;
    static int[][] d;
    static int[] value;
    public static void main(String[] args) {
        value = new int[]{0, 10, 20, 30, 40, 50};
        d = new int[][]{
                {0, 10, 15, 20, 25, 30},
                {10, 0, 5, 10, 15, 20},
                {15, 5, 0, 5, 10, 15},
                {20, 10, 5, 0, 5, 10},
                {25, 15, 10, 5, 0, 5},
                {30, 20, 15, 10, 5, 0}
        };
        cache = new HashMap<>();
        System.out.println(max_benefit());
    }
    private static int max_benefit() {
        List<Set<Integer>> allSubsets = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            Set<Integer> subset = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(j + 1);
                }
            }
            allSubsets.add(subset);
            cache.put(subset, new int[n+1]);
        }

        int max = 0;
        for(Set<Integer> workingSet: allSubsets){
            for(int i : workingSet) {
                max = Math.max(max, dp(i, workingSet) + d[i][0]);
            }
        }
        return max;
    }
    private static int dp(int endAt, Set<Integer> workingSet) {
        if(endAt == 0) return Integer.MAX_VALUE;
        if(workingSet.size() == 1) return value[endAt];

        if(cache.get(workingSet)[endAt] !=0 )return cache.get(workingSet)[endAt];
        Set<Integer> currentWorkingSet = new HashSet<>(workingSet);
        currentWorkingSet.remove(endAt);
        int max = 0;
        for(int i : workingSet){
            max = Math.max(max, value[endAt]-d[i][endAt] + dp(i, currentWorkingSet));
        }
        return cache.get(workingSet)[endAt] = max;
    }
}
