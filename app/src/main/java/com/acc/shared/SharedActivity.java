package com.acc.shared;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acc.R;

public class SharedActivity {

    private static final String LOG_TAG = "SharedActivity";
    private final Context context;

    public SharedActivity(Context context) {
        this.context = context;
    }

    public Float getTrueValue(Float min, Float value) {
        return value + min;
    }

    public Float getProgressTrueValue(Float max, Float min, Float value) {
        return (100 / (max - min) * (value - min));
    }

    public void setBackgroundImageByCar(String carName, ImageView imageViewBackground) {
        String background = "drawable/" + carName;
        int imageResource = context.getResources().getIdentifier(background, null, context.getPackageName());
        Log.e(LOG_TAG, background);
        Log.e(LOG_TAG, String.valueOf(imageResource));
        if (imageResource != 0) {
            //Descomentar esta parte depois
            //imageViewBackground.setImageResource(imageResource);
            imageViewBackground.setImageResource(R.drawable.m6_gt3);
        } else {
            Log.e(LOG_TAG, String.valueOf(imageResource));
            Log.e(LOG_TAG, "There isn't any image for this ID");
            imageViewBackground.setImageResource(0);
        }
    }

    public void setTrueValuesToActivity(Float max, Float min, Float value, ProgressBar progressBar, TextView textView, int numberOfDecimals) {
        // set True Value To Activity
        // Set True Progress Bar Value To Activity

        // Value needs to be the REAL VALUE TO ADD TO THE MIN AND NOT THE THE TRUE VALUE FROM JSON
        // Exemplo WHEEL RATE TEM DE SER O VALOR QUE É AUMENTA DA DIFERENÇA DO MINIMO AO VALOR SUPOSTO APARECER
        // POR EXEMPLO, CASO OS VALORES SEJAM DE 0,2 EM 0,2, O VALOR DEVERÁ SER O NÚMERO DE CLICKS VEZES 0,2


        // É PROVÁVEL QUE TENHA SER ADICIONADO UMA PROPRIEDADE DE "STEP" PORQUE HÁ CAMPOS QUE VÃO 0.2 EM 0.2 POR EXEMPLO BRAKE BIAS/POWER
        Log.e(LOG_TAG, String.valueOf(progressBar));
        Float trueValue = getTrueValue(min, value);
        if (isValueValid(max, min, trueValue)) {
            textView.setText(String.format("%." + numberOfDecimals + "f", trueValue.doubleValue()));
            Float trueProgressValue = getProgressTrueValue(max, min, trueValue);
            progressBar.setProgress(trueProgressValue.intValue());
        }
        else {
            textView.setText(R.string.error);
            progressBar.setProgress(0);
        }


    }

    private boolean isValueValid(Float max, Float min, Float value) {
        Log.e(LOG_TAG, String.valueOf(max));
        Log.e(LOG_TAG, String.valueOf(min));
        Log.e(LOG_TAG, String.valueOf(value));
        return value >= min && value <= max;
    }

}
