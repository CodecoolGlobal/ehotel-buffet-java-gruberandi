package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

public class GenerateGuestList {

    Random random = new Random();

    private ArrayList<Guest> getGuestForActualDate (ArrayList<Guest> guests, LocalDate actualDate) {
        ArrayList<Guest> actualGuests = new ArrayList<>();

        for (Guest guest : guests) {
            if(guest.checkIn().isBefore(actualDate) && guest.checkOut().isAfter(actualDate) ) {
                actualGuests.add(guest);
            }
        }
        return actualGuests;
    }

    private ArrayList<ArrayList<Guest>> getCycleList(ArrayList<Guest> guests, LocalDate starterDay , LocalDate endDate) {
        long seasonDays = ChronoUnit.DAYS.between(starterDay,endDate);

        ArrayList<ArrayList<Guest>> listForCycle = new ArrayList<>();

        for (int i = 1; i < seasonDays;i++) {

            listForCycle.add(getGuestForActualDate(guests,starterDay.plusDays(i)));
        }
        return listForCycle;
    }

    private ArrayList<ArrayList<Guest>> getGuestForaCycle (ArrayList<ArrayList<Guest>> allGuest, int actualDay) {

        ArrayList<ArrayList<Guest>> saveAllGuest = allGuest;

        ArrayList<ArrayList<Guest>> guestForTheActualCycle = new ArrayList<>();
        int getGuestNumbers = allGuest.get(actualDay).size();
        for (int j = 0; j < 8; j++) {
            int getListSize = Math.round(random.nextFloat(getGuestNumbers) / 8);
            ArrayList<Guest> choosedGuest = new ArrayList<>(getListSize);

            for (int i = 0; i < getListSize; i++) {
                choosedGuest.add(saveAllGuest.get(actualDay).get(i));
            }
            guestForTheActualCycle.add(choosedGuest);
        }

        System.out.println(guestForTheActualCycle + "getGuestForACycle");
        return guestForTheActualCycle;
    }

    public ArrayList<ArrayList<Guest>> getTheGuestForDay (int actualDay,ArrayList<Guest> guests, LocalDate starterDay , LocalDate endDate) {

        return (getGuestForaCycle(getCycleList(guests, starterDay, endDate),actualDay));
    }
}
