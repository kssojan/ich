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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
    private List<Address> addressList = new ArrayList<>();
    private List<Menu> menuListOption = new ArrayList<>();
    private List<Menu> menuListOptionArray = new ArrayList<>();
    private List<Menu> menuListOptionTotal = new ArrayList<>();
    boolean selectedAddress=false;
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

            //
        /*    for (int m=0;m<10;m++)
            {
                dataToPassOption.put("options",);
            }*/
            CartOptionItems = dao.getAllCartOptionItem(i.getMenuId());
            for (Menu j : CartOptionItems) {
                Menu optionJson1 = new Menu();
                optionJson1.setValueId(j.getValueId());
                optionJson1.setValueName(j.getValueName());
                optionJson1.setValuePrice(j.getValuePrice());
                HashMap<String, Object> dataToPassOption = new LinkedHashMap<>();
                /*HashMap<String, Object> dataToPass = new LinkedHashMap<>();
                dataToPass.put("value_id",j.getValueId());
                dataToPass.put("value_name",j.getValueName());
                dataToPass.put("value_price",j.getValuePrice());*/
                dataToPassOption.put("options",optionJson1);

              //  menuListOption.add(optionJson1);
                optionJson.setDataToPass(dataToPassOption);
            }


            menuListOption.add(optionJson);
            /*menuListOption.addAll(menuListOptionArray);
            menuListOptionTotal.addAll(menuListOption);*/





//            Menu optionJson1 = new Menu();
//            optionJson1.setMenuId(i.getMenuId());
//            optionJson1.setValueId(i.getValueId());
//            optionJson1.setValueName(i.getValueName());
//            optionJson1.setValuePrice(i.getValuePrice());
//            menuListOptionArray.add(optionJson1);
//            menuListOption.add(i.setMenuOptions(menuListOptionArray););


        }


          Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(menuListOption).getAsJsonArray();
        //JsonArray myCustomArray1 = gson.toJsonTree(menuListOptionArray).getAsJsonArray();
      //  Toast.makeText(CheckoutActivity.this, myCustomArray.toString(), Toast.LENGTH_SHORT).show();
        Log.d("array",myCustomArray.toString());
       // Log.d("optionarray",myCustomArray1.toString());


        rvDeliveryAddress.addOnItemTouchListener(new RecyclerItemClickListener(CheckoutActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                view.setBackgroundColor(R.color.colorPrimary);

              selectedAddress=true;
                AppController.setString(getApplicationContext(), "ich_address1", addressList.get(position).getAddress1());
                AppController.setString(getApplicationContext(), "ich_address2", addressList.get(position).getAddress2());
                AppController.setString(getApplicationContext(), "ich_city", addressList.get(position).getCity());
                AppController.setString(getApplicationContext(), "ich_state", addressList.get(position).getState());




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
                else
{
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
            tv_address2.setText( AppController.getString(getApplicationContext(), "ich_address2"));
            tv_city.setText( AppController.getString(getApplicationContext(), "ich_city"));
            tv_state.setText(AppController.getString(getApplicationContext(), "ich_state"));
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
        String customerId= AppController.getString(getApplicationContext(), "ich_customer_id");


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AddressResponse> call = apiService.addressesList(customerId);
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, retrofit2.Response<AddressResponse> response) {
                progressDialog.hide();
                if (response.isSuccessful()) {
                    String fName=response.body().getCustomerDetails().getFirstName()+" "+response.body().getCustomerDetails().getLastName();
                    AppController.setString(getApplicationContext(), "ich_name", fName);
                    addressList = response.body().getCustomerDetails().getAddresses();
                    rvDeliveryAddress.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvDeliveryAddress.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
                    rvDeliveryAddress.setAdapter(new AddressListAdapter(addressList, R.layout.item_address_list, getApplicationContext(),fName));
                }

            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
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
