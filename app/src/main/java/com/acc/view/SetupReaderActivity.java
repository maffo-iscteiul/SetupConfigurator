package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.acc.R;
import com.acc.constant.Constants;
import com.acc.databinding.ActivitySetupReaderBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetupReaderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = "SetupReader";
    private final List<String> folderList = new ArrayList<>();
    private ArrayAdapter<String> directoryList;
    private ActivitySetupReaderBinding binding;
    private long mLastClickTime = 0;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetupReaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        directoryList = new ArrayAdapter<>(this, R.layout.list_files, folderList);
        binding.listFolders.setAdapter(directoryList);

        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        binding.progressbarView.setVisibility(View.VISIBLE);
        binding.listFolders.setVisibility(View.GONE);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                File root = new File(String.valueOf(Environment.getExternalStorageDirectory()));
                listFolders(root);
                if (folderList.isEmpty()) {
                    showToast("Não foi encontrado nenhumas pastas!");
                    finish();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressbarView.setVisibility(View.GONE);
                        binding.listFolders.setVisibility(View.VISIBLE);
                        directoryList.notifyDataSetChanged();
                    }
                });
            }
        });

        if (!isFinishing()) {
            binding.listFolders.setOnItemClickListener(this);
        }
    }

    private void listFolders(File directory) {
        String[] values = directory.list();
        assert values != null;
        for (String value : values) {
            if (!value.startsWith(".")) {
                Log.e(LOG_TAG, value);
                folderList.add(value);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        Intent intent = new Intent(this, SetupReaderFilesActivity.class);

        String filePath = String.valueOf(Environment.getExternalStorageDirectory()) + '/' + adapterView.getItemAtPosition(i);
        intent.putExtra(Constants.FILE_PATH, filePath);
        startActivity(intent);

    }

    private void showToast(String string) {
        this.mToast.setText(string);
        this.mToast.show();
    }
}