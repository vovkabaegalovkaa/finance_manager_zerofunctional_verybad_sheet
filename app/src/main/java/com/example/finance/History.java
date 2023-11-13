package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class History extends AppCompatActivity {

    DBAdapter dbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbadapter = new DBAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button back = findViewById(R.id.buttonBack);
        Button clear = findViewById(R.id.clearHistory);
        HistoryAdapter adapter = new HistoryAdapter(this);
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        });
        clear.setOnClickListener(v -> {
            dbadapter.DeleteAll();
            HistoryAdapter.getOperations().clear();
            adapter.notifyDataSetChanged();
        });
    }
}