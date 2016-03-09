package com.upversionlab.keugasto.category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.expense.AddExpenseActivity;
import com.upversionlab.keugasto.mainscreen.MainActivity;
import com.upversionlab.keugasto.model.categoryModel.Category;

public class ListCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.category_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);
        categoryAdapter.setOnCategoryPickListener(new CategoryAdapter.OnCategoryPickListener(){
            public void onCategoryPick(Category category) {
                Intent intentResult = new Intent();
                intentResult.putExtra(AddExpenseActivity.CATEGORY_LIST_DIALOG_CATEGORY_EXTRA, category);
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
        recyclerView.setAdapter(categoryAdapter);
    }
}
