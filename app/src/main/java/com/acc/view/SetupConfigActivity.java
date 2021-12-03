package com.acc.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.acc.databinding.ActivitySetupConfigBinding;


public class SetupConfigActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySetupConfigBinding binding;
    private static final String LOG_TAG = "SetupConfig";
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetupConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSetupReader.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (view.getId() == binding.buttonSetupReader.getId()) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            boolean isAllowed = (result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED);
            Log.e(LOG_TAG, String.valueOf(result));
            Log.e(LOG_TAG, String.valueOf(result1));
            Log.e(LOG_TAG, String.valueOf(isAllowed));
            Log.e(LOG_TAG, String.valueOf(Build.VERSION.SDK_INT));
            Log.e(LOG_TAG, String.valueOf(PackageManager.PERMISSION_GRANTED));

            //IF ALLOWS
            if (isAllowed) {
                Intent intent = new Intent(this, SetupReaderActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "É preciso acesso a todos os ficheiros do dispositivo!", Toast.LENGTH_LONG).show();
            }

            //ELSE NOT ALLOWS
            //ASK FOR PERMISSION
        }
    }
}