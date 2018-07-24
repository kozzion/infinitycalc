package com.coderemote.infinitycalc.model;

import android.util.Log;

import com.coderemote.infinitycalc.tools.DiceTools;

import java.util.Random;

public class ModelFaceToFace {

    public boolean mIsDefencive;
    public int mMyBurst;
    public int mMyTarget;
    public int mMyArmor;
    public int mMyDamage;

    public int mOtherBurst;
    public int mOtherTarget;
    public int mOtherArmor;
    public int mOtherDamage;

    public ModelFaceToFace() {
        mIsDefencive = false;
        mMyBurst = 4;
        mMyTarget = 11;
        mMyArmor = 3;
        mMyDamage = 13;

        mOtherBurst = 1;
        mOtherTarget = 15;
        mOtherArmor = 5;
        mOtherDamage = 14;

    }

    public int simulate(Random random) {
        int[] myToHitRolls = DiceTools.rolld20(random, mMyBurst);
        int[] myHits = DiceTools.getLower(myToHitRolls, mMyTarget);
        int myCritCount = DiceTools.countEqual(myToHitRolls, mMyTarget);
        int[] otherToHitRolls = DiceTools.rolld20(random, mOtherBurst);
        int[] otherHits = DiceTools.getLower(otherToHitRolls, mOtherTarget);
        int otherCritCount = DiceTools.countEqual(otherToHitRolls, mOtherTarget);

        int result = 0;
        if (0 < myCritCount) {
            if (0 < otherCritCount) {
                //Both crit at least once: nothing happens
                result = 0;

            } else {
                //Only i crit
                //Log.e("UT", "outcome: " +  "Only i crit");
                result = myCritCount;
                //Log.e("UT", "outcome: " +  result);
                int otherMax = DiceTools.getMaxValue(otherHits);
                int hitCount = DiceTools.countHigher(myHits, otherMax);
                //Log.e("UT", "hitcount: " +  hitCount);
                int[] otherToSaveRolls = DiceTools.rolld20(random, hitCount);
                result += DiceTools.countHigher(otherToSaveRolls, mMyDamage - mOtherArmor);
                //Log.e("UT", "outcome: " +  result);

            }
        } else {
            if (0 < otherCritCount) {
                //Only other crits
                result = -otherCritCount;

                int myMax = DiceTools.getMaxValue(myHits);
                int hitCount = DiceTools.countHigher(otherHits, myMax);
                int[] myToSaveRolls = DiceTools.rolld20(random, hitCount);
                result -= DiceTools.countHigher(myToSaveRolls, mOtherDamage - mMyArmor);

            } else {
                //Log.e("UT", "outcome: " +  "Nocrit");
                //No crits
                int myMax = DiceTools.getMaxValue(myHits);
                int otherMax = DiceTools.getMaxValue(otherHits);
                if (otherMax == myMax) {
                    //nothing happens
                    result = 0;

                } else {
                    if (myMax < otherMax) {
                        // He wins
                        int hitCount = DiceTools.countHigher(otherHits, myMax);
                        int[] myToSaveRolls = DiceTools.rolld20(random, hitCount);
                        result -= DiceTools.countHigher(myToSaveRolls, mOtherDamage - mMyArmor);
                    } else {
                        // I win
                        int hitCount = DiceTools.countHigher(myHits, otherMax);
                        int[] otherToSaveRolls = DiceTools.rolld20(random, hitCount);
                        result += DiceTools.countHigher(otherToSaveRolls, mMyDamage - mOtherArmor);
                    }
                }
            }
        }
        return result;
    }

    public ModelFaceToFaceResult getResult(Random random, int iterationCount)
    {
        double [] chances = new double [11];
        for (int i = 0; i < iterationCount; i++) {
            int outcome = simulate(random);
            //Log.e("UT", "outcome: " +  outcome);
            chances[outcome + 5]++;
        }
        for (int i = 0; i < 11; i++) {
            chances[i] /= ((double) iterationCount);
        }
        return new ModelFaceToFaceResult(this, chances);
    }
}

