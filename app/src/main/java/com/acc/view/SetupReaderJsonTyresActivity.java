package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.constant.Constants;
import com.acc.shared.SecurityPreferences;
import com.acc.databinding.ActivitySetupReaderJsonTyresBinding;
import com.acc.shared.SharedActivity;

public class SetupReaderJsonTyresActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "SetupReaderJson";
    private SecurityPreferences mSecurityPreferences;
    private ActivitySetupReaderJsonTyresBinding binding;
    private long mLastClickTime = 0;
    private SharedActivity mSharedActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupReaderJsonTyresBinding.inflate(getLayoutInflater());
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

        //TYRE PRESSURE

        this.mSharedActivity.setTrueValuesToActivity(Constants.TYRE_PRESSURE_MAX, Constants.TYRE_PRESSURE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TYRE_PRESSURE_FL)) / 10,
                binding.progressBarTyrePressureFl, binding.textViewTyrePressureFl, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TYRE_PRESSURE_MAX, Constants.TYRE_PRESSURE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TYRE_PRESSURE_FR)) / 10,
                binding.progressBarTyrePressureFr, binding.textViewTyrePressureFr, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TYRE_PRESSURE_MAX, Constants.TYRE_PRESSURE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TYRE_PRESSURE_RL)) / 10,
                binding.progressBarTyrePressureRl, binding.textViewTyrePressureRl, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TYRE_PRESSURE_MAX, Constants.TYRE_PRESSURE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TYRE_PRESSURE_RR)) / 10,
                binding.progressBarTyrePressureRr, binding.textViewTyrePressureRr, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_MAX, Constants.TOE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_FL)) / 100,
                binding.progressBarToeFl, binding.textViewToeFl, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_MAX, Constants.TOE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_FR)) / 100,
                binding.progressBarToeFr, binding.textViewToeFr, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_MAX, Constants.TOE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_RL)) / 100,
                binding.progressBarToeRl, binding.textViewToeRl, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_MAX, Constants.TOE_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_RR)) / 100,
                binding.progressBarToeRr, binding.textViewToeRr, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CASTER_MAX, Constants.CASTER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CASTER_FL)) / 10,
                binding.progressBarCasterFl, binding.textViewCasterFl, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CASTER_MAX, Constants.CASTER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CASTER_FR)) / 10,
                binding.progressBarCasterFr, binding.textViewCasterFr, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_MAX, Constants.CAMBER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_FL)),
                binding.progressBarCamberFl, binding.textViewCamberFl, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_MAX, Constants.CAMBER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_FR)),
                binding.progressBarCamberFr, binding.textViewCamberFr, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_MAX, Constants.CAMBER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_RL)),
                binding.progressBarCamberRl, binding.textViewCamberRl, 0);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_MAX, Constants.CAMBER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_RR)),
                binding.progressBarCamberRr, binding.textViewCamberRr, 0);
    }


    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        if (view.getId() == binding.viewArrowForward.getId()) {
            mLastClickTime = SystemClock.elapsedRealtime();
            Intent intent = new Intent(this, SetupReaderJsonElectronicsActivity.class);
            startActivity(intent);
        }
    }
}