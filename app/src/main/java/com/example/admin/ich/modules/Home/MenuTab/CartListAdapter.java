package com.example.admin.ich.modules.Home.MenuTab;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.Database.IchDao;
import com.example.admin.ich.modules.Home.OptionJson;
import com.example.admin.ich.recyclerview.SimpleDividerItemDecoration;
import com.example.admin.ich.retrofit.model.Menu;

import java.util.ArrayList;

/**
 * Created by admin on 6/28/2017.
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private ArrayList<Menu> catList = new ArrayList<>();
    private int rowLayout;
    private Context context;
    int count = 0;
    String total;
    int currentcount;

    IchDao dao;
    double total_price = 0.00;
    ArrayList<OptionJson> sumtotalOption;

    private CartListAdapter.ListAdapterListener mListener;

    public interface ListAdapterListener { // create an interface
        void onClickAtOKButton(int position); // create callback function
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu_name;
       // ImageView menu_image;
        TextView tv_description;
        TextView tv_price;
        TextView tv_plus;
        TextView tv_minus;
        TextView tv_count;
        ImageView add_image;
        RecyclerView rv_menuOption;

        public ViewHolder(View v) {
            super(v);

            tv_menu_name = (TextView) v.findViewById(R.id.menuTitle);
            tv_description = (TextView) v.findViewById(R.id.menudescription);
            tv_price = (TextView) v.findViewById(R.id.menuprice);
        //    menu_image = (ImageView) v.findViewById(R.id.menuimage);
            add_image = (ImageView) v.findViewById(R.id.addImage);
            tv_count = (TextView) v.findViewById(R.id.count);
            tv_plus = (TextView) v.findViewById(R.id.plusbtn);
            tv_minus = (TextView) v.findViewById(R.id.minusbtn);
            rv_menuOption = (RecyclerView) v.findViewById(R.id.rv_menuoption);


        }
    }

    public CartListAdapter(IchDao dao,ArrayList<Menu> nottificationList, int rowLayout, Context context, CartListAdapter.ListAdapterListener mListener) {
        this.catList = nottificationList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mListener = mListener;
        this.dao=dao;

    }

    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        return new CartListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartListAdapter.ViewHolder holder, final int position) {
        // For setting the material Textdrawable

        holder.tv_menu_name.setText(catList.get(position).getMenuName());
        // holder.tv_description.setText(catList.get(position).getMenuDescription());
        holder.tv_count.setText(catList.get(position).getCount());
        if (catList.get(position).getMenuPrice() != null)
            holder.tv_price.setText(catList.get(position).getMenuPrice().substring(0, catList.get(position).getMenuPrice().length() - 2));
      //  Picasso.with(context).load(catList.get(position).getImagePath()).fit().centerCrop().into(holder.menu_image);

        // catList.get(position).setTotalcount("0");
        holder.tv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentcount = Integer.parseInt(holder.tv_count.getText().toString());
                //   catList.get(position).setTotalcount(String.valueOf(currentcount));
                int new_count = currentcount + 1;
                total = String.valueOf(new_count);
                // holder.tv_count.setText(total);
                catList.get(position).setTotalcount(total);
                catList.get(position).setCount(total);

                notifyItemChanged(position);
            }
        });

        String menuId=catList.get(position).getMenuId();

        sumtotalOption = dao.getMenuOptionItemOption(menuId);

        holder.rv_menuOption.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_menuOption.addItemDecoration(new SimpleDividerItemDecoration(context));
        holder.rv_menuOption.setAdapter(new MenuItemOptionListAdapter(sumtotalOption, R.layout.item_menu_option_list, context));


        holder.tv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentcount = Integer.parseInt(holder.tv_count.getText().toString());
                if (currentcount == 0) {

                } else {
                    int new_count = currentcount - 1;
                    String total = String.valueOf(new_count);
                    //  holder.tv_count.setText(total);
                    catList.get(position).setTotalcount(total);
                    catList.get(position).setCount(total);
                    notifyItemChanged(position);
                }
                //   mListener.onClickAtOKButton(position);
            }
        });

        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mListener.onClickAtOKButton(position);

                //  catList.get(position).setMealtimeId("0");

                notifyItemChanged(position);

            }
        });

    }


    @Override
    public int getItemCount() {
        if (catList != null) {
            return catList.size();
        }

        return 0;
    }


}