package com.example.admin.ich.modules.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.Home.MenuTab.TabviewActivity;
import com.example.admin.ich.modules.Login.LoginPageActivity;
import com.example.admin.ich.modules.Login.SessionManager;
import com.example.admin.ich.recyclerview.RecyclerItemClickListener;
import com.example.admin.ich.recyclerview.SimpleDividerItemDecoration;
import com.example.admin.ich.retrofit.ApiClient;
import com.example.admin.ich.retrofit.ApiInterface;
import com.example.admin.ich.retrofit.model.CategoriesModel;
import com.example.admin.ich.retrofit.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private RecyclerView rvCategories;
    private List<Category> categoryList = new ArrayList<>();
    ImageView imgDrawer;
    DrawerLayout drawer;
    NavigationView navigationView;
    CategoryListAdapter categoryListAdapter;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new SessionManager(getApplicationContext());
        rvCategories = (RecyclerView) findViewById(R.id.rv_catogries);
        imgDrawer = (ImageView) findViewById(R.id.img_drawer);
        imgDrawer.setOnClickListener(this);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        /*categoryListAdapter = new CategoryListAdapter(categoryList, R.layout.item_categories_list, getApplicationContext());
        rvCategories.setAdapter(categoryListAdapter);*/
        setupClickListener();
        getCategories();
    }

    private void setupClickListener() {
        rvCategories.addOnItemTouchListener(new RecyclerItemClickListener(HomeActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String notification_meta_name = categoryList.get(position).getCategoryId();

                Intent intent = new Intent(HomeActivity.this, TabviewActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);


            }
        }));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_logout) {
            session.setLogin(false);
            Intent intent4 = new Intent(HomeActivity.this, LoginPageActivity.class);
            intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent4);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getCategories
            () {

        final ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);


        progressDialog.setMessage("loading");
        progressDialog.show();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<CategoriesModel> call = apiService.CategoriesList();
        call.enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, retrofit2.Response<CategoriesModel> response) {
                progressDialog.hide();
                categoryList = response.body().getCategory();
                rvCategories.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvCategories.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
                rvCategories.setAdapter(new CategoryListAdapter(categoryList, R.layout.item_categories_list, getApplicationContext()));


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
            case R.id.img_drawer:
                drawer.openDrawer(navigationView);
                break;
        }
    }
}
