package com.example.admin.ich.retrofit;

import com.example.admin.ich.retrofit.model.AddressResponse;
import com.example.admin.ich.retrofit.model.CategoriesModel;
import com.example.admin.ich.retrofit.model.CountryResponse;
import com.example.admin.ich.retrofit.model.MenuOption.MenuOptionResponse;
import com.example.admin.ich.retrofit.model.Menumodel;
import com.example.admin.ich.retrofit.model.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    /* @FormUrlEncoded
     @POST("main/forgot-password")
     Call<LoginResponse> ForgotPasscode(@Field("username") String username);


     @GET("vehicle")
     Call<VechicleListResponse> VehicleList();

     @GET("service-categories")
     Call<CatagoriesListresponse> CatagoriesList(@Query("expand") String services);*/
    //http://dev.bteem.com/ICH/Menu_api/menu?category_id=15
    @GET("Category_api/category")
    Call<CategoriesModel> CategoriesList();

    @GET("Registration_api/countries")
    Call<CountryResponse> CountryList();

    @GET("Menu_api/menu")
    Call<Menumodel> MenuList(@Query("category_id") String id);
    @GET("Customer_details_api/customer_details")
    Call<AddressResponse> addressesList(@Query("customer_id") String id);

    @GET("Menu_api/menu_option")
    Call<MenuOptionResponse> MenuOptionList(@Query("menu_id") String id);

    @FormUrlEncoded
    @POST("Registration_api/user_registration")
    Call<SignupResponse> Signup(@Field("email") String email, @Field("password") String password,
                                @Field("first_name") String fname, @Field("last_name") String lname,
                                @Field("telephone") String mobile,@Field("newsletter") String newsletter,
                                @Field("address_1") String address1,@Field("address_2") String address2,
                                @Field("city") String city,@Field("state") String state,@Field("country") String country,
                                @Field("privilage_card_no") String previlage);

    @FormUrlEncoded
    @POST("Registration_api/user_login")
    Call<SignupResponse> Signin(@Field("email") String email, @Field("password") String password);
}
