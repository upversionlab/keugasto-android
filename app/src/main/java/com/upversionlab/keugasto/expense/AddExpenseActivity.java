package com.upversionlab.keugasto.expense;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.category.ListCategoryActivity;
import com.upversionlab.keugasto.mainscreen.MainActivity;
import com.upversionlab.keugasto.model.categoryModel.Category;
import com.upversionlab.keugasto.model.expenseModel.Expense;
import com.upversionlab.keugasto.model.expenseModel.ExpenseDbHelper;

public class AddExpenseActivity extends AppCompatActivity {
    private EditText expenseCategory;
    private EditText expenseValue;
    private EditText expenseDate;
    private EditText expenseDescription;
    private Button addButton;
    private Category selectedCategory = null;
    public static final int CATEGORY_LIST_DIALOG_REQUEST_CODE = 1;
    public static final String CATEGORY_LIST_DIALOG_CATEGORY_EXTRA = "CATEGORY_LIST_DIALOG_CATEGORY_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expenseCategory = (EditText) findViewById(R.id.expense_category);
        expenseCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenseActivity activity = AddExpenseActivity.this;
                Intent intent = new Intent(activity, ListCategoryActivity.class);
                activity.startActivityForResult(intent, CATEGORY_LIST_DIALOG_REQUEST_CODE);
            }
        });
        expenseValue = (EditText) findViewById(R.id.expense_value);
        expenseDate = (EditText) findViewById(R.id.expense_date);
        expenseDescription = (EditText) findViewById(R.id.expense_description);

        addButton = (Button) findViewById(R.id.confirm_expense_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenseActivity activity = AddExpenseActivity.this;
                String value = activity.expenseValue.getText().toString();
                String date = activity.expenseDate.getText().toString();
                String description = activity.expenseDescription.getText().toString();

                long rowId = ExpenseDbHelper.createExpense(activity, selectedCategory, value, date, description);
                // the row ID of the newly inserted row, or -1 if an error occurred
                if (rowId != -1) {
                    Expense expense = new Expense(selectedCategory, value, date, description);
                    Intent intentResult = new Intent();
                    intentResult.putExtra(MainActivity.ADD_EXPENSE_EXPENSE_EXTRA, expense);
                    activity.setResult(RESULT_OK, intentResult);
                } else {
                    activity.setResult(RESULT_CANCELED);
                }

                activity.finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CATEGORY_LIST_DIALOG_REQUEST_CODE && resultCode == RESULT_OK) {
            Category category = (Category) data.getSerializableExtra(CATEGORY_LIST_DIALOG_CATEGORY_EXTRA);
            selectedCategory = category;
            expenseCategory.setText(selectedCategory.name);
        } else {
            // err
        }
    }
}
