package com.coderemote.infinitycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.coderemote.infinitycalc.model.ModelFaceToFace;
import com.coderemote.infinitycalc.model.ModelFaceToFaceResult;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ModelFaceToFace mF2F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mF2F = new ModelFaceToFace();
        (findViewById(R.id.f2f_bt_comute)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }
    public void update(){
        Random random = new Random();
        int myBurst = Integer.parseInt(((EditText)findViewById(R.id.f2f_et_my_burst)).getText().toString());
        int otherBurst = Integer.parseInt(((EditText)findViewById(R.id.f2f_et_other_burst)).getText().toString());
        mF2F.mMyBurst = myBurst;
        mF2F.mOtherBurst = otherBurst;
        ModelFaceToFaceResult result = mF2F.getResult(random, 10000);
        DecimalFormat df = new DecimalFormat("#.00");
        ((TextView)findViewById(R.id.f2f_result_tv_min5)).setText(df.format(result.mChances[0]));
        ((TextView)findViewById(R.id.f2f_result_tv_min4)).setText(df.format(result.mChances[1]));
        ((TextView)findViewById(R.id.f2f_result_tv_min3)).setText(df.format(result.mChances[2]));
        ((TextView)findViewById(R.id.f2f_result_tv_min2)).setText(df.format(result.mChances[3]));
        ((TextView)findViewById(R.id.f2f_result_tv_min1)).setText(df.format(result.mChances[4]));
        ((TextView)findViewById(R.id.f2f_result_tv_null)).setText(df.format(result.mChances[5]));
        ((TextView)findViewById(R.id.f2f_result_tv_plus1)).setText(df.format(result.mChances[6]));
        ((TextView)findViewById(R.id.f2f_result_tv_plus2)).setText(df.format(result.mChances[7]));
        ((TextView)findViewById(R.id.f2f_result_tv_plus3)).setText(df.format(result.mChances[8]));
        ((TextView)findViewById(R.id.f2f_result_tv_plus4)).setText(df.format(result.mChances[9]));
        ((TextView)findViewById(R.id.f2f_result_tv_plus5)).setText(df.format(result.mChances[10]));
    }
}
