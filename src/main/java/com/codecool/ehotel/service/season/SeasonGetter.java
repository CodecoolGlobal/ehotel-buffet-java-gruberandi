package com.codecool.ehotel.service.season;
import com.codecool.ehotel.Logger.ConsoleLogger;

import java.time.LocalDate;
import java.util.Scanner;

public class SeasonGetter implements GetSeason{
    ConsoleLogger logger = new ConsoleLogger();
    Scanner scanner = new Scanner(System.in);

    public LocalDate seasonStart;
    public LocalDate seasonEnd;
    @Override
    public void getSeason(){
        logger.logInfo("Give a Start date (YYYY-MM-DD):");
        String sDate = scanner.nextLine();
        LocalDate starterDate = LocalDate.parse(sDate);
        logger.logInfo("Give a End date (YYYY-MM-DD):");
        String eDate = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(eDate);
        this.seasonStart = starterDate;
        this.seasonEnd = endDate;
        System.out.println(seasonStart);
        System.out.println(seasonEnd);

    }

    public LocalDate getSeasonStart() {
        return seasonStart;
    }

    public LocalDate getSeasonEnd() {
        return seasonEnd;
    }
}
