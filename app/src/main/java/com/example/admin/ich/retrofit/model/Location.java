
package com.example.admin.ich.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("location_name")
    @Expose
    private String locationName;
    @SerializedName("location_email")
    @Expose
    private String locationEmail;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("location_address_1")
    @Expose
    private String locationAddress1;
    @SerializedName("location_address_2")
    @Expose
    private String locationAddress2;
    @SerializedName("location_city")
    @Expose
    private String locationCity;
    @SerializedName("location_state")
    @Expose
    private String locationState;
    @SerializedName("location_postcode")
    @Expose
    private String locationPostcode;
    @SerializedName("location_country_id")
    @Expose
    private String locationCountryId;
    @SerializedName("location_telephone")
    @Expose
    private String locationTelephone;
    @SerializedName("location_lat")
    @Expose
    private String locationLat;
    @SerializedName("location_lng")
    @Expose
    private String locationLng;
    @SerializedName("location_radius")
    @Expose
    private String locationRadius;
    @SerializedName("offer_delivery")
    @Expose
    private String offerDelivery;
    @SerializedName("offer_collection")
    @Expose
    private String offerCollection;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("last_order_time")
    @Expose
    private String lastOrderTime;
    @SerializedName("reservation_time_interval")
    @Expose
    private String reservationTimeInterval;
    @SerializedName("reservation_stay_time")
    @Expose
    private String reservationStayTime;
    @SerializedName("location_status")
    @Expose
    private String locationStatus;
    @SerializedName("collection_time")
    @Expose
    private String collectionTime;
    @SerializedName("options")
    @Expose
    private String options;
    @SerializedName("location_image")
    @Expose
    private String locationImage;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationEmail() {
        return locationEmail;
    }

    public void setLocationEmail(String locationEmail) {
        this.locationEmail = locationEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationAddress1() {
        return locationAddress1;
    }

    public void setLocationAddress1(String locationAddress1) {
        this.locationAddress1 = locationAddress1;
    }

    public String getLocationAddress2() {
        return locationAddress2;
    }

    public void setLocationAddress2(String locationAddress2) {
        this.locationAddress2 = locationAddress2;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationPostcode() {
        return locationPostcode;
    }

    public void setLocationPostcode(String locationPostcode) {
        this.locationPostcode = locationPostcode;
    }

    public String getLocationCountryId() {
        return locationCountryId;
    }

    public void setLocationCountryId(String locationCountryId) {
        this.locationCountryId = locationCountryId;
    }

    public String getLocationTelephone() {
        return locationTelephone;
    }

    public void setLocationTelephone(String locationTelephone) {
        this.locationTelephone = locationTelephone;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(String locationLng) {
        this.locationLng = locationLng;
    }

    public String getLocationRadius() {
        return locationRadius;
    }

    public void setLocationRadius(String locationRadius) {
        this.locationRadius = locationRadius;
    }

    public String getOfferDelivery() {
        return offerDelivery;
    }

    public void setOfferDelivery(String offerDelivery) {
        this.offerDelivery = offerDelivery;
    }

    public String getOfferCollection() {
        return offerCollection;
    }

    public void setOfferCollection(String offerCollection) {
        this.offerCollection = offerCollection;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(String lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public String getReservationTimeInterval() {
        return reservationTimeInterval;
    }

    public void setReservationTimeInterval(String reservationTimeInterval) {
        this.reservationTimeInterval = reservationTimeInterval;
    }

    public String getReservationStayTime() {
        return reservationStayTime;
    }

    public void setReservationStayTime(String reservationStayTime) {
        this.reservationStayTime = reservationStayTime;
    }

    public String getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(String locationImage) {
        this.locationImage = locationImage;
    }

}
