package com.upversionlab.keugasto.addscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.model.ExpenseDbHelper;

public class AddExpenseActivity extends AppCompatActivity {
    private EditText expenseCategory;
    private EditText expenseValue;
    private EditText expenseDate;
    private EditText expenseDescription;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expenseCategory = (EditText) findViewById(R.id.expense_category);
        expenseValue = (EditText) findViewById(R.id.expense_value);
        expenseDate = (EditText) findViewById(R.id.expense_date);
        expenseDescription = (EditText) findViewById(R.id.expense_description);

        addButton = (Button) findViewById(R.id.confirm_expense_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenseActivity activity = AddExpenseActivity.this;
                String category = activity.expenseCategory.getText().toString();
                String value = activity.expenseValue.getText().toString();
                String date = activity.expenseDate.getText().toString();
                String description = activity.expenseDescription.getText().toString();

                long newRowId = ExpenseDbHelper.createExpense(activity, category, value, date, description);
            }
        });
    }
}
