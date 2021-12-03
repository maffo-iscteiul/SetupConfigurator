package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.databinding.ActivityRaceTacticsBinding;

public class RaceTacticsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRaceTacticsBinding binding;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRaceTacticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonFuelCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (view.getId() == binding.buttonFuelCalculator.getId()) {

            Intent intent = new Intent(this, FuelCalculatorActivity.class);

            startActivity(intent);
        }
    }
}
