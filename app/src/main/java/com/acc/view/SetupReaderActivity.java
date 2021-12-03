package com.acc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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

public class SetupReaderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = "SetupReader";
    private final List<String> folderList = new ArrayList<>();
    private ActivitySetupReaderBinding binding;
    private long mLastClickTime = 0;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetupReaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        File root = new File(String.valueOf(Environment.getExternalStorageDirectory()));
        listFolders(root);

        if (folderList.isEmpty()) {
            this.showToast("Não foi encontrado nenhuns ficheiros JSON");
        } else {
            ArrayAdapter<String> directoryList = new ArrayAdapter<>(this, R.layout.list_files, folderList);
            binding.listFolders.setAdapter(directoryList);
        }
        binding.listFolders.setOnItemClickListener(this);
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