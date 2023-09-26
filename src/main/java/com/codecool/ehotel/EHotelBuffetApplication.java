package com.codecool.ehotel;

import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.service.buffet.BuffetProvider;
import com.codecool.ehotel.service.buffet.BuffetService;

import java.time.LocalDate;
import java.time.LocalTime;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        LocalTime date = LocalTime.now();
        // Initialize services
        BuffetService buffet = new BuffetProvider();
        buffet.generateMeals(date, 10);

        for (Meal meal : buffet.getFoodPortions()) {
        };
        LocalTime date2 = LocalTime.now();
        buffet.collectFoodWaste(date2);

        // Generate guests for the season


        // Run breakfast buffet


    }
}
