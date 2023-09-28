package com.codecool.ehotel;


import com.codecool.ehotel.service.BreakfastManager.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetProvider;
import com.codecool.ehotel.service.guest.GenerateGuestList;
import com.codecool.ehotel.service.guest.GenerateRandomGuest;
import com.codecool.ehotel.service.season.SeasonGetter;


public class EHotelBuffetApplication {
    public static void main(String[] args) {
        SeasonGetter seasonGetter = new SeasonGetter();
        GenerateRandomGuest guest = new GenerateRandomGuest();
        BuffetProvider buffet = new BuffetProvider();
        GenerateGuestList guestList = new GenerateGuestList();

        BreakfastManager breakfastManager = new BreakfastManager(seasonGetter,guestList,guest,buffet);


        breakfastManager.serveBreakfast();

    }
}
