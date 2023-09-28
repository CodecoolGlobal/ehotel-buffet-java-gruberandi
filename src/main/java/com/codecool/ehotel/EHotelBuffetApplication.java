package com.codecool.ehotel;


import com.codecool.ehotel.service.BreakfastManager.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetProvider;
import com.codecool.ehotel.service.guest.GenerateGuestList;
import com.codecool.ehotel.service.guest.GenerateRandomGuest;
import com.codecool.ehotel.service.season.SeasonGetter;

import java.time.LocalDate;
import java.time.LocalTime;

public class EHotelBuffetApplication {
    public static void main(String[] args) {

        GenerateRandomGuest guest = new GenerateRandomGuest();
        BuffetProvider buffet = new BuffetProvider();
        GenerateGuestList guestList = new GenerateGuestList();
        LocalDate seasonStart = LocalDate.of(2020, 3, 10);
        LocalDate seasonEnd =LocalDate.of(2020, 3, 13);
        BreakfastManager breakfastManager = new BreakfastManager();
        breakfastManager.serveBreakfast();



        //guest.generateGuestList(50);

    }
}
