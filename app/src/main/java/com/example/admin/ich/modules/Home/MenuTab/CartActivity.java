package com.example.admin.ich.modules.Home.MenuTab;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.BaseActivity;
import com.example.admin.ich.modules.Database.IchDao;
import com.example.admin.ich.modules.Home.Checkout.CheckoutActivity;
import com.example.admin.ich.modules.Home.OptionJson;
import com.example.admin.ich.modules.Login.SessionManager;
import com.example.admin.ich.retrofit.model.Menu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView rvMenus;
    private List<Menu> menuList = new ArrayList<>();
    IchDao dao;

    CartListAdapter adapter;

    TextView tv_total, tv_count,tv_location;
    ArrayList<Menu> product, sumtotal, totalCount;

    ArrayList<String> menuIdList;
    ArrayList<Double> menuTotal, menuTotalOption;
    ArrayList<OptionJson> sumtotalOption;
    LinearLayout liCheckout;

    int totalCartCount;
    double total_price;

    private SessionManager session;
    Dialog mBottomSheetDialog;
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("ICH Cart");

        session = new SessionManager(getApplicationContext());
        rvMenus = (RecyclerView) findViewById(R.id.rv_menus);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_total = (TextView) findViewById(R.id.totalAmount);
        tv_location = (TextView)findViewById(R.id.tv_location);
        tv_location.setOnClickListener(this);
        liCheckout = (LinearLayout) findViewById(R.id.licheckout);
        liCheckout.setOnClickListener(this);
        dao = new IchDao(CartActivity.this);

        menuIdList = new ArrayList<>();
        menuTotal = new ArrayList<>();
        menuTotalOption = new ArrayList<>();


       /* totalCount = dao.getCart();
        for (Menu i : totalCount) {
            menuIdList.add(i.getMenuId());

        }
        String count = String.valueOf(menuIdList.size());
        DecimalFormat df = new DecimalFormat("####0.00");
        sumtotal = dao.getTotalPrice();
        for (Menu i : sumtotal) {

            menuTotal.add(Double.valueOf(i.getTotalcount()));

        }
        int i;
        double sum = 0;
        for (i = 0; i < menuTotal.size(); i++)
            sum += menuTotal.get(i);
        tv_total.setText(String.valueOf(df.format(sum)));
        //  tv_total.setText(String.valueOf(dao.getTotal()));
        tv_count.setText(count);*/
        updateCart();

        product = dao.getCartMenus();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvMenus.setLayoutManager(layoutManager);
        /*rvMenus.setAdapter(new MenuListAdapter(menuList, R.layout.item_menu_list, getContext()));
        MenuListAdapter menu = new MenuListAdapter(menuList, R.layout.item_categories_list, getContext());*/

        adapter = new CartListAdapter(dao,product, R.layout.item_cart_menu_list, getApplicationContext(), new CartListAdapter.ListAdapterListener() {
            @Override
            public void onClickAtOKButton(int position) {

                String itemCount = product.get(position).getCount();
                String menuid = product.get(position).getMenuId();
                String menu_price = product.get(position).getMenuPrice();
                String menu_option = product.get(position).getMenuOptionSelection();
                String menuname = product.get(position).getMenuName();
                String menupic = product.get(position).getImagePath();
                DecimalFormat df = new DecimalFormat("####0.00");

                if (menu_option.equals("no")) {

                    if (itemCount.equals("0")) {
                        dao.deleteMenu(menuid);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    } else {
                        totalCartCount = Integer.parseInt(itemCount);

                        total_price = (Integer.parseInt(itemCount) * Float.parseFloat(menu_price));
                        // Toast.makeText(getActivity(), count+" "+total, Toast.LENGTH_SHORT).show();
                        dao.updateCart(menuid, String.valueOf(totalCartCount), String.valueOf(df.format(total_price)));
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                } else {
                    if (!Objects.equals(itemCount, "0")) {
                        Intent intent = new Intent(CartActivity.this, CartMenuOptionActivity.class);
                        intent.putExtra("menuId", menuid);
                        intent.putExtra("count", itemCount);
                        intent.putExtra("menuName", menuname);
                        intent.putExtra("menuPic", menupic);
                        intent.putExtra("menuPrice", menu_price);
                        intent.putExtra("menuOptions", menu_option);
                        startActivityForResult(intent, 3);
                    } else {
                        //Toast.makeText(CartActivity.this, "Please select atleast one menu", Toast.LENGTH_SHORT).show();
                        dao.deleteOptionCart(menuid);
                        dao.deleteMenu(menuid);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }

                }

            }
        });
        rvMenus.setAdapter(adapter);


       /* rvMenus.addOnItemTouchListener(new RecyclerItemClickListener(CartActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Dialog dialog=new Dialog(CartActivity.this);
                String menupic=product.get(position).getImagePath();

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.item_menu_list);

                ImageView addImage=(ImageView)dialog.findViewById (R.id.addImage);


                ImageView menuimage=(ImageView)dialog.findViewById (R.id.menuimage);

                LinearLayout lititle=(LinearLayout)dialog.findViewById(R.id.lititle);

                TextView menudescription=(TextView)dialog.findViewById(R.id.menudescription);

                LinearLayout licountandprice=(LinearLayout)dialog.findViewById(R.id.licountandprice);

                Picasso.with(CartActivity.this).load(menupic).fit().centerCrop().into(menuimage);
                addImage.setVisibility(View.GONE);

                 lititle.setVisibility(View.GONE);

                menudescription.setVisibility(View.GONE);

                licountandprice.setVisibility(View.GONE);

                dialog.show();




            }
        }));*/

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(CartActivity.this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                tv_location.setText(place.getName());


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(CartActivity.this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if (requestCode == 3) {
            updateCart();
        }
    }

    private void updateCart() {
        tv_total.setText("");
        tv_count.setText("");
        menuIdList.clear();
        menuTotal.clear();
        menuTotalOption.clear();

        totalCount = dao.getCart();
        for (Menu i : totalCount) {
            menuIdList.add(i.getMenuId());

        }
        String count = String.valueOf(menuIdList.size());
        DecimalFormat df = new DecimalFormat("####0.00");
        sumtotal = dao.getTotalPrice();
        for (Menu i : sumtotal) {

            menuTotal.add(Double.valueOf(i.getTotalcount()));

        }
        int i;
        double sum = 0;
        for (i = 0; i < menuTotal.size(); i++)
            sum += menuTotal.get(i);


        sumtotalOption = dao.getTotalPriceOption();
        for (OptionJson j : sumtotalOption) {

            menuTotalOption.add(Double.valueOf(j.getOption_total()));

        }
        int j;
        double sumOption = 0;
        for (j = 0; j < menuTotalOption.size(); j++)
            sumOption += menuTotalOption.get(j);


        tv_total.setText(String.valueOf(df.format(sum + sumOption)));
        //  tv_total.setText(String.valueOf(dao.getTotal()));
        tv_count.setText(count);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.licheckout:
                if (session.isLoggedIn()) {
                    Intent intent4 = new Intent(CartActivity.this, CheckoutActivity.class);
                    startActivity(intent4);
                }
                else
                {
                    showMenu();
                }
                break;

            case R.id.tv_location:
                try {
                    Intent intent1 =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .build(CartActivity.this);
                    startActivityForResult(intent1, 1);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    System.out.println(e);
                    // TODO: Handle the error.
                }

                break;
            case R.id.btn_signin:
                /*Intent intent1 = new Intent(LoginPageActivity.this, HomeActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);*/
                String email=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();
                if (isValidEmail(email)&&password.length()>0)
                {
                    signinUser(email,password,"checkout");
                }
                break;
        }
    }

    private void showMenu() {
        final LayoutInflater li = LayoutInflater.from(CartActivity.this);
        final View view = li.inflate(R.layout.log_in, null);
        mBottomSheetDialog = new Dialog(CartActivity.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        // mBottomSheetDialog.setCancelable(true);


        // mBottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mBottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        inputLayoutEmail = (TextInputLayout) mBottomSheetDialog.findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) mBottomSheetDialog.findViewById(R.id.input_layout_password);

        inputEmail = (EditText) mBottomSheetDialog.findViewById(R.id.input_email);
        inputPassword = (EditText) mBottomSheetDialog.findViewById(R.id.input_password);
        btnSignUp = (Button) mBottomSheetDialog.findViewById(R.id.btn_signin);
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);

        btnSignUp.setOnClickListener(this);
        mBottomSheetDialog.show();
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;

        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.input_email:
                    validateEmail();

                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
