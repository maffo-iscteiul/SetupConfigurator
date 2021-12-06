package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.constant.Constants;
import com.acc.databinding.ActivitySetupReaderJsonTyresBinding;
import com.acc.shared.SecurityPreferences;
import com.acc.shared.SharedActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.perf.metrics.AddTrace;


public class SetupReaderJsonTyresActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "SetupReaderJson";
    private SecurityPreferences mSecurityPreferences;
    private ActivitySetupReaderJsonTyresBinding binding;
    private long mLastClickTime = 0;
    private SharedActivity mSharedActivity;


    @Override
    @AddTrace(name = "onCreateSetupReaderJsonTyresActivity")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupReaderJsonTyresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mSharedActivity = new SharedActivity(this);

        MobileAds.initialize(this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });

        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adViewPub.loadAd(adRequest);

        //Trace trace = FirebasePerformance.getInstance().newTrace("test_trace");

        //trace.start();
        //do_something_code
        //trace.stop();


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

    @AddTrace(name = "loadTyresDataToActivity")
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

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_FRONT_MAX, Constants.TOE_FRONT_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_FL)) / 100,
                binding.progressBarToeFl, binding.textViewToeFl, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_FRONT_MAX, Constants.TOE_FRONT_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_FR)) / 100,
                binding.progressBarToeFr, binding.textViewToeFr, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_REAR_MAX, Constants.TOE_REAR_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_RL)) / 100,
                binding.progressBarToeRl, binding.textViewToeRl, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.TOE_REAR_MAX, Constants.TOE_REAR_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.TOE_RR)) / 100,
                binding.progressBarToeRr, binding.textViewToeRr, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CASTER_MAX, Constants.CASTER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CASTER_FL)) / 10,
                binding.progressBarCasterFl, binding.textViewCasterFl, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CASTER_MAX, Constants.CASTER_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CASTER_FR)) / 10,
                binding.progressBarCasterFr, binding.textViewCasterFr, 2);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_FRONT_MAX, Constants.CAMBER_FRONT_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_FL)) / 10,
                binding.progressBarCamberFl, binding.textViewCamberFl, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_FRONT_MAX, Constants.CAMBER_FRONT_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_FR)) / 10,
                binding.progressBarCamberFr, binding.textViewCamberFr, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_REAR_MAX, Constants.CAMBER_REAR_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_RL)) / 10,
                binding.progressBarCamberRl, binding.textViewCamberRl, 1);

        this.mSharedActivity.setTrueValuesToActivity(Constants.CAMBER_REAR_MAX, Constants.CAMBER_REAR_MIN,
                Float.parseFloat(mSecurityPreferences.getStoredString(Constants.CAMBER_RR)) / 10,
                binding.progressBarCamberRr, binding.textViewCamberRr, 1);
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