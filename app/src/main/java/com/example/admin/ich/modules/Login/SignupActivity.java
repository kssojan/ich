package com.example.admin.ich.modules.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.ich.R;
import com.example.admin.ich.retrofit.ApiClient;
import com.example.admin.ich.retrofit.ApiInterface;
import com.example.admin.ich.retrofit.model.Country;
import com.example.admin.ich.retrofit.model.CountryResponse;
import com.example.admin.ich.retrofit.model.SignupResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_signup;
    ImageView imgClose;
    private EditText inputEmail, inputPassword, inputConformPssword, inputPhone, inputFname,
            inputLname, inputPrevilage, inputAddress,inputAddress2,inputCity,inputLandmark;
    EditText inputCountry;
    Spinner spinnerCountry;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword, inputLayoutConfirmPassword, inputLayoutPhone, inputLayoutFname, inputLayoutLname, inputLayoutPrevilage,
            inputLayoutAddress,inputLayoutCountry;
    String email, fname, lname, mobile, address, previlage = "", password,address2="",city,country,lanmark="",countryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.input_layout_confirmpassword);
        inputLayoutFname = (TextInputLayout) findViewById(R.id.input_layout_fname);
        inputLayoutLname = (TextInputLayout) findViewById(R.id.input_layout_lname);
        inputLayoutPrevilage = (TextInputLayout) findViewById(R.id.input_layout_previlage);
        inputLayoutAddress = (TextInputLayout) findViewById(R.id.input_layout_address);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_telephone);
        inputLayoutCountry = (TextInputLayout) findViewById(R.id.input_layout_country);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputConformPssword = (EditText) findViewById(R.id.input_confirmpassword);
        inputFname = (EditText) findViewById(R.id.input_fname);
        inputLname = (EditText) findViewById(R.id.input_lname);
        inputPhone = (EditText) findViewById(R.id.input_telephone);
        inputPrevilage = (EditText) findViewById(R.id.input_previlage);
        inputAddress = (EditText) findViewById(R.id.input_address);
        inputAddress2 = (EditText) findViewById(R.id.input_address2);
        inputLandmark = (EditText) findViewById(R.id.input_landmark);
        inputCity = (EditText) findViewById(R.id.input_city);
        inputCountry = (EditText) findViewById(R.id.input_country);
        spinnerCountry=(Spinner)findViewById(R.id.spinnerCountry);

        countryList();


        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        inputConformPssword.addTextChangedListener(new MyTextWatcher(inputConformPssword));

        btn_signup = (Button) findViewById(R.id.btn_signup);
        imgClose = (ImageView) findViewById(R.id.colsesignup);
        imgClose.setOnClickListener(this);
        btn_signup.setOnClickListener(this);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        btn_signup.setBackgroundDrawable(gd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.colsesignup:
                finish();
                break;
            case R.id.btn_signup:
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();
                address = inputAddress.getText().toString();
                previlage = inputPrevilage.getText().toString();
                fname = inputFname.getText().toString();
                lname = inputLname.getText().toString();
                mobile = inputPhone.getText().toString();

                city = inputCity.getText().toString();
                address2= inputAddress2.getText().toString();
                lanmark= inputLandmark.getText().toString();
                country = inputCountry.getText().toString();


                if (!isValidEmail(email)) {
                    return;

                } else
                    registerUser(email, password, address, previlage, fname, lname, mobile,city,address2,lanmark,country);

                break;
        }
    }

    public void countryList
            () {
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setMessage("loading");
        progressDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<CountryResponse> call = apiService.CountryList();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, retrofit2.Response<CountryResponse> response) {

                progressDialog.dismiss();
                final List<Country> listVehicle = response.body().getCountries();
                ArrayList<String> manufactureList = new ArrayList<String>();
                manufactureList.add(0, "Select Country");
                for (int i = 0; i < listVehicle.size(); i++) {

                    manufactureList.add(listVehicle.get(i).getCountryName());
                }
                // Creating adapter for spinner service
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_spinner_item, manufactureList);
                // Drop down layout style - list view with radio button
                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner;
                spinnerCountry.setAdapter(mAdapter);


                inputCountry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        spinnerCountry.performClick();
                    }
                });
                spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            if (isService)

                        inputCountry.setText(spinnerCountry.getSelectedItem().toString());
                        if (position != 0) {
                            countryId = listVehicle.get(position - 1).getCountryId();


                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                // Log error here since request failed
                progressDialog.dismiss();

            }
        });
    }



    private void registerUser(String email, String password, String address, String previlage, String fname,
                              String lname, String mobile, String city, String address2, String lanmark, String country) {
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);

        progressDialog.setMessage("loading");
        progressDialog.show();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<SignupResponse> call = apiService.Signup(email, password, fname, lname, mobile,"1", address,address2,city,
                lanmark,country, previlage);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, retrofit2.Response<SignupResponse> response) {
                progressDialog.hide();
                if (response.body().getStatus()) {
                    Toast.makeText(SignupActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignupActivity.this,LoginPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }

                Toast.makeText(SignupActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();
                progressDialog.hide();

            }
        });
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
                    validateField(inputPassword, inputLayoutPassword);
                    break;
                case R.id.input_confirmpassword:
                    validatePassword();
                    break;
                case R.id.input_fname:
                    validateField(inputFname, inputLayoutFname);
                    break;
                case R.id.input_lname:
                    validateField(inputLname, inputLayoutLname);
                    break;
                case R.id.input_telephone:
                    validateField(inputPhone, inputLayoutPhone);
                    break;
                case R.id.input_country:
                    validateField(inputCountry, inputLayoutCountry);
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

    private boolean validateField(EditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(getString(R.string.err_msg_field));
            requestFocus(editText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (!Objects.equals(inputConformPssword.getText().toString().trim(), inputPassword.getText().toString().trim())) {
            inputLayoutConfirmPassword.setError(getString(R.string.err_msg_password_notmatching));
            requestFocus(inputConformPssword);
            return false;
        } else {
            inputLayoutConfirmPassword.setErrorEnabled(false);
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
