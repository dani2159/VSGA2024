package com.dns30.vsga2024;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnSave, btnGetData;
    private EditText etName;
    private DatabaseHelper databaseHelper;
    private TextView tvResult;
    private ArrayList<String> studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        setupDatabaseHelper();
        setupButtons();
    }

    private void setupUI() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.editTextName);
        btnSave = findViewById(R.id.btnsimpan);
        btnGetData = findViewById(R.id.btngetdata);
        tvResult = findViewById(R.id.tvhasil);
    }

    private void setupDatabaseHelper() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void setupButtons() {
        btnSave.setOnClickListener(v -> saveStudent());

        btnGetData.setOnClickListener(v -> displayStudents());
    }

    private void saveStudent() {
        String studentName = etName.getText().toString();
        if (!studentName.isEmpty()) {
            databaseHelper.addStudent(studentName);
            showToast("Nama " +studentName+ " Berhasil Tersimpan!");
            etName.setText("");
        } else {
            showToast("Nama Tidak Boleh Kosong");
        }
    }

    private void displayStudents() {
        studentsList = databaseHelper.getAllStudents();
        tvResult.setText(""); // Clear the text view before adding new data
        StringBuilder sb = new StringBuilder();
        for (String student : studentsList) {
            sb.append(student).append("\n");
        }
        tvResult.setText(sb.toString());
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

