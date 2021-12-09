package com.acc.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (!isFinishing()) {
            binding.buttonRaceTactics.setOnClickListener(this);
            binding.buttonSetupConfig.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (view.getId() == binding.buttonRaceTactics.getId()) {
            Intent intent = new Intent(this, RaceTacticsActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        if (view.getId() == binding.buttonSetupConfig.getId()) {
            Intent intent = new Intent(this, SetupConfigActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }
}
