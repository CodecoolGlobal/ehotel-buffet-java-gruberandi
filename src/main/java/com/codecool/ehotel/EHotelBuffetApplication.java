package com.codecool.ehotel;


import com.codecool.ehotel.service.buffet.BuffetProvider;
import com.codecool.ehotel.service.guest.GenerateGuestList;
import com.codecool.ehotel.service.guest.GenerateRandomGuest;

import java.time.LocalDate;

public class EHotelBuffetApplication {
    public static void main(String[] args) {
        GenerateRandomGuest guest = new GenerateRandomGuest();
        BuffetProvider buffet = new BuffetProvider();
        GenerateGuestList guestList = new GenerateGuestList();
        LocalDate seasonStart = LocalDate.of(2020, 3, 10);
        LocalDate seasonEnd =LocalDate.of(2020, 3, 13);



       for (int i = 0; i < 2; i++) {
           guestList.getTheGuestForDay(i,guest.generateGuestList(500),seasonStart,seasonEnd);
       }





        //guest.generateGuestList(50);

    }
}
