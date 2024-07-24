package com.dns30.vsga2024;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] nama = new String[]{
            "Indonesia",
            "Malaysia",
            "Singapura",
            "Thailand",
            "Filipina",
            "Brunei Darussalam",
            "Timor Leste",
            "Vietnam",
            "Papua Nuguine",
            "Australia",
            "Amerika",
            "English",
            "Afganistan",
            "Afrika Selatan",
            "Aljazair",
            "Arab Saudi",
            "Argentina",
            "Belanda",
            "Bangladesh",
            "Belgia",
            "Brazil",
            "Canada"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nama
        );
        Arrays.sort(nama);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, id) -> Toast.makeText(
                MainActivity.this,
                nama[position] + " diklik",
                Toast.LENGTH_LONG).show());
    }
}