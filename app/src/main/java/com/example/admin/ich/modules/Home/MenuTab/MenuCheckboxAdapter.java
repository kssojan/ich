package com.example.admin.ich.modules.Home.MenuTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.retrofit.model.MenuOption.OptionValue;

import java.util.List;

/**
 * Created by admin on 7/4/2017.
 */
public class MenuCheckboxAdapter extends RecyclerView.Adapter<MenuCheckboxAdapter.ViewHolder> {
private List<OptionValue> optionList;
public MenuCheckboxAdapter(List<OptionValue> menuoptions) {
        this.optionList = menuoptions;
        }

@Override
public MenuCheckboxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.item_checkbox, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder viewHolder, int position) {
final int pos = position;
    OptionValue optionValue = optionList.get(position);
        viewHolder.tvName.setText(optionValue.getValue()+" "+optionValue.getPrice());

        viewHolder.chkSelected.setChecked(optionValue.isSelected());
        viewHolder.chkSelected.setTag(optionList.get(position));
        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) {
        CheckBox cb = (CheckBox) v;
    OptionValue emp = (OptionValue) cb.getTag();

        emp.setSelected(cb.isChecked());
    optionList.get(pos).setSelected(cb.isChecked());

/*
        Toast.makeText(
        v.getContext(),
        "Selected Employees: " + cb.getText() + " is "
        + cb.isChecked(), Toast.LENGTH_LONG).show();
*/
        }
        });
        }

@Override
public int getItemCount() {
        return optionList.size();
        }

public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvComp;
    public TextView tvNum;
    public CheckBox chkSelected;

    public ViewHolder(View itemLayoutView) {
        super(itemLayoutView);

        tvName = (TextView) itemLayoutView.findViewById(R.id.empName);

        chkSelected = (CheckBox) itemLayoutView.findViewById(R.id.checkBox);
    }
}
    public List<OptionValue> getOptionList() {
        return optionList;
    }
}