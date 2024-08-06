package com.dns30.vsga2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        initializeListView();
    }

    private void setupUI() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeListView() {
        listView = findViewById(R.id.listView);
        adapter = new MainAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshCatatanList();
    }

    private void refreshCatatanList() {
        adapter.clear();
        adapter.addAll(loadCatatan());
    }

    private ArrayList<Catatan> loadCatatan() {
        ArrayList<Catatan> catatanList = new ArrayList<>();
        File directory = new File(getFilesDir(), "catatan");
        File[] files = directory.listFiles();

        if (files == null) return catatanList;

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault());
        for (File file : files) {
            String namaFile = file.getName();
            String timestamp = formatter.format(new Date(file.lastModified()));
            catatanList.add(new Catatan(namaFile, timestamp));
        }
        return catatanList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_tambah) {
            startTambahActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startTambahActivity() {
        Intent intent = new Intent(this, TambahActivity.class);
        startActivity(intent);
    }
}
