
package com.example.admin.ich.retrofit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerDetails {

    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address_id")
    @Expose
    private String addressId;
    @SerializedName("privilage_card_no")
    @Expose
    private String privilageCardNo;
    @SerializedName("security_question_id")
    @Expose
    private String securityQuestionId;
    @SerializedName("security_answer")
    @Expose
    private String securityAnswer;
    @SerializedName("newsletter")
    @Expose
    private String newsletter;
    @SerializedName("customer_group_id")
    @Expose
    private String customerGroupId;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("cart")
    @Expose
    private String cart;
    @SerializedName("addresses")
    @Expose
    private List<Address> addresses = null;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPrivilageCardNo() {
        return privilageCardNo;
    }

    public void setPrivilageCardNo(String privilageCardNo) {
        this.privilageCardNo = privilageCardNo;
    }

    public String getSecurityQuestionId() {
        return securityQuestionId;
    }

    public void setSecurityQuestionId(String securityQuestionId) {
        this.securityQuestionId = securityQuestionId;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(String customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}
