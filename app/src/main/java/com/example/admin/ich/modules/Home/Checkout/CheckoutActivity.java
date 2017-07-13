package com.example.admin.ich.modules.Home.Checkout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.AppController;
import com.example.admin.ich.modules.BaseActivity;
import com.example.admin.ich.modules.Database.IchDao;
import com.example.admin.ich.recyclerview.RecyclerItemClickListener;
import com.example.admin.ich.recyclerview.SimpleDividerItemDecoration;
import com.example.admin.ich.retrofit.ApiClient;
import com.example.admin.ich.retrofit.ApiInterface;
import com.example.admin.ich.retrofit.model.Address;
import com.example.admin.ich.retrofit.model.AddressResponse;
import com.example.admin.ich.retrofit.model.Menu;
import com.example.admin.ich.retrofit.model.MenuOption.DeliveryAddressPost;
import com.example.admin.ich.retrofit.model.SignupResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class CheckoutActivity extends BaseActivity {
    private Context mContext;
    private Animation.AnimationListener mAnimationListener;
    @InjectView(R.id.container)
    ViewFlipper viewFlipper;
    @InjectView(R.id.proceed)
    Button btn_proceedtopayment;
    @InjectView(R.id.orderpost_scroll)
    ImageView orderpost_scroll;
    @InjectView(R.id.header_text)
    TextView textHeader;
    @InjectView(R.id.rv_deliveryAddress)
    RecyclerView rvDeliveryAddress;
    @InjectView(R.id.coordinatorLayout)
    RelativeLayout rootView;
    @InjectView(R.id.tv_name)
    TextView tv_name;
    @InjectView(R.id.tv_address1)
    TextView tv_address1;
    @InjectView(R.id.tv_address2)
    TextView tv_address2;
    @InjectView(R.id.tv_city)
    TextView tv_city;
    @InjectView(R.id.tv_state)
    TextView tv_state;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    IchDao dao;
    ArrayList<Menu> CartItems;
    ArrayList<Menu> CartOptionItems;
    ArrayList<Menu> CartOptionItemsCheck;
    private List<Address> addressList = new ArrayList<>();
    private List<Menu> menuListOption = new ArrayList<>();
    private List<Menu> menuListOptionArray = new ArrayList<>();
    private List<Menu> menuListOptionTotal = new ArrayList<>();
    private List<DeliveryAddressPost> deliveryAddressList = new ArrayList<>();
    boolean selectedAddress = false;

    ArrayList<Double>  menuTotalOption;
    ArrayList<String> menuOptionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.inject(this);
        //final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("ICH Menu Order");
        menuTotalOption = new ArrayList<>();
        menuOptionId = new ArrayList<>();
        mContext = this;

        dao = new IchDao(CheckoutActivity.this);
        mAnimationListener = new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                // animation started event
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                // TODO animation stopped event
            }
        };

        getdelivryAddress();



        CartItems = dao.getAllCartItem();
        for (Menu i : CartItems) {

            Menu optionJson = new Menu();
            optionJson.setMenuId(i.getMenuId());
            optionJson.setTotalcount(i.getTotalcount());
            optionJson.setCount(i.getCount());
            optionJson.setMenuName(i.getMenuName());
            optionJson.setMenuPrice(i.getMenuPrice());
            optionJson.setComment("hello");


            try {
                HashMap<String, List<Map<String, String>>> map2 = new HashMap<String, List<Map<String, String>>>();
                HashMap<String, String> map1 = new HashMap<String, String>();
                //HashMap<String, String> map1 = new HashMap<String, String>();
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("name", i.getMenuName()); // Set the first name/pair
                jsonObj.put("qty", i.getCount());

                map1.put("name", i.getMenuName()); // Set the first name/pair
                map1.put("qty", i.getCount());

                /*JSONObject jsonAdd = new JSONObject(); // we need another object to store the address
                jsonAdd.put("address", person.getAddress().getAddress());
                jsonAdd.put("city", person.getAddress().getCity());
                jsonAdd.put("state", person.getAddress().getState());

                // We add the object to the main object
                jsonObj.put("address", jsonAdd);*/

                // and finally we add the phone number
                // In this case we need a json array to hold the java list
                JSONArray jsonArr = new JSONArray();
                ArrayList<HashMap<String, String>> bathroomList = new ArrayList<HashMap<String, String>>();
                List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();


                CartOptionItems = dao.getAllCartOptionItem(i.getMenuId());
                for (Menu j : CartOptionItems) {

                    menuTotalOption.add(Double.valueOf(j.getValuePriceTotal()));


                    menuOptionId.add(j.getMenuOptionId());
                    /*JSONObject pnObj = new JSONObject();
                    pnObj.put("value_id", j.getValueId());
                    pnObj.put("value_name", j.getValueName());
                    jsonArr.put(pnObj);*/

//                    CartOptionItemsCheck = dao.getAllCartOptionItemCheck(i.getMenuId(),j.getMenuOptionId());
//                    for (Menu k : CartOptionItemsCheck) {

                            HashMap<String, String> map5 = new HashMap<String, String>();
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("value_id", j.getValueId());
                            map.put("value_name", j.getValueName());
                            map.put("value_price", j.getValuePrice());
                            map.put("menu_option_id", j.getMenuOptionId());

                            jsonArr.put(map1);


                            listOfMaps.add(map);

                            map2.put(j.getMenuOptionId(), listOfMaps);


                    //    }


                    Menu optionJson1 = new Menu();
//                optionJson1.setValueId(j.getValueId());
//                optionJson1.setValueName(j.getValueName());
//                optionJson1.setValuePrice(j.getValuePrice());
//                HashMap<String, ArrayList<String>> dataToPass = new LinkedHashMap<>();
//                dataToPass.put("value_id", j.getValueId());
//                dataToPass.put("value_name", j.getValueName());
//                dataToPass.put("value_price", j.getValuePrice());
//                dataToPassOption.put("options", dataToPass);
//                Log.d("arrrryy", dataToPassOption.toString());
//                optionJson.setDataToPass(dataToPassOption);


                }


                DecimalFormat df = new DecimalFormat("####0.00");
                int j;
                double sumOption = 0.00;
                for (j = 0; j < menuTotalOption.size(); j++)
                    sumOption += menuTotalOption.get(j);

                Log.d("total", menuTotalOption.toString());
                String total = i.getTotalcount();
                double totalmenuprice = 0.00;
                totalmenuprice=Double.parseDouble(total);

                String subtotal = String.valueOf(df.format(totalmenuprice + sumOption));
                optionJson.setValuePriceTotal(subtotal);


                optionJson.setDataToPass(map2);
                // jsonObj.put("Options", jsonArr);
                //  map1.put("Options", map1);
                //listOfMaps.add(map1);
                //jsonObj.put("Options",listOfMaps);
                // listOfMaps.add(map1);

                // menuListOption.add(jsonObj);
                menuListOption.add(optionJson);

            } catch (JSONException e) {
                e.printStackTrace();
            }


//menuListOption.addAll(menuListOptionArray);

            // menuListOption.add(optionJson);


            /*menuListOption.addAll(menuListOptionArray);
            menuListOptionTotal.addAll(menuListOption);*/


//            Menu optionJson1 = new Menu();
//            optionJson1.setMenuId(i.getMenuId());
//            optionJson1.setValueId(i.getValueId());
//            optionJson1.setValueName(i.getValueName());
//            optionJson1.setValuePrice(i.getValuePrice());
//            menuListOptionArray.add(optionJson1);
//            menuListOption.add(i.setMenuOptions(menuListOptionArray););

            menuTotalOption.clear();
        }





        // postArray(menuListOption);
        Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(menuListOption).getAsJsonArray();




        //JsonArray myCustomArray1 = gson.toJsonTree(menuListOptionArray).getAsJsonArray();
        //  Toast.makeText(CheckoutActivity.this, myCustomArray.toString(), Toast.LENGTH_SHORT).show();
        //  Log.d("array",jsonArray2.toString());
        Log.d("optionarray", myCustomArray.toString());


        rvDeliveryAddress.addOnItemTouchListener(new RecyclerItemClickListener(CheckoutActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                view.setBackgroundColor(R.color.colorPrimary);

                selectedAddress = true;
                AppController.setString(getApplicationContext(), "ich_address1", addressList.get(position).getAddress1());
                AppController.setString(getApplicationContext(), "ich_address2", addressList.get(position).getAddress2());
                AppController.setString(getApplicationContext(), "ich_city", addressList.get(position).getCity());
                AppController.setString(getApplicationContext(), "ich_state", addressList.get(position).getState());
                AppController.setString(getApplicationContext(), "ich_address_id", addressList.get(position).getAddressId());



            }
        }));

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.proceed)
    public void proceedtopayment() {
        {
            if (viewFlipper.getDisplayedChild() == 0) {
                if (selectedAddress)
                    showNextPage();
                else {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Select Address", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            } else if (viewFlipper.getDisplayedChild() == 1) {


                showNextPage();

            } else if (viewFlipper.getDisplayedChild() == 2) {


            }
        }
    }


    @Override
    public void onBackPressed() {
        if (viewFlipper.getDisplayedChild() == 0) {
            super.onBackPressed();
        } else if (viewFlipper.getDisplayedChild() == 1) {
            showPreviousPage();
        } else if (viewFlipper.getDisplayedChild() == 2) {
            showPreviousPage();
        }

    }

    private void switchIndicators(int displayedChild) {
        // TODO Auto-generated method stub
        if (displayedChild == 0) {
            orderpost_scroll.setImageResource(R.drawable.job_1);
            textHeader.setText(getResources().getString(R.string.job_create_header_1));
        } else if (displayedChild == 1) {
            btn_proceedtopayment.setText("Order");
            textHeader.setText(getResources().getString(R.string.order_create_header_two));
            tv_name.setText(AppController.getString(getApplicationContext(), "ich_name"));
            tv_address1.setText(AppController.getString(getApplicationContext(), "ich_address1"));
            tv_address2.setText(AppController.getString(getApplicationContext(), "ich_address2"));
            tv_city.setText(AppController.getString(getApplicationContext(), "ich_city"));
            tv_state.setText(AppController.getString(getApplicationContext(), "ich_state"));

            /*DeliveryAddressPost deliveryAddressPost =new DeliveryAddressPost();
            deliveryAddressPost.setLocation_id(AppController.getString(getApplicationContext(), "ich_location_id"));
            deliveryAddressPost.setCustomer_id(AppController.getString(getApplicationContext(), "ich_customer_id"));
            deliveryAddressPost.setCheckout_step(AppController.getString(getApplicationContext(), "ich_checkout_step"));
            deliveryAddressPost.setFirst_name(AppController.getString(getApplicationContext(), "ich_first_name"));
            deliveryAddressPost.setLast_name(AppController.getString(getApplicationContext(), "ich_last_name"));
            deliveryAddressPost.setEmail(AppController.getString(getApplicationContext(), "ich_email"));
            deliveryAddressPost.setTelephone(AppController.getString(getApplicationContext(), "ich_telephone"));
            deliveryAddressPost.setPrivilage_card_no(AppController.getString(getApplicationContext(), "ich_previlage_card_no"));
            deliveryAddressPost.setOrder_time_type(AppController.getString(getApplicationContext(), "ich_time_type"));
            deliveryAddressPost.setOrder_asap_time(AppController.getString(getApplicationContext(), "ich_asap_time"));
            deliveryAddressPost.setOrder_date(AppController.getString(getApplicationContext(), "ich_order_date"));
            deliveryAddressPost.setOrder_hour(AppController.getString(getApplicationContext(), "ich_order_hour"));
            deliveryAddressPost.setOrder_minute(AppController.getString(getApplicationContext(), "ich_order_minute"));
            deliveryAddressPost.setOrder_time(AppController.getString(getApplicationContext(), "ich_order_time"));
            deliveryAddressPost.setOrder_type(AppController.getString(getApplicationContext(), "ich_order_type"));
            deliveryAddressPost.setAddress_id(AppController.getString(getApplicationContext(), "ich_address_id"));
            deliveryAddressPost.setComment(AppController.getString(getApplicationContext(), "ich_comment"));

            deliveryAddressList.add(deliveryAddressPost);*/

            orderpost_scroll.setImageResource(R.drawable.job_2);

        } else if (displayedChild == 2) {
            textHeader.setText(getResources().getString(R.string.order_create_header_three));
            orderpost_scroll.setImageResource(R.drawable.job_3);

        }

        togglePageTitle(displayedChild);
    }

    private void togglePageTitle(int pageNumber) {

        switch (pageNumber) {
            case 0:
                setTitle(getResources().getString(R.string.job_create_header_1));
                break;
            case 1:
                setTitle(getResources().getString(R.string.order_create_header_two));
                break;
            case 2:
                setTitle(getResources().getString(R.string.order_create_header_three));
                break;

        }
    }

    private void showNextPage() {
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
                mContext, R.anim.left_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
                mContext, R.anim.left_out));
        // controlling animation
        viewFlipper.getInAnimation().setAnimationListener(
                mAnimationListener);
        viewFlipper.showNext();
        switchIndicators(viewFlipper.getDisplayedChild());
        // togglePageTitle(viewFlipper);
    }

    private void showPreviousPage() {
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
                mContext, R.anim.right_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
                mContext, R.anim.right_out));
        // controlling animation
        viewFlipper.getInAnimation().setAnimationListener(
                mAnimationListener);
        viewFlipper.showPrevious();
        switchIndicators(viewFlipper.getDisplayedChild());
        //  togglePageTitle(viewFlipper);

    }

    public void getdelivryAddress
            () {

        final ProgressDialog progressDialog = new ProgressDialog(CheckoutActivity.this);


        progressDialog.setMessage("loading");
        progressDialog.show();
        String customerId = AppController.getString(getApplicationContext(), "ich_customer_id");


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AddressResponse> call = apiService.addressesList(customerId);
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, retrofit2.Response<AddressResponse> response) {
                progressDialog.hide();
                if (response.isSuccessful()) {
                    String fName = response.body().getCustomerDetails().getFirstName() + " " + response.body().getCustomerDetails().getLastName();
                    AppController.setString(getApplicationContext(), "ich_name", fName);
                    AppController.setString(getApplicationContext(), "ich_first_name", response.body().getCustomerDetails().getFirstName());
                    AppController.setString(getApplicationContext(), "ich_last_name", response.body().getCustomerDetails().getLastName());
                    AppController.setString(getApplicationContext(), "ich_email", response.body().getCustomerDetails().getEmail());
                    AppController.setString(getApplicationContext(), "ich_telephone", response.body().getCustomerDetails().getTelephone());
                    AppController.setString(getApplicationContext(), "ich_previlage_card_no", response.body().getCustomerDetails().getPrivilageCardNo());
                    addressList = response.body().getCustomerDetails().getAddresses();
                    rvDeliveryAddress.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvDeliveryAddress.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
                    rvDeliveryAddress.setAdapter(new AddressListAdapter(addressList, R.layout.item_address_list, getApplicationContext(), fName));
                }

            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();

            }
        });
    }


    public void postArray(List<JSONObject> menuListOption) {

        final ProgressDialog progressDialog = new ProgressDialog(CheckoutActivity.this);


        progressDialog.setMessage("loading");
        progressDialog.show();
        String customerId = AppController.getString(getApplicationContext(), "ich_customer_id");


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SignupResponse> call = apiService.PostArray(menuListOption.toString());
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, retrofit2.Response<SignupResponse> response) {
                progressDialog.hide();
                if (response.isSuccessful()) {
                }

            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
