package com.upversionlab.keugasto.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.mainscreen.MainActivity;
import com.upversionlab.keugasto.model.categoryModel.Category;
import com.upversionlab.keugasto.model.categoryModel.CategoryDbHelper;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText categoryName;
    private EditText categoryValue;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoryName = (EditText) findViewById(R.id.category_name);
        categoryValue = (EditText) findViewById(R.id.category_value);

        addButton = (Button) findViewById(R.id.confirm_category_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCategoryActivity activity = AddCategoryActivity.this;
                String name = activity.categoryName.getText().toString();
                String value = activity.categoryValue.getText().toString();

                long rowId = CategoryDbHelper.createCategory(activity, name, value);
                // the row ID of the newly inserted row, or -1 if an error occurred
                if (rowId != -1) {
                    Category category = new Category(name, value);
                    Intent intentResult = new Intent();
                    // Change the put extra to something that will come from AddExpenseActivity
                    intentResult.putExtra(MainActivity.ADD_EXPENSE_EXPENSE_EXTRA, category);
                    activity.setResult(RESULT_OK, intentResult);
                } else {
                    activity.setResult(RESULT_CANCELED);
                }

                activity.finish();
            }
        });
    }
}
