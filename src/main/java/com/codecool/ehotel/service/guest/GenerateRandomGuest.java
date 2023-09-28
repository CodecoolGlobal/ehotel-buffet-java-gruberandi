package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.Logger.ConsoleLogger;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.service.season.GetSeason;
import com.codecool.ehotel.service.season.SeasonGetter;

import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;


public class GenerateRandomGuest implements GuestService{
    Random random = new Random();
    String[] names = {"Alex" ,"Mia", "Zoe","Max"};



    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
       long startDate = seasonStart.toEpochDay();
       long endDate = seasonEnd.toEpochDay();
       long seasonLong = endDate-startDate;
       LocalDate generateRandomStartDate = LocalDate.ofEpochDay(random.nextLong(startDate - 1, endDate + 1));
       long epochRandomStartDay = generateRandomStartDate.toEpochDay();
       long randomDay = random.nextLong(7);
       long randomDayBetweenSeasonLong = random.nextLong(seasonLong);
       LocalDate CheckoutDate = LocalDate.ofEpochDay(epochRandomStartDay + randomDay);
       LocalDate CheckoutDateWithSortSeason = LocalDate.ofEpochDay(epochRandomStartDay + randomDayBetweenSeasonLong);
      if(seasonLong > 7){
         return new Guest(pickName(names),pickAGuestType(),generateRandomStartDate,CheckoutDate);
      }else {
         return new Guest(pickName(names), pickAGuestType(), generateRandomStartDate, CheckoutDateWithSortSeason);
      }
    }

    @Override
    public String pickName(String[] names){
        String name = names[random.nextInt(0,names.length)];
        return name;
    }
    @Override
    public  GuestType pickAGuestType(){
        ArrayList<GuestType> guestTypes = new ArrayList<>( Arrays.asList(GuestType.KID,
                                                                         GuestType.BUSINESS,
                                                                            GuestType.TOURIST));
        GuestType guest = guestTypes.get(random.nextInt(0, guestTypes.size()));
        return  guest;
    }
    @Override
   public ArrayList<Guest> generateGuestList(int guestNumber, LocalDate seasonStart, LocalDate seasonEnd){

       ArrayList<Guest> allGuest= new ArrayList<>();
       for(int i = 0; i < guestNumber; i++){
          allGuest.add(generateRandomGuest(seasonStart, seasonEnd));
       }
       return  allGuest;
   }
}




