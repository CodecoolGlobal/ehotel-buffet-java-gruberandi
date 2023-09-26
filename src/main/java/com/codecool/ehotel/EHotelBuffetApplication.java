package com.codecool.ehotel;


import com.codecool.ehotel.service.guest.GenerateRandomGuest;

public class EHotelBuffetApplication {
    public static void main(String[] args) {
        GenerateRandomGuest guest = new GenerateRandomGuest();
        guest.getSeason();
        guest.generateGuestList(50);

    }
}
