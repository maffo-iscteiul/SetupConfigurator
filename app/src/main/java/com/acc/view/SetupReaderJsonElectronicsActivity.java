package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.constant.Constants;
import com.acc.shared.SecurityPreferences;
import com.acc.databinding.ActivitySetupReaderJsonElectronicsBinding;
import com.acc.shared.SharedActivity;

public class SetupReaderJsonElectronicsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "SetupReaderJsonElect";
    private SecurityPreferences mSecurityPreferences;
    private ActivitySetupReaderJsonElectronicsBinding binding;
    private long mLastClickTime = 0;
    private SharedActivity mSharedActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupReaderJsonElectronicsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mSharedActivity = new SharedActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!this.isFinishing()) {
            loadDataToActivity();
            binding.viewArrowForward.setOnClickListener(this);
            binding.viewArrowBackward.setOnClickListener(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        if (view.getId() == binding.viewArrowForward.getId()) {
            mLastClickTime = SystemClock.elapsedRealtime();
            Intent intent = new Intent(this, SetupReaderJsonMechanicalActivity.class);
            startActivity(intent);
        }
        if (view.getId() == binding.viewArrowBackward.getId()) {
            mLastClickTime = SystemClock.elapsedRealtime();
            this.finish();
        }
    }

    private void loadDataToActivity() {
        //Loads the 1st values to this activity
        // Gets the values from mSecurityPreferences

        //SET BACKGROUND IMAGE FROM JSON CAR
        this.mSharedActivity.setBackgroundImageByCar(mSecurityPreferences.getStoredString(Constants.CAR_NAME), binding.imageViewBackground);

        //Load Values
        this.mSharedActivity.setTrueValuesToActivity(Constants.TC_MAX, Constants.TC_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.ABS)),
                binding.progressAbs, binding.textViewAbs, 0);

    }

}