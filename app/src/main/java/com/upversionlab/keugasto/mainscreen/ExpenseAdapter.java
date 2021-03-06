package com.upversionlab.keugasto.mainscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upversionlab.keugasto.model.expenseModel.Expense;
import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.model.expenseModel.ExpenseDbHelper;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by rborcat on 11/3/2015.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> arrayExpense;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExpenseAdapter(Context context) {
        this.arrayExpense = ExpenseDbHelper.readExpense(context);

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
        String name = expense.category.name;

        holder.expenseName.setText(name);
        holder.expenseValue.setText(value);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayExpense.size();
    }

    // Adds an expense to the array
    public void addAndUpdate(Expense expense) {
        this.arrayExpense.add(0, expense);

        // refresh
        this.notifyDataSetChanged();
    }

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
}
