package com.upversionlab.keugasto.category;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.model.categoryModel.Category;

public class ListCategoryActivity extends AppCompatActivity {
    private final static int ADD_CATEGORY_REQUEST_CODE = 1;
    public static final String CATEGORY_EXTRA = "CATEGORY_EXTRA";

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
                pickCategory(category);
            }
        });
        recyclerView.setAdapter(categoryAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_category_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCategoryActivity.this, AddCategoryActivity.class);
                startActivityForResult(intent, ADD_CATEGORY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ADD_CATEGORY_REQUEST_CODE && resultCode == RESULT_OK) {
            Category category = (Category) data.getSerializableExtra(AddCategoryActivity.ADD_CATEGORY_CATEGORY_EXTRA);
            pickCategory(category);
        } else {
            // err
        }

    }

    private void pickCategory(Category category){
        Intent intentResult = new Intent();
        intentResult.putExtra(CATEGORY_EXTRA, category);
        setResult(RESULT_OK, intentResult);
        finish();
    }
}
