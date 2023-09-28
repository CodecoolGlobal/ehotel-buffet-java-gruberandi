package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface GuestService {

    Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd);
    String pickName(String[] names);
    GuestType pickAGuestType();
    ArrayList<Guest> generateGuestList(int guestNumber, LocalDate seasonStart, LocalDate seasonEnd);

}
