package com.upversionlab.keugasto.balance;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upversionlab.keugasto.R;
import com.upversionlab.keugasto.model.categoryModel.Category;
import com.upversionlab.keugasto.model.categoryModel.CategoryDbHelper;
import com.upversionlab.keugasto.model.expenseModel.Expense;
import com.upversionlab.keugasto.model.expenseModel.ExpenseDbHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rborcat on 4/19/2016.
 */
public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.ViewHolder> {
    private List<Category> categoryList;
    private Map<Integer, List<Expense>> categoryExpenses = new HashMap<>();

    // Provide a suitable constructor (depends on the kind of dataset)
    public BalanceAdapter(Context context) {
        this.categoryList = CategoryDbHelper.readCategory(context);
        for(Expense expense: ExpenseDbHelper.readExpense(context)) {
            List<Expense> expenseList = this.categoryExpenses.get(expense.category.id);
            if(expenseList == null) {
                expenseList = new ArrayList<Expense>();
            }
            expenseList.add(expense);
            this.categoryExpenses.put(expense.category.id, expenseList);
        }

        Log.d(BalanceAdapter.class.getSimpleName(), this.categoryList.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.balance, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        List<Expense> expenseList = categoryExpenses.get(category.id);
        Float balance = 0F;
        for(Expense expense: expenseList) {
            balance += Float.parseFloat(expense.value);
        }
        String name = category.name;

        holder.categoryName.setText(name);
        holder.categoryBalance.setText(balance.toString());
        ViewHolder.ExpenseAdapter adapter = new ViewHolder.ExpenseAdapter(expenseList);
        holder.expensesView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView categoryName;
        public TextView categoryBalance;
        public RecyclerView expensesView;
        public ViewHolder(View v) {
            super(v);
            categoryName = (TextView) v.findViewById(R.id.category_name);
            categoryBalance = (TextView) v.findViewById(R.id.category_balance);

            expensesView = (RecyclerView) v.findViewById(R.id.expenses);
            expensesView.setHasFixedSize(true);
            expensesView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        }

        public static class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>{
            private List<Expense> expenseList;
            public ExpenseAdapter(List<Expense> expenseList) {
                this.expenseList = expenseList;
            }

            @Override
            public ExpenseAdapter.ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // create a new view
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.expense, parent, false);
                // set the view's size, margins, paddings and layout parameters
                ExpenseViewHolder evh = new ExpenseViewHolder(v);
                return evh;
            }

            @Override
            public void onBindViewHolder(ExpenseAdapter.ExpenseViewHolder holder, int position) {
                Expense expense = expenseList.get(position);
                holder.expenseName.setText(expense.category.name);
                holder.expenseValue.setText(expense.value);
            }

            @Override
            public int getItemCount() {
                return this.expenseList.size();
            }

            public static class ExpenseViewHolder extends RecyclerView.ViewHolder{
                public TextView expenseName;
                public TextView expenseValue;
                public ExpenseViewHolder(View itemView) {
                    super(itemView);
                    expenseName = (TextView) itemView.findViewById(R.id.expense_name);
                    expenseValue = (TextView) itemView.findViewById(R.id.expense_value);
                }
            }
        }
    }
}
