package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBAdapter dbAdapter;
    TextView showBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBalance = findViewById(R.id.balance);
        Button add = findViewById(R.id.buttonAdd);
        Button disAdd = findViewById(R.id.buttonNoAdd);
        Button history = findViewById(R.id.buttonHistory);
        dbAdapter = new DBAdapter(this);
        if(Operation.getOperationHistory().size() == 0) {
            dbAdapter.getAll();
        }
        if(Operation.getOperationHistory().size()==0){
            showBalance.setText("0 byn");
        }
        else {
            showBalance.setText(Integer.toString(Operation.getOperationHistory().get(Operation.getOperationHistory().size() - 1).getBalance())+" byn");
        }
        add.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.addoperationdialog);
            EditText editSum = dialog.findViewById(R.id.editSum);
            Button confirm = dialog.findViewById(R.id.confirm_button);
            confirm.setOnClickListener(v1 -> {
                if (TextUtils.isEmpty(editSum.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
                } else {
                    if(Operation.getOperationHistory().size() != 0) {
                        showBalance.setText(Operation.getOperationHistory().get(Operation.getOperationHistory().size() - 1).getBalance() + Integer.parseInt(editSum.getText().toString()) + " byn");
                        dbAdapter.addOne(Operation.getOperationHistory().get(Operation.getOperationHistory().size() - 1).getBalance() + Integer.parseInt(editSum.getText().toString()), Integer.parseInt(editSum.getText().toString()), "Money added");
                    }
                    else{
                        dbAdapter.addOne(Integer.parseInt(editSum.getText().toString()), Integer.parseInt(editSum.getText().toString()), "Money added");
                        showBalance.setText(Integer.parseInt(editSum.getText().toString()) + " byn");
                    }
                    dialog.dismiss();

                }
            });
            dialog.show();
        });
        disAdd.setOnClickListener(v -> {
            if(Operation.getOperationHistory().size()==0)
                Toast.makeText(this,"Cant remove!",Toast.LENGTH_SHORT).show();
            else {
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.removeoperationdialog);
                EditText editSum = dialog.findViewById(R.id.editSum);
                EditText editReason = dialog.findViewById(R.id.editReason);
                Button confirm = dialog.findViewById(R.id.confirm_button);
                confirm.setOnClickListener(v1 -> {
                    if (TextUtils.isEmpty(editSum.getText().toString()) || TextUtils.isEmpty(editReason.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
                    } else {
                        if(Operation.getOperationHistory().get(Operation.getOperationHistory().size() - 1).getBalance() - Integer.parseInt(editSum.getText().toString())<0){
                            Toast.makeText(this,"Try again!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            showBalance.setText(Operation.getOperationHistory().get(Operation.getOperationHistory().size() - 1).getBalance() - Integer.parseInt(editSum.getText().toString()) + " byn");
                            dbAdapter.addOne(Operation.getOperationHistory().get(Operation.getOperationHistory().size() - 1).getBalance() - Integer.parseInt(editSum.getText().toString()), Integer.parseInt(editSum.getText().toString()) * (-1), editReason.getText().toString());
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
        history.setOnClickListener(v -> {
            if(Operation.getOperationHistory().size()==0){
                Toast.makeText(this,"History is empty!", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(this, History.class);
                startActivity(intent);
                finish();
            }
        });
    }
}