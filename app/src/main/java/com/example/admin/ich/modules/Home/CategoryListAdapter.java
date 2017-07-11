package com.example.admin.ich.modules.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.retrofit.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 5/11/2017.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<Category> catList = new ArrayList<>();
    private int rowLayout;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Category_name;


        public ViewHolder(View v) {
            super(v);

            tv_Category_name = (TextView) v.findViewById(R.id.tv_categry_name);


        }
    }

    public CategoryListAdapter(List<Category> nottificationList, int rowLayout, Context context) {
        this.catList = nottificationList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // For setting the material Textdrawable

        holder.tv_Category_name.setText(catList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        if (catList != null) {
            return catList.size();
        }

        return 0;
    }
}