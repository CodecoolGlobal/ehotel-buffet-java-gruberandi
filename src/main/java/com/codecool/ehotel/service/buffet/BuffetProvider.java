package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuffetProvider implements BuffetService{

    private Random random = new Random();

    public List<Meal> Meals = new ArrayList<>();

    public void generateMeals(LocalDate timestamp, int amount) {
        for (MealType mealtype : MealType.values()) {
            Meal meal = new Meal(mealtype, timestamp);
            for (int i = 0; i < amount; i++) {
                Meals.add(meal);
            }
        }
    };
    public List<Meal> getFoodPortions() {
        return Meals;
    };
    public void makeNewPortion() {

    }

    @Override
    public void generateMeals(LocalDate timestamp) {

    }

    ;

    public void collectFoodWaste(LocalDate timeCheck) {

    };

    public void eatMeal(MealType mealToEat) {
        List<Meal> filteredMeals = filterMeal(mealToEat);
        removeFreshestMeal(filteredMeals);
    }

    private List<Meal> filterMeal(MealType mealToEat) {
        List<Meal> filteredMeals = new ArrayList<>();

        for (Meal meal : Meals) {
            if (meal.mealType().equals(mealToEat)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }

    private void removeFreshestMeal(List<Meal> filteredMeals) {
        Meal freshestMeal = filteredMeals.get(0);
        for (Meal meal : filteredMeals) {
            if (meal.prepDate().isAfter(freshestMeal.prepDate())) {
                freshestMeal = meal;
            }
        }
        Meals.remove(freshestMeal);
    }


}
