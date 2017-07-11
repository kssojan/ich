package com.example.admin.ich.modules.Home.MenuTab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.ich.R;
import com.example.admin.ich.modules.Database.IchDao;
import com.example.admin.ich.retrofit.ApiClient;
import com.example.admin.ich.retrofit.ApiInterface;
import com.example.admin.ich.retrofit.model.Menu;
import com.example.admin.ich.retrofit.model.Menumodel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 5/11/2017.
 */
public class CategoryTabFragment extends Fragment implements View.OnClickListener {
    private String mText;
    private RecyclerView rvMenus;
    private List<Menu> menuList = new ArrayList<>();
    MenuListAdapter adapter;
    TextView tv_count, tv_veg, tv_nonveg, tv_all;
    TextView tv_total, tv_location;
    private boolean _hasLoadedOnce = false;
    int totalCartCount;
    double total_price;
    View vegView, nonView, allview;

    IchDao dao;
    ArrayList<Menu> product;
    ArrayList<String> menusList, menuTotalList, menuCountList;

    public CategoryTabFragment(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        rvMenus = (RecyclerView) view.findViewById(R.id.rv_menus);
        /*tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_total = (TextView) view.findViewById(R.id.totalAmount);
        tv_location = (TextView) view.findViewById(R.id.tv_location);*/
        tv_veg = (TextView) view.findViewById(R.id.tv_veg);
        tv_nonveg = (TextView) view.findViewById(R.id.tv_nonveg);
        tv_all = (TextView) view.findViewById(R.id.tv_all);

        dao = new IchDao(getActivity());


        vegView = view.findViewById(R.id.vegline);
        nonView = view.findViewById(R.id.nonvegline);
        allview = view.findViewById(R.id.allline);


        tv_all.setOnClickListener(this);
        tv_veg.setOnClickListener(this);
        tv_nonveg.setOnClickListener(this);
        vegView.setVisibility(View.VISIBLE);
        tv_veg.setTextColor(getResources().getColor(R.color.colorAccent));
        getmenus(mText);
        menusList = new ArrayList<>();
        menuTotalList = new ArrayList<>();
        menuCountList = new ArrayList<>();


       /* tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .build(getActivity());
                    startActivityForResult(intent, 1);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    System.out.println(e);
                    // TODO: Handle the error.
                }
            }
        });*/
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Boolean a = isConnectingToInternet(getActivity());
        if (a) {

        } else {

            Snackbar snackbar = Snackbar
                    .make(getActivity().findViewById(R.id.root), "No internet connection", Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();

        }


    }

    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public void getmenus
            (String id) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());

        progressDialog.setMessage("loading");
        progressDialog.show();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Menumodel> call = apiService.MenuList(id);
        call.enqueue(new Callback<Menumodel>() {
            @Override
            public void onResponse(Call<Menumodel> call, retrofit2.Response<Menumodel> response) {
                progressDialog.hide();
                if (response.code() == 200) {
                    if (response.body().getStatus()) {
                        menuList = response.body().getMenus();
                        setclick(menuList);
//

                    }


                }
                if (response.code() == 404)
                    Toast.makeText(getActivity(), "No menus found", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Menumodel> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();

            }
        });
    }

