package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
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
import com.acc.data.JSONData;
import com.acc.databinding.ActivitySetupReaderFilesBinding;
import com.acc.shared.SecurityPreferences;
import com.google.firebase.perf.metrics.AddTrace;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetupReaderFilesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = "SetupReaderFiles";
    private ActivitySetupReaderFilesBinding binding;
    private final List<String> fileListPath = new ArrayList<>();
    private final List<String> fileList = new ArrayList<>();
    private ArrayAdapter<String> fileArrayList;
    private JSONData mJSONData = new JSONData();
    private long mLastClickTime = 0;
    private SecurityPreferences mSecurityPreferences;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetupReaderFilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        fileArrayList = new ArrayAdapter<>(this, R.layout.list_files, fileList);
        binding.listFiles.setAdapter(fileArrayList);

        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        binding.progressbarView.setVisibility(View.VISIBLE);
        binding.listFiles.setVisibility(View.GONE);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                File folder = new File(loadDataFromActivity());
                listFiles(folder);
                if (fileList.isEmpty() || fileListPath.isEmpty()) {
                    showToast("N??o foi encontrado nenhuns ficheiros JSON");
                    finish();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressbarView.setVisibility(View.GONE);
                        binding.listFiles.setVisibility(View.VISIBLE);
                        fileArrayList.notifyDataSetChanged();
                    }
                });
            }
        });

        if (!this.isFinishing()) {
            binding.listFiles.setOnItemClickListener(this);
        }

    }

    private void showToast(String string) {
        this.mToast.setText(string);
        this.mToast.show();
    }

    private void listFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file != null) {
                    if (file.isDirectory()) {
                        listFiles(file);
                    } else {
                        if (file.getPath().toLowerCase(Locale.ROOT).endsWith(".json")) {
                            fileListPath.add(file.getPath());
                            fileList.add(file.getName());
                            Log.e(LOG_TAG, file.getPath());
                            Log.e(LOG_TAG, file.getName());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        File file = new File(String.valueOf(fileListPath.get(i)));
        Log.e(LOG_TAG, file.getAbsolutePath());

        if (validateJSONFile(file)) {
            loadJSONDataToSecurityPreferences(this.mJSONData.getJSONData());
            Intent intent = new Intent(this, SetupReaderJsonTyresActivity.class);
            startActivity(intent);
        } else {
            this.showToast("N??o foi poss??vel carregar este JSON");
        }
    }

    @AddTrace(name = "validateJSONFile")
    private boolean validateJSONFile(File file) {
        try {

            this.mJSONData = new JSONData(file);
            return true;

        } catch (IOException | NumberFormatException | NullPointerException | JSONException e) {
            Log.e(LOG_TAG, e.toString());
            e.printStackTrace();
            return false;
        }
    }

    @AddTrace(name = "loadJSONDataToSecurityPreferences")
    private void loadJSONDataToSecurityPreferences(HashMap<String, String> mJSONData) {
        for (Map.Entry<String, String> data : mJSONData.entrySet()) {
            Log.e(LOG_TAG, data.getKey() + " -> " + data.getValue());
            mSecurityPreferences.storeString(data.getKey(), data.getValue());
        }
    }


    private String loadDataFromActivity() throws NullPointerException {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String filePath = extras.getString(Constants.FILE_PATH);
            if (filePath != null && !filePath.equals("")) {
                return filePath;
            } else {
                throw new NullPointerException();
            }
        }
        return "";
    }
}