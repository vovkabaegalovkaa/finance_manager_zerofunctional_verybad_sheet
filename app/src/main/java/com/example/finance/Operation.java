package com.example.finance;

import java.util.ArrayList;

public class Operation {
    private static ArrayList<Operation> operationHistory= new ArrayList<>();


    private int balance;
    private int sum;
    private String reason;

    public Operation(int balance, int sum, String reason) {
        this.balance = balance;
        this.sum = sum;
        this.reason = reason;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static ArrayList<Operation> getOperationHistory() {
        return operationHistory;
    }

    public static void addOperationHistory(int balance, int sum, String reason) {
        Operation.operationHistory.add(new Operation(balance, sum, reason));


    }
}