    public void setclick(final List<Menu> menuList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMenus.setLayoutManager(layoutManager);
        /*rvMenus.setAdapter(new MenuListAdapter(menuList, R.layout.item_menu_list, getContext()));
        MenuListAdapter menu = new MenuListAdapter(menuList, R.layout.item_categories_list, getContext());*/

        adapter = new MenuListAdapter(menuList, R.layout.item_menu_list, getActivity(), new MenuListAdapter.ListAdapterListener() {
            @Override
            public void onClickAtOKButton(int position) {


                String value = menuList.get(position).getMealtimeId();
                String menuid = menuList.get(position).getMenuId();
                String menuname = menuList.get(position).getMenuName();
                String menupic = menuList.get(position).getImagePath();

                menuList.get(position).getMenuOptions();
                String menu_price = menuList.get(position).getMenuPrice();

                DecimalFormat df = new DecimalFormat("####0.00");
                System.out.println("Value: " + df.format(total_price));

                if (menuList.get(position).getMenuOptions().contains("No menu options found")) {


                    product = dao.getCartItem(menuid);
                    for (Menu i : product) {
                        menusList.add(i.getMenuId());
                        menuTotalList.add(i.getTotalcount());
                        menuCountList.add(i.getCount());


                    }
                    if (menuCountList.toString() != "[]") {
                        String count = menuCountList.toString();

                        totalCartCount = Integer.parseInt(value);
                        String total = menuTotalList.toString();
                        total_price = (Integer.parseInt(value) * Float.parseFloat(menu_price));
                        // Toast.makeText(getActivity(), count+" "+total, Toast.LENGTH_SHORT).show();
                        if (totalCartCount != 0)
                            dao.updateCart(menuid, String.valueOf(totalCartCount), String.valueOf(df.format(total_price)));
                        else
                            dao.deleteMenu(menuid);
                    } else {
                        totalCartCount = Integer.parseInt(value);
                        total_price = (Integer.parseInt(value) * Double.parseDouble(menu_price));
                        if (totalCartCount != 0)
                            dao.insetCart(menuid, menuname, menupic, String.valueOf(totalCartCount),
                                    menu_price,"no", String.valueOf(0), String.valueOf(df.format(total_price)));


                    }

                    updateCart();
                }
                else
                {

                    if (!Objects.equals(value, "0")) {
                        Intent intent = new Intent(getActivity(), MenuOptionActivity.class);
                        intent.putExtra("menuId", menuid);
                        intent.putExtra("count", value);
                        intent.putExtra("menuName", menuname);
                        intent.putExtra("menuPic", menupic);
                        intent.putExtra("menuPrice", menu_price);
                        intent.putExtra("menuOptions", menuList.get(position).getMenuOptions().toString());
                        startActivityForResult(intent, 2);// Activity is started with requestCode 2
                    }
                    else
                    {
                       /* Snackbar snackbar = Snackbar
                                .make(getActivity().findViewById(R.id.root), "Please select atleast one menu", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.RED);
                        snackbar.show();
*/
                        Toast.makeText(getActivity(), "Please select atleast one menu", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
        rvMenus.setAdapter(adapter);

    }

    public void updateCart() {
        TabviewActivity tabviewActivity = (TabviewActivity) getActivity();
        tabviewActivity.updateCart();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_veg:
                vegView.setVisibility(View.VISIBLE);
                nonView.setVisibility(View.INVISIBLE);
                allview.setVisibility(View.INVISIBLE);
                tv_veg.setTextColor(getResources().getColor(R.color.colorAccent));
                tv_nonveg.setTextColor(getResources().getColor(R.color.colorPrimaryText));
                tv_all.setTextColor(getResources().getColor(R.color.colorPrimaryText));
                break;
            case R.id.tv_nonveg:
                vegView.setVisibility(View.INVISIBLE);
                nonView.setVisibility(View.VISIBLE);
                allview.setVisibility(View.INVISIBLE);
                tv_veg.setTextColor(getResources().getColor(R.color.colorPrimaryText));
                tv_nonveg.setTextColor(getResources().getColor(R.color.colorAccent));
                tv_all.setTextColor(getResources().getColor(R.color.colorPrimaryText));
                break;
            case R.id.tv_all:
                vegView.setVisibility(View.INVISIBLE);
                nonView.setVisibility(View.INVISIBLE);
                allview.setVisibility(View.VISIBLE);
                tv_veg.setTextColor(getResources().getColor(R.color.colorPrimaryText));
                tv_nonveg.setTextColor(getResources().getColor(R.color.colorPrimaryText));
                tv_all.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1010) {

        }

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                tv_location.setText(place.getName());


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if(requestCode==2)
        {

            updateCart();

        }

    }



}
