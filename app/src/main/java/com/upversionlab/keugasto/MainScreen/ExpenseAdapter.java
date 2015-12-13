package com.upversionlab.keugasto.mainscreen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upversionlab.keugasto.controller.ExpenseDbController;
import com.upversionlab.keugasto.model.Expense;
import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.model.ExpenseContract.ExpenseColumns;
import com.upversionlab.keugasto.model.ExpenseDbHelper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rborcat on 11/3/2015.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> arrayExpense;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView expenseName;
        public TextView expenseValue;
        public ViewHolder(View v) {
            super(v);
            view = v;
            expenseName = (TextView) v.findViewById(R.id.expense_name);
            expenseValue = (TextView) v.findViewById(R.id.expense_value);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExpenseAdapter(Context context) {
        this.arrayExpense = new ArrayList<>();
        Cursor cursor = ExpenseDbController.readValuesFromDb(context);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String category = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseColumns.COLUMN_NAME_CATEGORY));
            String value = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseColumns.COLUMN_NAME_VALUE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseColumns.COLUMN_NAME_DATE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseColumns.COLUMN_NAME_DESCRIPTION));

            this.arrayExpense.add(new Expense(category, value, date, description));
        }

        Log.d(ExpenseAdapter.class.getSimpleName(), this.arrayExpense.toString());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Expense expense = arrayExpense.get(position);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        //String value = formatter.format(arrayExpense.get(position).value);
        String value = expense.value;
        String name = expense.category;

        holder.expenseName.setText(name);
        holder.expenseValue.setText(value);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayExpense.size();
    }

    // Adds an expense to the array
    public void addExpense(Expense expense) {
        this.arrayExpense.add(expense);

        // refresh
        this.notifyDataSetChanged();
    }
}
