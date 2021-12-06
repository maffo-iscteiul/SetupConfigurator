package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.constant.Constants;
import com.acc.shared.SecurityPreferences;
import com.acc.databinding.ActivitySetupReaderJsonDampersBinding;
import com.acc.shared.SharedActivity;

public class SetupReaderJsonDampersActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "SetupReaderJsonDampers";
    private SecurityPreferences mSecurityPreferences;
    private ActivitySetupReaderJsonDampersBinding binding;
    private long mLastClickTime = 0;
    private SharedActivity mSharedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupReaderJsonDampersBinding.inflate(getLayoutInflater());
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

    private void loadDataToActivity() {
        //Loads the 1st values to this activity
        // Gets the values from mSecurityPreferences

        //SET BACKGROUND IMAGE FROM JSON CAR
        this.mSharedActivity.setBackgroundImageByCar(mSecurityPreferences.getStoredString(Constants.CAR_NAME), binding.imageViewBackground);

        //Load Values
        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMP_STOP_MAX, Constants.BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_FL)),
                binding.progressBarBumpLf, binding.textViewBumpLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMP_STOP_MAX, Constants.BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_FR)),
                binding.progressBarBumpRf, binding.textViewBumpRf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMP_STOP_MAX, Constants.BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_RL)),
                binding.progressBarBumpLr, binding.textViewBumpLr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BUMP_STOP_MAX, Constants.BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_RR)),
                binding.progressBarBumpRr, binding.textViewBumpRr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_BUMP_STOP_MAX, Constants.FAST_BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_FAST_FL)),
                binding.progressBarFastBumpLf, binding.textViewFastBumpLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_BUMP_STOP_MAX, Constants.FAST_BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_FAST_FR)),
                binding.progressBarFastBumpRf, binding.textViewFastBumpRf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_BUMP_STOP_MAX, Constants.FAST_BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_FAST_RL)),
                binding.progressBarFastBumpLr, binding.textViewFastBumpLr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_BUMP_STOP_MAX, Constants.FAST_BUMP_STOP_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BUMP_FAST_RR)),
                binding.progressBarFastBumpRr, binding.textViewFastBumpRr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.REBOUND_MAX, Constants.REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_FL)),
                binding.progressBarReboundLf, binding.textViewReboundLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.REBOUND_MAX, Constants.REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_FR)),
                binding.progressBarReboundRf, binding.textViewReboundRf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.REBOUND_MAX, Constants.REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_RL)),
                binding.progressBarReboundLr, binding.textViewReboundLr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.REBOUND_MAX, Constants.REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_RR)),
                binding.progressBarReboundRr, binding.textViewReboundRr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_REBOUND_MAX, Constants.FAST_REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_FAST_FL)),
                binding.progressBarFastReboundLf, binding.textViewFastReboundLf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_REBOUND_MAX, Constants.FAST_REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_FAST_FR)),
                binding.progressBarFastReboundRf, binding.textViewFastReboundRf, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_REBOUND_MAX, Constants.FAST_REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_FAST_RL)),
                binding.progressBarFastReboundLr, binding.textViewFastReboundLr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.FAST_REBOUND_MAX, Constants.FAST_REBOUND_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REBOUND_FAST_RR)),
                binding.progressBarFastReboundRr, binding.textViewFastReboundRr, 0);

    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        if (view.getId() == binding.viewArrowForward.getId()) {
            mLastClickTime = SystemClock.elapsedRealtime();
            Intent intent = new Intent(this, SetupReaderJsonAeroActivity.class);
            startActivity(intent);
        }
        if (view.getId() == binding.viewArrowBackward.getId()) {
            mLastClickTime = SystemClock.elapsedRealtime();
            this.finish();
        }
    }

}