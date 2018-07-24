package com.coderemote.infinitycalc.model;

public class ModelFaceToFaceResult {

    ModelFaceToFace mSource;

    public double [] mChances;
    public double mExpectedWounds;

    public ModelFaceToFaceResult(ModelFaceToFace source, double [] chances)
    {
        mSource = source;
        mChances = chances;
        mExpectedWounds = 0;
        for (int i = 0; i < 11; i++) {
            mExpectedWounds = mChances[i] * (i-5);
        }
    }
}
