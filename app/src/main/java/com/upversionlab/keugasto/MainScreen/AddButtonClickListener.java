package com.upversionlab.keugasto.mainscreen;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.upversionlab.keugasto.expense.AddExpenseActivity;

/**
 * Created by rborcat on 11/3/2015.
 */
public class AddButtonClickListener implements View.OnClickListener {
    private ExpenseAdapter expenseAdapter;
    private Activity activity;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AddButtonClickListener(ExpenseAdapter expenseAdapter, Activity activity) {
        this.expenseAdapter = expenseAdapter;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.activity, AddExpenseActivity.class);
        this.activity.startActivityForResult(intent, 1);
    }
}
