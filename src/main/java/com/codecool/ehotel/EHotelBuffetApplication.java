package com.codecool.ehotel;

import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.service.buffet.BuffetProvider;
import com.codecool.ehotel.service.buffet.BuffetService;

import java.time.LocalDate;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        LocalDate date = LocalDate.parse("2023-09-26");
        // Initialize services
        BuffetService buffet = new BuffetProvider();
        buffet.generateMeals(date);

        for (Meal meal : buffet.getFoodPortions()) {
            System.out.println(meal.mealType());
        };


        // Generate guests for the season


        // Run breakfast buffet


    }
}
