package com.example.admin.ich.modules.Home.MenuTab;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.BaseActivity;
import com.example.admin.ich.modules.Database.IchDao;
import com.example.admin.ich.modules.Home.Checkout.CheckoutActivity;
import com.example.admin.ich.modules.Home.OptionJson;
import com.example.admin.ich.modules.Login.SessionManager;
import com.example.admin.ich.retrofit.ApiClient;
import com.example.admin.ich.retrofit.ApiInterface;
import com.example.admin.ich.retrofit.model.CategoriesModel;
import com.example.admin.ich.retrofit.model.Category;
import com.example.admin.ich.retrofit.model.Menu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class TabviewActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Category> categoryList = new ArrayList<>();
    ArrayList<Integer> elements = new ArrayList<>();
    ArrayList<String> categ = new ArrayList<>();
    int position_page;
    TextView tv_total, tv_count, tv_location;
    ArrayList<Menu> product, sumtotal;
    ArrayList<OptionJson> sumtotalOption;
    IchDao dao;
    ArrayList<String> menuIdList;
    ArrayList<Double> menuTotal, menuTotalOption;
    LinearLayout liCheckout;
    RelativeLayout relativeLayoutCart;

    private SessionManager session;
    Dialog mBottomSheetDialog;
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ICH");
        session = new SessionManager(getApplicationContext());
        Intent intent = getIntent();
        position_page = intent.getIntExtra("position", 1);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(Gravity.CENTER_VERTICAL);
        getCategories();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_total = (TextView) findViewById(R.id.totalAmount);
        tv_location = (TextView) findViewById(R.id.tv_location);
        liCheckout = (LinearLayout) findViewById(R.id.licheckout);
        relativeLayoutCart = (RelativeLayout) findViewById(R.id.relativeCart);
        relativeLayoutCart.setOnClickListener(this);
        liCheckout.setOnClickListener(this);
        tv_total.setOnClickListener(this);
        tv_count.setOnClickListener(this);
        dao = new IchDao(getApplicationContext());
        menuIdList = new ArrayList<>();
        menuTotal = new ArrayList<>();
        menuTotalOption = new ArrayList<>();

        tv_location.setOnClickListener(this);

        product = dao.getCart();
        for (Menu i : product) {
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
        tv_count.setText(count);
    }

    private void setupViewPager(ViewPager viewPager, List<Category> categoryList) {
        //  final ActionBar bar = getActionBar();
        TabviewActivity.ViewPagerAdapter adapter = new TabviewActivity.ViewPagerAdapter(getSupportFragmentManager());
        /*adapter.addFrag(new CategoryTabFragment(), "Category");
        viewPager.setAdapter(adapter)*/
        ;

        Log.d("position", String.valueOf(position_page));
        for (int i = 0; i <= categoryList.size() - 1; i++) {

            //final int tabCount = bar.getTabCount();
            final String text = categoryList.get(i).getName();
            adapter.addFrag(new CategoryTabFragment(categoryList.get(i).getCategoryId()), text);
            viewPager.setAdapter(adapter);

        }
        viewPager.setCurrentItem(position_page);
    }

    public void getCategories
            () {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<CategoriesModel> call = apiService.CategoriesList();
        call.enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, retrofit2.Response<CategoriesModel> response) {

                categoryList = response.body().getCategory();
                TabviewActivity.ViewPagerAdapter adapter = new TabviewActivity.ViewPagerAdapter(getSupportFragmentManager());
                for (int i = 0; i < categoryList.size(); i++) {

                    elements.add(Integer.valueOf(categoryList.get(i).getCategoryId()));
                    categ.add(categoryList.get(i).getName());

                }
                setupViewPager(viewPager, categoryList);

            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.licheckout:
                if (session.isLoggedIn()) {
                    Intent intent4 = new Intent(TabviewActivity.this, CheckoutActivity.class);
                    startActivity(intent4);
                }
                else
                {
                    showMenu();
                }
                break;
            case R.id.relativeCart:
                Intent intent3 = new Intent(TabviewActivity.this, CartActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_count:
                Intent intent = new Intent(TabviewActivity.this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.totalAmount:
                Intent intent2 = new Intent(TabviewActivity.this, CartActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_location:
                try {
                    Intent intent1 =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .build(TabviewActivity.this);
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
        final LayoutInflater li = LayoutInflater.from(TabviewActivity.this);
        final View view = li.inflate(R.layout.log_in, null);
        mBottomSheetDialog = new Dialog(TabviewActivity.this, R.style.MaterialDialogSheet);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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

    public void updateCart() {

        tv_total.setText("");
        tv_count.setText("");
        menuIdList.clear();
        menuTotal.clear();
        menuTotalOption.clear();

        product = dao.getCart();
        for (Menu i : product) {
            menuIdList.add(i.getMenuId());
            //  menuTotal.add(i.getTotalcount());


        }
        Double total = 0.0;
        String count = String.valueOf(menuIdList.size());
      /*  for (int i=0;i<menuTotal.size();i++)
        {
            Double j=0.0;
         total=j+Double.parseDouble(menuTotal.get(i));
        }*/
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
        // tv_total.setText(String.valueOf(dao.getTotal()));
        tv_count.setText(count);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1010) {

        }

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(TabviewActivity.this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                tv_location.setText(place.getName());


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(TabviewActivity.this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        // put your code here...
        updateCart();
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

}
