package com.upversionlab.keugasto.MainScreen;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.upversionlab.keugasto.AddScreen.AddExpenseActivity;
import com.upversionlab.keugasto.Expense;

/**
 * Created by rborcat on 11/3/2015.
 */
public class AddButtonClickListener implements View.OnClickListener {
    private ExpenseAdapter expenseAdapter;
    private Activity activity;
    private int count = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AddButtonClickListener(ExpenseAdapter expenseAdapter, Activity activity) {
        this.expenseAdapter = expenseAdapter;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
//        String name = "test - " + this.count;
//        float value = this.count;
//        Expense expense = new Expense(name, value);
//        this.expenseAdapter.addExpense(expense);
//        this.count++;

        Intent intent = new Intent(this.activity, AddExpenseActivity.class);
        this.activity.startActivity(intent);

//        CategoryDialog cdd = new CategoryDialog(this.activity);
//        cdd.show();
    }
}
