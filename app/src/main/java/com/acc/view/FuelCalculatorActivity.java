package com.acc.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.R;
import com.acc.constant.Constants;
import com.acc.databinding.ActivityFuelCalculatorBinding;

public class FuelCalculatorActivity extends AppCompatActivity implements View.OnClickListener, CheckBox.OnCheckedChangeListener {

    private ActivityFuelCalculatorBinding binding;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFuelCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!this.isFinishing()) {
            binding.viewArrowBackward.setOnClickListener(this);
            binding.buttonCalculateFuel.setOnClickListener(this);
            binding.checkboxFormationLap.setOnCheckedChangeListener(this);
            binding.editStintLengthMinutes.setInputType(InputType.TYPE_CLASS_NUMBER);
            binding.editLapTimeMinutes.setInputType(InputType.TYPE_CLASS_NUMBER);
            binding.editLapTimeSeconds.setInputType(InputType.TYPE_CLASS_NUMBER);
            binding.editLitresPerLapLitres.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }

    @Override
    protected void onDestroy() {
        this.clearValues();
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (view.getId() == binding.buttonCalculateFuel.getId()) {
            if (this.isValidValues()) {
                binding.progressRiskyFuel.setProgress(100);
                binding.progressSafeFuel.setProgress(100);
                binding.textViewRiskyFuel.setText(calculateFuel(Constants.RISKY_FUEL));
                binding.textViewSafeFuel.setText(calculateFuel(Constants.SAFE_FUEL));
            } else {
                Toast.makeText(this, "Informe um valor ou um valor diferente de 0", Toast.LENGTH_LONG).show();
            }
        }
        if (view.getId() == binding.viewArrowBackward.getId()) {
            mLastClickTime = SystemClock.elapsedRealtime();
            this.finish();
        }
    }

    private void clearValues() {
        binding.textViewRiskyFuel.setText("0 L");
        binding.textViewSafeFuel.setText("0 L");
        binding.progressRiskyFuel.setProgress(0);
        binding.progressSafeFuel.setProgress(0);
    }

    private String calculateFuel(String typeOfFuelLoad) {
        double lapTimeMinutes = Double.parseDouble(binding.editLapTimeMinutes.getText().toString());
        double lapTimeSeconds = Double.parseDouble(binding.editLapTimeSeconds.getText().toString());
        double stintLengthMinutes = Double.parseDouble(binding.editStintLengthMinutes.getText().toString());
        double litresPerLap = Double.parseDouble(binding.editLitresPerLapLitres.getText().toString());
        double extraLitres = 0.0;

        if (binding.checkboxFormationLap.isChecked()) {
            extraLitres = litresPerLap;
        }
        Double totalLapTimeMinutes = 0.0;
        if (totalLapTimeMinutes.equals(lapTimeSeconds)) {
            totalLapTimeMinutes = lapTimeMinutes;
        } else {
            totalLapTimeMinutes = (lapTimeSeconds / 60) + lapTimeMinutes;
        }
        double fuel = 0.0;
        if (typeOfFuelLoad.equals(Constants.RISKY_FUEL)) {
            fuel = stintLengthMinutes / totalLapTimeMinutes * litresPerLap + litresPerLap + extraLitres;
        } else if (typeOfFuelLoad.equals(Constants.SAFE_FUEL)) {
            fuel = stintLengthMinutes / totalLapTimeMinutes * litresPerLap + litresPerLap * 2 + extraLitres;
        }
        int numberOfDecimals = 0;
        return String.format("%." + numberOfDecimals + "f L", fuel);
    }

    private boolean isValidValues() {
        if ("".equals(binding.editLapTimeMinutes.getText().toString())) {
            return false;
        } else if ("".equals(binding.editLapTimeSeconds.getText().toString())) {
            return false;
        } else if ("".equals(binding.editStintLengthMinutes.getText().toString())) {
            return false;
        } else if ("".equals(binding.editLitresPerLapLitres.getText().toString())) {
            return false;
        }
        Double zero = 0.0;
        Double lapTimeMinutes = Double.valueOf(binding.editLapTimeMinutes.getText().toString());
        Double stintLengthMinutes = Double.valueOf(binding.editStintLengthMinutes.getText().toString());
        Double litresPerLap = Double.valueOf(binding.editLitresPerLapLitres.getText().toString());

        if (lapTimeMinutes > stintLengthMinutes) {
            return false;
        }

        if (zero.equals(lapTimeMinutes)) {
            return false;
        } else if (zero.equals(stintLengthMinutes)) {
            return false;
        } else return !zero.equals(litresPerLap);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            binding.checkboxFormationLap.setText(R.string.yes);
        } else {
            binding.checkboxFormationLap.setText(R.string.no);
        }
    }
}