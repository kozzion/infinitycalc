package com.coderemote.infinitycalc.tools;

import java.util.Random;

public class DiceTools {
    public static int [] rolld20(Random random, int rollCount)
    {
        int [] rolls = new int[rollCount];
        for (int i = 0; i < rollCount; i++) {
            rolls[i] = random.nextInt(20) + 1;
        }
        return rolls;
    }

    public static int[] getLower(int[] rolls, int target) {

        int [] lower = new int[countLower(rolls, target)];
        int lowerIndex = 0;
        for (int i = 0; i < rolls.length; i++) {
            if(rolls[i] < target){
                lower[lowerIndex] = rolls[i];
                lowerIndex++;
            }
        }
        return lower;
    }

    public static int countLower(int[] rolls, int target) {
        int count = 0;
        for (int i = 0; i < rolls.length; i++) {
            if(rolls[i] < target){
                count++;
            }
        }
        return count;
    }

    public static int countEqual(int[] rolls, int target) {
        int count = 0;
        for (int i = 0; i < rolls.length; i++) {
            if(rolls[i] == target){
                count++;
            }
        }
        return count;
    }

    public static int countHigher(int[] rolls, int target) {
        int count = 0;
        for (int i = 0; i < rolls.length; i++) {
            if(target < rolls[i]){
                count++;
            }
        }
        return count;
    }

    public static int getMaxValue(int[] rolls) {
        int maxValue = 0;
        for (int i = 0; i < rolls.length; i++) {
            if(maxValue < rolls[i]){
                maxValue =  rolls[i];
            }
        }
        return maxValue;
    }


}
