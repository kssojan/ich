package com.example.admin.ich.modules.Login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.BaseActivity;
import com.example.admin.ich.modules.Home.HomeActivity;

public class LoginPageActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout lisignup;
    TextView tvSignin, tvSignup,tv_guest;
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    Dialog mBottomSheetDialog;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        lisignup = (LinearLayout) findViewById(R.id.lisignup);
        tvSignin = (TextView) findViewById(R.id.tv_login);
        tvSignup = (TextView) findViewById(R.id.tv_signup);
        tv_guest = (TextView) findViewById(R.id.tv_guest);
        session = new SessionManager(getApplicationContext());

        tvSignin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
        tv_guest.setOnClickListener(this);


        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        lisignup.setBackgroundDrawable(gd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if (session.isLoggedIn()) {
                    Log.d("login Isuue", "sessionfalse");
                    Intent intent1 = new Intent(LoginPageActivity.this, HomeActivity.class);
                    startActivity(intent1);
                    finish();
                }
                else
                showMenu();
                break;
            case R.id.tv_signup:
                Intent intent = new Intent(LoginPageActivity.this, SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.tv_guest:
                session.setLogin(false);
                Intent intentG = new Intent(LoginPageActivity.this, HomeActivity.class);
                intentG.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentG);
                break;
            case R.id.btn_signin:
                /*Intent intent1 = new Intent(LoginPageActivity.this, HomeActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);*/
                String email=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();
                if (isValidEmail(email)&&password.length()>0)
                {
                    signinUser(email,password,"login");
                }
                break;
        }
    }

    /*private void signinUser(String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginPageActivity.this);

        progressDialog.setMessage("loading");
        progressDialog.show();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SignupResponse> call = apiService.Signin(email, password);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, retrofit2.Response<SignupResponse> response) {
                progressDialog.hide();
                if (response.body().getStatus()) {
                    session.setLogin(true);
                    AppController.setString(getApplicationContext(), "ich_customer_id", response.body().getCstomerId());
                    Intent intent=new Intent(LoginPageActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }


            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();

            }
        });
    }*/

    private void showMenu() {
        final LayoutInflater li = LayoutInflater.from(LoginPageActivity.this);
        final View view = li.inflate(R.layout.log_in, null);
        mBottomSheetDialog = new Dialog(LoginPageActivity.this, R.style.MaterialDialogSheet);
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
        if (inputPassword.getText().toString().trim().isEmpty()||inputPassword.getText().length()<6) {
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
