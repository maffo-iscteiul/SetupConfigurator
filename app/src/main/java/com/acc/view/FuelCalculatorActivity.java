package com.acc.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.databinding.ActivityFuelCalculatorBinding;

public class FuelCalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFuelCalculatorBinding binding;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFuelCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCalculateFuel.setOnClickListener(this);

        this.clearValues();
    }

    private void clearValues() {
        binding.textCalculatedLitres.setText("0");
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (view.getId() == binding.buttonCalculateFuel.getId()) {
            if (this.isValidValues()) {
                String fuel = calculateFuel();
                binding.textCalculatedLitres.setText(fuel);
            } else {
                Toast.makeText(this, "Informe um valor ou um valor diferente de 0", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String calculateFuel() {
        Double lapTimeMinutes = Double.valueOf(binding.editLapTimeMinutes.getText().toString());
        Double lapTimeSeconds = Double.valueOf(binding.editLapTimeSeconds.getText().toString());
        Double stintLengthMinutes = Double.valueOf(binding.editStingLengthMinutes.getText().toString());
        Double litresPerLap = Double.valueOf(binding.editLitresPerLapLitres.getText().toString());
        Double extraLitres = 0.0;
        //asdf
        if (binding.checkboxFormationOnLap.isChecked()) {
            extraLitres = litresPerLap;
        }
        Double totalLapTimeMinutes = 0.0;
        if (totalLapTimeMinutes.equals(lapTimeSeconds)) {
            totalLapTimeMinutes = lapTimeMinutes;
        } else {
            totalLapTimeMinutes = (lapTimeSeconds / 60) + lapTimeMinutes;
        }

        Double fuel = stintLengthMinutes / totalLapTimeMinutes * litresPerLap + litresPerLap + extraLitres;

        return String.format("%.0f", fuel);
    }

    private boolean isValidValues() {
        if ("".equals(binding.editLapTimeMinutes.getText().toString())) {
            return false;
        } else if ("".equals(binding.editLapTimeSeconds.getText().toString())) {
            return false;
        } else if ("".equals(binding.editStingLengthMinutes.getText().toString())) {
            return false;
        } else if ("".equals(binding.editLitresPerLapLitres.getText().toString())) {
            return false;
        }
        Double zero = 0.0;
        Double lapTimeMinutes = Double.valueOf(binding.editLapTimeMinutes.getText().toString());
        Double stintLengthMinutes = Double.valueOf(binding.editStingLengthMinutes.getText().toString());
        Double litresPerLap = Double.valueOf(binding.editLitresPerLapLitres.getText().toString());

        if (zero.equals(lapTimeMinutes)) {
            return false;
        } else if (zero.equals(stintLengthMinutes)) {
            return false;
        } else return !zero.equals(litresPerLap);
    }
}