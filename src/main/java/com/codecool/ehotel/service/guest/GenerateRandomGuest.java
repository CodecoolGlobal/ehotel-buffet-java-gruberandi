package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.Logger.ConsoleLogger;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;


public class GenerateRandomGuest implements GuestService{
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    ConsoleLogger logger = new ConsoleLogger();
    String[] names = {"Alex" ,"Mia", "Zoe","Max"};
    LocalDate seasonStart = LocalDate.of(2020, 3, 10);
    LocalDate seasonEnd =LocalDate.of(2020, 3, 19);

    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
       long startDate = seasonStart.toEpochDay();
       long endDate = seasonEnd.toEpochDay();
       long seasonLong = endDate-startDate;
       LocalDate generateRandomStartDate = LocalDate.ofEpochDay(random.nextLong(endDate - startDate)+ startDate);
       long epochRandomStartDay = generateRandomStartDate.toEpochDay();
       long randomDay = random.nextLong(7);
       long randomDayBetweenSeasonLong = random.nextLong(seasonLong);
       LocalDate CheckoutDate = LocalDate.ofEpochDay(epochRandomStartDay + randomDay);
       LocalDate ChekoutDateWithSortSeason = LocalDate.ofEpochDay(epochRandomStartDay + randomDayBetweenSeasonLong);
      if(seasonLong > 7){
         return new Guest(pickName(names),pickAGuestType(),generateRandomStartDate,CheckoutDate);
      }else {
         return new Guest(pickName(names), pickAGuestType(), generateRandomStartDate, ChekoutDateWithSortSeason);
      }

    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {

        return null;
    }
    private String pickName(String[] names){
        String name = names[random.nextInt(0,names.length)];
        return name;
    }
    private  GuestType pickAGuestType(){
        ArrayList<GuestType> guestTypes = new ArrayList<>( Arrays.asList(GuestType.KID,
                                                                         GuestType.BUSINESS,
                                                                            GuestType.TOURIST));
        GuestType guest = guestTypes.get(random.nextInt(0, guestTypes.size()));
        return  guest;
    }
   public void getSeason(){
        logger.logInfo("Give a start date (YYYY-MM-DD):");
        String sDate = scanner.nextLine();
        LocalDate starterDate = LocalDate.parse(sDate);
       logger.logInfo("Give a End date (YYYY-MM-DD):");
       String eDate = scanner.nextLine();
       LocalDate endDate = LocalDate.parse(eDate);
        this.seasonStart = starterDate;
        this.seasonEnd = endDate;

   }
   public ArrayList<Guest> generateGuestList(int guestNumber){

       int randomGuestNUmber = random.nextInt(0,50);
       ArrayList<Guest> allGuest= new ArrayList<>();
       for(int i = 0; i < guestNumber; i ++){
          allGuest.add(generateRandomGuest(seasonStart,seasonEnd));

       }
       System.out.println(allGuest);
       return  allGuest;
   }

}



