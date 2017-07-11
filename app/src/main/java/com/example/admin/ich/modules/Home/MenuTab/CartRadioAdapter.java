package com.example.admin.ich.modules.Home.MenuTab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.retrofit.model.MenuOption.OptionValue;

import java.util.List;

/**
 * Created by admin on 7/6/2017.
 */
public class CartRadioAdapter extends RecyclerView.Adapter<CartRadioAdapter.SingleCheckViewHolder> {

    private int mSelectedItem = -1;
    private List<OptionValue> mSingleCheckList;
    private Context mContext;
    private AdapterView.OnItemClickListener onItemClickListener;
    private String menuoptionId;
    String optionId;

    public CartRadioAdapter(Context context, List<OptionValue> listItems,String menuoptionid) {
        mContext = context;
        mSingleCheckList = listItems;
         menuoptionId=menuoptionid;

    }

    @Override
    public SingleCheckViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.item_radio, viewGroup, false);
        return new SingleCheckViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(SingleCheckViewHolder viewHolder, final int position) {
        OptionValue item = mSingleCheckList.get(position);
        try {
            viewHolder.setDateToView(item, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mSingleCheckList.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void onItemHolderClick(SingleCheckViewHolder holder) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(null, holder.itemView, holder.getAdapterPosition(), holder.getItemId());
    }



    class SingleCheckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CartRadioAdapter mAdapter;
        private RadioButton mRadio;
        private TextView mText;

        public SingleCheckViewHolder(View itemView, final CartRadioAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;

            mText = (TextView) itemView.findViewById(R.id.text);
            mRadio = (RadioButton) itemView.findViewById(R.id.radio);
            itemView.setOnClickListener(this);
            mRadio.setOnClickListener(this);

        }

        public void setDateToView(OptionValue item, int position) throws Exception {
            mRadio.setChecked(position == mSelectedItem);
            optionId=item.getMenuOptionValueId();

            if (optionId.equals(menuoptionId)) {
                mRadio.setChecked(position == mSelectedItem);
                mRadio.setChecked(true);
                mSelectedItem = getAdapterPosition();
                mAdapter.onItemHolderClick(SingleCheckViewHolder.this);


            }
          //  mRadio.setChecked(position == mSelectedItem);
            mText.setText(item.getValue()+" "+item.getPrice());
        }

        @Override
        public void onClick(View v) {

            menuoptionId="-2";
            mSelectedItem = getAdapterPosition();
            notifyItemRangeChanged(0, mSingleCheckList.size());
            mAdapter.onItemHolderClick(SingleCheckViewHolder.this);
        }
    }

}