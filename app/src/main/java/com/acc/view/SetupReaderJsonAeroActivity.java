package com.acc.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.constant.Constants;
import com.acc.shared.SecurityPreferences;
import com.acc.databinding.ActivitySetupReaderJsonAeroBinding;
import com.acc.shared.SharedActivity;

public class SetupReaderJsonAeroActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = "SetupReaderJsonAero";
    private SecurityPreferences mSecurityPreferences;
    private ActivitySetupReaderJsonAeroBinding binding;
    private SharedActivity mSharedActivity;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetupReaderJsonAeroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mSharedActivity = new SharedActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!this.isFinishing()) {
            loadDataToActivity();
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
        this.mSharedActivity.setTrueValuesToActivity(Constants.RIDE_HEIGHT_FRONT_MAX, Constants.RIDE_HEIGHT_FRONT_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.RIDE_HEIGHT_FRONT)),
                binding.progressHeightFront, binding.textViewHeightFront, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.RIDE_HEIGHT_REAR_MAX, Constants.RIDE_HEIGHT_REAR_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.RIDE_HEIGHT_REAR)),
                binding.progressHeightRear, binding.textViewHeightRear, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BRAKE_DUCTS_MAX, Constants.BRAKE_DUCTS_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BRAKE_DUCT_FRONT)),
                binding.progressBrakeDuctsFront, binding.textViewBrakeDuctsFront, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.BRAKE_DUCTS_MAX, Constants.BRAKE_DUCTS_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.BRAKE_DUCT_REAR)),
                binding.progressBrakeDuctsRear, binding.textViewBrakeDuctsRear, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.SPLITTER_MAX, Constants.SPLITTER_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.SPLITTER)),
                binding.progressSplitter, binding.textViewSplitter, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.REAR_WING_MAX, Constants.REAR_WING_MIN,
                Float.valueOf(mSecurityPreferences.getStoredString(Constants.REAR_WING)),
                binding.progressWing, binding.textViewWing, 0);
    }


}
