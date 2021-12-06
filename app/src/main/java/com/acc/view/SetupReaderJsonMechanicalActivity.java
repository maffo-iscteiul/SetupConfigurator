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

        this.mSharedActivity.setTrueValuesToActivity(Constants.ANTIROLL_BAR_FRONT_MAX, Constants.ANTIROLL_BAR_FRONT_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.ANTIROLL_BAR_FRONT)),
                binding.progressAntiRollBarFront, binding.textViewAntiRollBarFront, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.ANTIROLL_BAR_REAR_MAX, Constants.ANTIROLL_BAR_REAR_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.ANTIROLL_BAR_REAR)),
                binding.progressAntiRollBarRear, binding.textViewAntiRollBarRear, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BRAKE_POWER_MAX, Constants.BRAKE_POWER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.BRAKE_TORQUE)),
                binding.progressBrakeTorque, binding.textViewBrakeTorque, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BRAKE_BIAS_MAX, Constants.BRAKE_BIAS_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.BRAKE_BIAS)) / 10 * 2,
                binding.progressBrakeBias, binding.textViewBrakeBias, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.STEER_RATIO_MAX, Constants.STEER_RATIO_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.STEER_RATIO)),
                binding.progressSteerRatio, binding.textViewSteerRatio, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.PRELOAD_DIFFERENTIAL_MAX, Constants.PRELOAD_DIFFERENTIAL_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.PRELOAD)),
                binding.progressPreloadRear, binding.textViewPreloadRear, 0);

        //NEEDS IMPROVED TO GET TRUE VALUE
        this.mSharedActivity.setTrueValuesToActivity(Constants.WHEEL_RATE_FRONT_MAX, Constants.WHEEL_RATE_FRONT_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.WHEEL_RATE_FL)),
                binding.progressBarWheelRateLf, binding.textViewWheelRateLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.WHEEL_RATE_FRONT_MAX, Constants.WHEEL_RATE_FRONT_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.WHEEL_RATE_FR)),
                binding.progressBarWheelRateRf, binding.textViewWheelRateRf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.WHEEL_RATE_REAR_MAX, Constants.WHEEL_RATE_REAR_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.WHEEL_RATE_RL)),
                binding.progressBarWheelRateLr, binding.textViewWheelRateLr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.WHEEL_RATE_REAR_MAX, Constants.WHEEL_RATE_REAR_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.WHEEL_RATE_RR)),
                binding.progressBarWheelRateRr, binding.textViewWheelRateRr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RATE_MAX, Constants.BUMPSTOP_RATE_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_RATE_FL)),
                binding.progressBarBumpStopRateLf, binding.textViewBumpStopRateLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RATE_MAX, Constants.BUMPSTOP_RATE_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_RATE_FR)),
                binding.progressBarBumpStopRateRf, binding.textViewBumpStopRateRf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RATE_MAX, Constants.BUMPSTOP_RATE_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_RATE_RL)),
                binding.progressBarBumpStopRateLr, binding.textViewBumpStopRateLr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RATE_MAX, Constants.BUMPSTOP_RATE_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_RATE_RR)),
                binding.progressBarBumpStopRateRr, binding.textViewBumpStopRateRr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RANGE_FRONT_MAX, Constants.BUMPSTOP_RANGE_FRONT_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_WINDOW_FL)),
                binding.progressBarBumpStopRangeLf, binding.textViewBumpStopRangeLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RANGE_FRONT_MAX, Constants.BUMPSTOP_RANGE_FRONT_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_WINDOW_FR)),
                binding.progressBarBumpStopRangeRf, binding.textViewBumpStopRangeRf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RANGE_REAR_MAX, Constants.BUMPSTOP_RANGE_REAR_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_WINDOW_RL)),
                binding.progressBarBumpStopRangeLr, binding.textViewBumpStopRangeLr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMPSTOP_RANGE_REAR_MAX, Constants.BUMPSTOP_RANGE_REAR_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_STOP_WINDOW_RR)),
                binding.progressBarBumpStopRangeRr, binding.textViewBumpStopRangeRr, 0);



    }
}
