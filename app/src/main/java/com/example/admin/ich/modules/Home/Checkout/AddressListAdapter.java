package com.example.admin.ich.modules.Home.Checkout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.retrofit.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/9/2017.
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    private List<Address> catList = new ArrayList<>();
    private int rowLayout;
    private Context context;
    String Name;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_address1;
        TextView tv_address2;
        TextView tv_city;
        TextView tv_state;
        TextView tv_country;
        TextView tv_name;



        public ViewHolder(View v) {
            super(v);

            tv_address1 = (TextView) v.findViewById(R.id.tv_address1);
            tv_address2 = (TextView) v.findViewById(R.id.tv_address2);
            tv_city = (TextView) v.findViewById(R.id.tv_city);
            tv_state = (TextView) v.findViewById(R.id.tv_state);
            tv_country = (TextView) v.findViewById(R.id.tv_country);
            tv_name = (TextView) v.findViewById(R.id.tv_name);


        }
    }

    public AddressListAdapter(List<Address> nottificationList, int rowLayout, Context context,String name) {
        this.catList = nottificationList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.Name=name;
    }

    @Override
    public AddressListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AddressListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressListAdapter.ViewHolder holder, int position) {
        // For setting the material Textdrawable

        holder.tv_address1.setText(catList.get(position).getAddress1());
        holder.tv_address2.setText(catList.get(position).getAddress2());
        holder.tv_city.setText(catList.get(position).getCity());
        holder.tv_state.setText(catList.get(position).getState());
        holder.tv_country.setText(catList.get(position).getCountryId());
        holder.tv_name.setText(Name);


    }

    @Override
    public int getItemCount() {
        if (catList != null) {
            return catList.size();
        }

        return 0;
    }
}