package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
        long seasonDays = ChronoUnit.DAYS.between(starterDay,endDate) + 1;

        ArrayList<ArrayList<Guest>> listForCycle = new ArrayList<>();

        for (int i = 0; i <= seasonDays;i++) {
            listForCycle.add(getGuestForActualDate(guests,starterDay.plusDays(i)));
        }

        return listForCycle;
    }

    private ArrayList<ArrayList<Guest>> getGuestForaCycle (ArrayList<ArrayList<Guest>> allGuest, int actualDay) {

        ArrayList<Guest> dailyGuestList = allGuest.get(actualDay);
        System.out.println();
        System.out.println("Guest numbers for the actual day(" + actualDay + "): " + dailyGuestList.size());
        System.out.println();
        ArrayList<ArrayList<Guest>> guestForTheActualCycle = new ArrayList<>();
        for ( int i = 0; i < 8; i++) {
            ArrayList<Guest> emptyArray = new ArrayList<>();
            guestForTheActualCycle.add(emptyArray);
        }

        for (Guest guest : dailyGuestList) {
            int randomCycleIndex = random.nextInt(0,8)  ;
            guestForTheActualCycle.get(randomCycleIndex).add(guest);
        }
        int index = 0;
        for (ArrayList<Guest> guests : guestForTheActualCycle) {
            index++;
        }

        return guestForTheActualCycle;
    }

    public ArrayList<ArrayList<Guest>> getTheGuestForDay (int actualDay,ArrayList<Guest> guests, LocalDate starterDay , LocalDate endDate) {
        return (getGuestForaCycle(getCycleList(guests, starterDay, endDate),actualDay));
    }
}
