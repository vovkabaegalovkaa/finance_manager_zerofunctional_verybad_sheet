package com.example.finance;

import android.content.Context;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private static List<Operation> operations;

    public static List<Operation> getOperations() {
        return operations;
    }

    HistoryAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        operations = new ArrayList<>(Operation.getOperationHistory());
        Collections.reverse(operations);
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Operation operation = operations.get(position);
        holder.sum.setText(Integer.toString(operation.getSum()));
        holder.reason.setText(operation.getReason());
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView sum, reason;
        ViewHolder(View view){
            super(view);
            sum = view.findViewById(R.id.sum);
            reason = view.findViewById(R.id.reason);
        }
    }
}
