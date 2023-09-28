package com.codecool.ehotel.service.BreakfastManager;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetProvider;
import com.codecool.ehotel.service.guest.GenerateGuestList;
import com.codecool.ehotel.service.guest.GenerateRandomGuest;
import com.codecool.ehotel.service.season.SeasonGetter;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.ehotel.model.GuestType.*;

public class BreakfastManager implements BreakfastService{

    SeasonGetter seasonGetter = new SeasonGetter();
    GenerateGuestList guestList = new GenerateGuestList();
    GenerateRandomGuest randomGuests = new GenerateRandomGuest();
    ArrayList<Guest> guests;
    ArrayList<ArrayList<Guest>> guestsForTheDay;
    BuffetProvider buffet = new BuffetProvider();
    int  sadCostumer = 0;
    int happyCostumer = 0;
    int guestamount = 0;
    List<Meal> Meals;

    public void serveBreakfast() {
        seasonGetter.getSeason();
        LocalDate seasonStart = seasonGetter.getSeasonStart();
        LocalDate seasonEnd = seasonGetter.getSeasonEnd();
        guests = randomGuests.generateGuestList(100, seasonStart, seasonEnd);



        long actualDate = 0;
        long seasonLength = ChronoUnit.DAYS.between(seasonStart, seasonEnd) + 1;

       for (int i = 0; i < seasonLength; i++) {
           LocalTime actualTime = LocalTime.of(6,0);
           List<Integer> remainingGuests;
           guestsForTheDay = guestList.getTheGuestForDay(i, guests, seasonStart, seasonEnd);
           for (int j = 0; j < guestsForTheDay.size(); j++) {
               System.out.println("guestfortheday");
               System.out.println(guestsForTheDay.get(j).size());
               remainingGuests = countGuestType(guestsForTheDay, j);
               getOptimalPortions(remainingGuests.get(0), remainingGuests.get(1), remainingGuests.get(2), actualTime);
               eatBreakfast(guestsForTheDay.get(j));
               actualTime = actualTime.plusMinutes( 30);
               buffet.collectFoodWaste(actualTime);
           }
       }
       System.out.println("Unhappy guests");
       System.out.println(sadCostumer);
       System.out.println("happy guests");
       System.out.println(happyCostumer);
       System.out.println("Loss:");
       System.out.println(buffet.getLoss());
       System.out.println("Guestamount");
       System.out.println(guestamount);
    };
    public void getOptimalPortions(int businessAmount, int touristAmount, int kidAmount, LocalTime actualTime) {
        buffet.generateMeals(actualTime, 1);
    };

    public void eatBreakfast(ArrayList<Guest> guests) {
        Meals = buffet.getFoodPortions();
        guestamount += guests.size();
        for (Guest guest : guests) {

            boolean eaten = false;
            for (MealType meal : guest.guestType().getMealPreferences()) {
                List<Meal> getMeals = buffet.filterMeal(meal);
                if (!eaten && !getMeals.isEmpty()) {
                    buffet.eatMeal(meal);
                    eaten = true;
                    happyCostumer++;
                }
            }
            if (!eaten) {
                sadCostumer++;
            }
        }
    }
    private List<Integer> countGuestType(ArrayList<ArrayList<Guest>> Guests, int cycles) {
        List<Integer> guestCount = new ArrayList<>();
        int businessCount = 0;
        int touristCount = 0;
        int kidCount = 0;
        for ( int i = 0; i < Guests.size() - cycles; i++) {
            for (Guest guest : Guests.get(cycles + i)) {
                switch (guest.guestType()) {
                    case BUSINESS -> businessCount++;
                    case KID -> kidCount++;
                    case TOURIST -> touristCount++;
                }

            }
        }
        guestCount.add(businessCount);
        guestCount.add(touristCount);
        guestCount.add(kidCount);
        return guestCount;
    }
}
