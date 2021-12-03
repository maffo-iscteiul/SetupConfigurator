package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.constant.Constants;
import com.acc.shared.SecurityPreferences;
import com.acc.databinding.ActivitySetupReaderJsonMechanicalBinding;
import com.acc.shared.SharedActivity;

public class SetupReaderJsonMechanicalActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = "SetupReaderJsonMecha";
    private SecurityPreferences mSecurityPreferences;
    private long mLastClickTime = 0;
    private ActivitySetupReaderJsonMechanicalBinding binding;
    private SharedActivity mSharedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetupReaderJsonMechanicalBinding.inflate(getLayoutInflater());
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
            Intent intent = new Intent(this, SetupReaderJsonDampersActivity.class);
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

        //Falta o load para a atividade!

        this.mSharedActivity.setTrueValuesToActivity(Constants.TC_MAX, Constants.TC_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.WHEEL_RATE_FL)),
                binding.progressBarWheelRateLf, binding.textViewWheelRateLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TC_MAX, Constants.TC_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.WHEEL_RATE_FR)),
                binding.progressBarWheelRateLr, binding.textViewWheelRateLr, 1);


    }
}
