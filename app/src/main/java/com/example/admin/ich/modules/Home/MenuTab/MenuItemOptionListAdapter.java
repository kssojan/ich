package com.example.admin.ich.modules.Home.MenuTab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.Home.OptionJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/10/2017.
 */
public class MenuItemOptionListAdapter extends RecyclerView.Adapter<MenuItemOptionListAdapter.ViewHolder> {

    private List<OptionJson> catList = new ArrayList<>();
    private int rowLayout;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_OptionName;
        TextView tv_optionPrice;


        public ViewHolder(View v) {
            super(v);

            tv_OptionName = (TextView) v.findViewById(R.id.menuOptionTitle);
            tv_optionPrice = (TextView) v.findViewById(R.id.menuOptionPrice);


        }
    }

    public MenuItemOptionListAdapter(List<OptionJson> nottificationList, int rowLayout, Context context) {
        this.catList = nottificationList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MenuItemOptionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MenuItemOptionListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuItemOptionListAdapter.ViewHolder holder, int position) {
        // For setting the material Textdrawable

        holder.tv_OptionName.setText(catList.get(position).getValue());
        holder.tv_optionPrice.setText(catList.get(position).getOption_price());


    }

    @Override
    public int getItemCount() {
        if (catList != null) {
            return catList.size();
        }

        return 0;
    }
}