package com.upversionlab.keugasto.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.model.categoryModel.Category;
import com.upversionlab.keugasto.model.categoryModel.CategoryDbHelper;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by rborcat on 3/8/2016.
 */
public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> arrayCategories;
    private OnCategoryPickListener listener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(Context context) {
        this.arrayCategories = CategoryDbHelper.readCategory(context);

        Log.d(CategoryAdapter.class.getSimpleName(), this.arrayCategories.toString());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category category = arrayCategories.get(position);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        //String value = formatter.format(arrayExpense.get(position).value);
        String name = category.name;

        holder.categoryName.setText(name);
        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onCategoryPick(category);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayCategories.size();
    }

    // Adds an category to the array
    public void addAndUpdate(Category category) {
        this.arrayCategories.add(0, category);

        // refresh
        this.notifyDataSetChanged();
    }

    public void setOnCategoryPickListener(OnCategoryPickListener listener) {
        this.listener = listener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView categoryName;
        public ViewHolder(View v) {
            super(v);
            view = v;
            categoryName = (TextView) v.findViewById(R.id.category_name);
        }
    }

    public interface OnCategoryPickListener {
        void onCategoryPick(Category category);
    }
}
