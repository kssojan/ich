package com.example.admin.ich.modules.Home.MenuTab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.Database.IchDao;
import com.example.admin.ich.modules.Home.OptionJson;
import com.example.admin.ich.retrofit.ApiClient;
import com.example.admin.ich.retrofit.ApiInterface;
import com.example.admin.ich.retrofit.model.Menu;
import com.example.admin.ich.retrofit.model.MenuOption.MenuOptionResponse;
import com.example.admin.ich.retrofit.model.MenuOption.OptionValue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class MenuOptionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @NonNull
    @InjectView(R.id.radioRecycle)
    RecyclerView radioRecycle;
    @NonNull
    @InjectView(R.id.checkboxRecycle)
    RecyclerView checkboxRecycle;
    @NonNull
    @InjectView(R.id.spinnerSelect)
    Spinner spinnerSelect;
    @NonNull
    @InjectView(R.id.input_select)
    TextView input_select;
    @NonNull
    @InjectView(R.id.frameSelect)
    FrameLayout frameSelect;
    @NonNull
    @InjectView(R.id.btnAddCartOptions)
    Button btnAddCartOptions;

    private List<OptionValue> menuListRadio = new ArrayList<>();
    private List<OptionValue> menuListCheckbox = new ArrayList<>();
    private List<OptionValue> menuListSelect = new ArrayList<>();
    private List<OptionJson> menuListOption = new ArrayList<>();
    private List<OptionJson> menuListOptionRadio = new ArrayList<>();
    private List<OptionJson> menuListOptionSelect = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RadioAdapter radioAdapter;
    String menuoptions, count, menuId, menu_option_id_checkbox, option_id_checkbox, option_name_checkbox, display_type_checkbox;
    String menu_option_id_radio, option_id_radio, option_name_radio, display_type_radio;
    String menu_option_id_select, option_id_select, option_name_select, display_type_select;
    String menuname, menupic, menu_price;

    String checkboxId = "";
    String radioId = "-1";
    String selectId = "-1";

    IchDao dao;
    ArrayList<Menu> product;
    ArrayList<String> menusList, menuTotalList, menuCountList;
    int totalCartCount;
    double total_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_option);
        ButterKnife.inject(this);
        dao = new IchDao(getApplicationContext());
        menusList = new ArrayList<>();
        menuTotalList = new ArrayList<>();
        menuCountList = new ArrayList<>();
        Intent intent = getIntent();
        menuId = intent.getExtras().getString("menuId");
        count = intent.getExtras().getString("count");
        menuoptions = intent.getExtras().getString("menuOptions");
        menuname = intent.getExtras().getString("menuName");
        menupic = intent.getExtras().getString("menuPic");
        menu_price = intent.getExtras().getString("menuPrice");
        getmenus(menuId);


    }

    public void getmenus
            (String id) {

        final ProgressDialog progressDialog = new ProgressDialog(MenuOptionActivity.this);

        progressDialog.setMessage("loading");
        progressDialog.show();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MenuOptionResponse> call = apiService.MenuOptionList(id);
        call.enqueue(new Callback<MenuOptionResponse>() {
            @Override
            public void onResponse(Call<MenuOptionResponse> call, retrofit2.Response<MenuOptionResponse> response) {
                progressDialog.hide();
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        if (menuoptions.contains("radio")) {
                            menuListRadio = response.body().getMenuOptions().getRadio().getOptionValues();
                            /*checkboxRecycle.setHasFixedSize(true);
                            checkboxRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            mAdapter = new MenuCheckboxAdapter(menuListRadio);
                            checkboxRecycle.setAdapter(mAdapter);

                            menu_option_id_checkbox = response.body().getMenuOptions().getRadio().getMenuOptionId();
                            option_id_checkbox = response.body().getMenuOptions().getRadio().getOptionId();
                            option_name_checkbox = response.body().getMenuOptions().getRadio().getOptionName();
                            display_type_checkbox = response.body().getMenuOptions().getRadio().getDisplayType();*/

                            menu_option_id_radio = response.body().getMenuOptions().getRadio().getMenuOptionId();
                            option_id_radio = response.body().getMenuOptions().getRadio().getOptionId();
                            option_name_radio = response.body().getMenuOptions().getRadio().getOptionName();
                            display_type_radio = response.body().getMenuOptions().getRadio().getDisplayType();


                            // radioRecycle.setAdapter(new RadioAdapter(getApplicationContext(), menuListRadio));
                            /*radioAdapter = new RadioAdapter(getApplicationContext(),menuListRadio);
                            radioRecycle.setAdapter(radioAdapter);
                            radioRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));*/

                            radioAdapter = new RadioAdapter(getApplicationContext(), menuListRadio);
                            radioRecycle.setAdapter(radioAdapter);
                            radioRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            radioAdapter.setOnItemClickListener(MenuOptionActivity.this);


                           /* ArrayList<String> manufactureList = new ArrayList<String>();
                            // manufactureList.add(0, "Select Country");
                            for (int i = 0; i < menuListRadio.size(); i++) {

                                manufactureList.add(menuListRadio.get(i).getValue());
                            }
                            // Creating adapter for spinner service
                            ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MenuOptionActivity.this, android.R.layout.simple_spinner_item, manufactureList);
                            mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            // attaching data adapter to spinner;
                            spinnerSelect.setAdapter(mAdapter);


                            input_select.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    spinnerSelect.performClick();
                                }
                            });
                            spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    input_select.setText(spinnerSelect.getSelectedItem().toString());
                                    if (position != 0) {
                                        //  countryId = listVehicle.get(position - 1).getCountryId();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });*/
                        }
                        if (menuoptions.contains("checkbox")) {
                            menuListCheckbox = response.body().getMenuOptions().getCheckbox().getOptionValues();
                            checkboxRecycle.setHasFixedSize(true);
                            checkboxRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            mAdapter = new MenuCheckboxAdapter(menuListCheckbox);
                            checkboxRecycle.setAdapter(mAdapter);

                            menu_option_id_checkbox = response.body().getMenuOptions().getCheckbox().getMenuOptionId();
                            option_id_checkbox = response.body().getMenuOptions().getCheckbox().getOptionId();
                            option_name_checkbox = response.body().getMenuOptions().getCheckbox().getOptionName();
                            display_type_checkbox = response.body().getMenuOptions().getCheckbox().getDisplayType();
                        }
                        if (menuoptions.contains("select")) {
                            frameSelect.setVisibility(View.VISIBLE);
                            menuListSelect = response.body().getMenuOptions().getSelect().getOptionValues();

                            menu_option_id_select = response.body().getMenuOptions().getSelect().getMenuOptionId();
                            option_id_select = response.body().getMenuOptions().getSelect().getOptionId();
                            option_name_select = response.body().getMenuOptions().getSelect().getOptionName();
                            display_type_select = response.body().getMenuOptions().getSelect().getDisplayType();

                            final ArrayList<String> dropdownList = new ArrayList<String>();
                            dropdownList.add(0, "Select Country");
                            for (int i = 0; i < menuListSelect.size(); i++) {

                                dropdownList.add(menuListSelect.get(i).getValue() +" " +menuListSelect.get(i).getPrice());
                            }
                            // Creating adapter for spinner service
                            ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MenuOptionActivity.this, android.R.layout.simple_spinner_item, dropdownList);
                            mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            // attaching data adapter to spinner;
                            spinnerSelect.setAdapter(mAdapter);


                            input_select.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    spinnerSelect.performClick();
                                }
                            });
                            spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position != 0) {
                                        input_select.setText(spinnerSelect.getSelectedItem().toString() );
                                        menuListOptionSelect.clear();
                                        OptionJson optionJson = new OptionJson();
                                        optionJson.setCount(count);
                                        optionJson.setMenu_id(menuId);
                                        optionJson.setMenu_option_id(menu_option_id_select);
                                        optionJson.setOption_id(option_id_select);
                                        optionJson.setOption_name(option_name_select);
                                        optionJson.setDisplay_type(display_type_select);
                                        optionJson.setOption_value_id(menuListSelect.get(position-1).getOptionValueId());
                                        optionJson.setMenu_option_value_id(menuListSelect.get(position-1).getMenuOptionValueId());
                                        optionJson.setValue(menuListSelect.get(position-1).getValue());
                                        optionJson.setOption_price(menuListSelect.get(position-1).getPrice());
                                        double total_price = (Integer.parseInt(count) * Double.parseDouble(menuListSelect.get(position-1).getPrice().substring(3).trim()));
                                        optionJson.setOption_total(String.valueOf(total_price));
                                        menuListOptionSelect.add(optionJson);

                                    }
                                    else {
                                        input_select.setText(spinnerSelect.getSelectedItem().toString() );
                                        menuListOptionSelect.clear();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        } else
                            frameSelect.setVisibility(View.GONE);


                    }


                }
                if (response.code() == 404)
                    Toast.makeText(getApplicationContext(), "No menus found", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MenuOptionResponse> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();

            }
        });
    }


    @OnClick(R.id.btnAddCartOptions)
    public void AddCart() {
        menuListOption.clear();
        if (menuoptions.contains("checkbox")) {
            List<OptionValue> stList = ((MenuCheckboxAdapter) mAdapter).getOptionList();

            for (int i = 0; i < stList.size(); i++) {
                OptionValue optionCheck = stList.get(i);
                if (optionCheck.isSelected()) {

                    checkboxId = checkboxId + "," + optionCheck.getOptionValueId() + "";

                    OptionJson optionJson = new OptionJson();
                    optionJson.setCount(count);
                    optionJson.setMenu_id(menuId);
                    optionJson.setMenu_option_id(menu_option_id_checkbox);
                    optionJson.setOption_id(option_id_checkbox);
                    optionJson.setOption_name(option_name_checkbox);
                    optionJson.setDisplay_type(display_type_checkbox);
                    optionJson.setOption_value_id(optionCheck.getOptionValueId());
                    optionJson.setMenu_option_value_id(optionCheck.getMenuOptionValueId());
                    optionJson.setValue(optionCheck.getValue());
                    optionJson.setOption_price(optionCheck.getPrice());
                    double total_price = (Integer.parseInt(count) * Double.parseDouble(optionCheck.getPrice()));
                    optionJson.setOption_total(String.valueOf(total_price));
                    menuListOption.add(optionJson);
                    //   Toast.makeText(MenuOptionActivity.this, optionCheck.getOptionValueId(), Toast.LENGTH_SHORT).show();
                }


            }
            if (!Objects.equals(checkboxId, ""))
                checkboxId = checkboxId.substring(1);


            List<String> myList = new ArrayList<String>(Arrays.asList(checkboxId.split(",")));



        }
        if (menuListOptionRadio != null)
            menuListOption.addAll(menuListOptionRadio);
        if (menuListOptionSelect != null)
            menuListOption.addAll(menuListOptionSelect);

       /* Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(menuListOption).getAsJsonArray();
        Toast.makeText(MenuOptionActivity.this, myCustomArray.toString(), Toast.LENGTH_SHORT).show();*/


        DecimalFormat df = new DecimalFormat("####0.00");
        product = dao.getCartItem(menuId);
        for (Menu i : product) {
            menusList.add(i.getMenuId());
            menuTotalList.add(i.getTotalcount());
            menuCountList.add(i.getCount());
        }
        if (menuCountList.toString() != "[]") {
            //   String count = menuCountList.toString();
            totalCartCount = Integer.parseInt(count);
            String total = menuTotalList.toString();
            total_price = (Integer.parseInt(count) * Float.parseFloat(menu_price));
            // Toast.makeText(getActivity(), count+" "+total, Toast.LENGTH_SHORT).show();
            if (totalCartCount != 0)
                dao.updateCart(menuId, String.valueOf(totalCartCount), String.valueOf(df.format(total_price)));
            else
                dao.deleteMenu(menuId);
        } else {
            totalCartCount = Integer.parseInt(count);
            total_price = (Integer.parseInt(count) * Double.parseDouble(menu_price));
            if (totalCartCount != 0)
                dao.insetCart(menuId, menuname, menupic, String.valueOf(totalCartCount),
                        menu_price, menuoptions, String.valueOf(0), String.valueOf(df.format(total_price)));
        }

        insertDb();




        Intent intent = new Intent();
        setResult(2, intent);
        finish();


    }

    private void insertDb() {
        dao.insertOptionCart(menuListOption);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        menuListOptionRadio.clear();
        //radioId = menuListRadio.get(position).getOptionValueId();
        // Toast.makeText(MenuOptionActivity.this, position + " - " + menuListRadio.get(position).getValue(), Toast.LENGTH_SHORT).show();
        OptionJson optionJson = new OptionJson();
        optionJson.setCount(count);
        optionJson.setMenu_id(menuId);
        optionJson.setMenu_option_id(menu_option_id_radio);
        optionJson.setOption_id(option_id_radio);
        optionJson.setOption_name(option_name_radio);
        optionJson.setDisplay_type(display_type_radio);
        optionJson.setOption_value_id(menuListRadio.get(position).getOptionValueId());
        optionJson.setMenu_option_value_id(menuListRadio.get(position).getMenuOptionValueId());
        optionJson.setValue(menuListRadio.get(position).getValue());
        optionJson.setOption_price(menuListRadio.get(position).getPrice());
        double total_price = (Integer.parseInt(count) * Double.parseDouble(menuListRadio.get(position).getPrice().trim()));
        optionJson.setOption_total(String.valueOf(total_price));
        menuListOptionRadio.add(optionJson);

    }


/*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("optionCheck", "-2");
        intent.putExtra("radioId", "-2");
        intent.putExtra("selectId", "-2");
        setResult(2, intent);
        finish();
    }
*/

}
