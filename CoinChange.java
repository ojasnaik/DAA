package DAAAssignments;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        makeChangeGreedy(90);

    }

    public static void makeChangeGreedy(int value) {
        int quarters = value / 25;
        value %= 25;
        int dimes = value / 10;
        value %= 10;
        int nickels = value / 5;
        value %= 5;
        int pennies = value;

        System.out.println("Quarters : " + quarters);
        System.out.println("Dimes : " + dimes);
        System.out.println("Nickels : " + nickels);
        System.out.println("Pennies : " + pennies);
        System.out.println("Total : " + (quarters + dimes + nickels + pennies));
    }


    private int makeChangeDynamic(int[] coins, int amount) {
        int array[] = new int[amount + 1];
        Arrays.fill(array, amount + 1);
        array[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                int check = i - coin;
                if (check >= 0 && (array[check] + 1 < array[i])) {
                    array[i] = array[check] + 1;
                }
            }
        }
        return array[amount] == amount + 1 ? -1 : array[amount];
    }

}
