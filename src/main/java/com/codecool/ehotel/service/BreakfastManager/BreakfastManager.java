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

   private final SeasonGetter seasonGetter;
   private final GenerateGuestList guestList;
   private final GenerateRandomGuest randomGuests;
   private final BuffetProvider buffet;
    ArrayList<Guest> guests;
    ArrayList<ArrayList<Guest>> guestsForTheDay;

   private int  sadCostumer = 0;
   private int happyCostumer = 0;
   private int guestamount = 0;
    List<Meal> Meals;

   public BreakfastManager(SeasonGetter seasonGetter, GenerateGuestList generateGuestList, GenerateRandomGuest generateRandomGuest,BuffetProvider buffetProvider) {
        this.seasonGetter = seasonGetter;
        this.guestList = generateGuestList;
        this.randomGuests = generateRandomGuest;
        this.buffet = buffetProvider;
    }

    public void serveBreakfast() {
        seasonGetter.getSeason();
        LocalDate seasonStart = seasonGetter.getSeasonStart();
        LocalDate seasonEnd = seasonGetter.getSeasonEnd();
        guests = randomGuests.generateGuestList(100, seasonStart, seasonEnd);
        long seasonLength = ChronoUnit.DAYS.between(seasonStart, seasonEnd) + 1;

       for (int i = 0; i < seasonLength; i++) {
           LocalTime actualTime = LocalTime.of(6,0);
           List<Integer> remainingGuests;
           guestsForTheDay = guestList.getTheGuestForDay(i, guests, seasonStart, seasonEnd);
           for (int j = 0; j < guestsForTheDay.size(); j++) {
               System.out.println("guest numbers for the actual cycle" + "(" +actualTime +"): " + guestsForTheDay.get(j).size() );
               remainingGuests = countGuestType(guestsForTheDay, j);
               getOptimalPortions(remainingGuests.get(0), remainingGuests.get(1), remainingGuests.get(2), actualTime);
               eatBreakfast(guestsForTheDay.get(j));
               actualTime = actualTime.plusMinutes( 30);
               buffet.collectFoodWaste(actualTime);
           }
       }

       System.out.println();
       System.out.println("Total details:");
       System.out.println("Unhappy guests: " + sadCostumer);
       System.out.println("Happy guests: " + happyCostumer);
       System.out.println("Loss: " + buffet.getLoss());
       System.out.println("Guest amount: " + guestamount);
    };
    public void getOptimalPortions(int businessAmount, int touristAmount, int kidAmount, LocalTime actualTime) {
        buffet.generateMeals(actualTime, 1);
    };

    private void eatBreakfast(ArrayList<Guest> guests) {
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
