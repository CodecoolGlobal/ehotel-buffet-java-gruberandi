package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuffetProvider implements BuffetService{

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

    };

    public void removeBadMeals() {
    };

    public void eatMeal(MealType mealToEat) {
        for ( Meal meal : Meals) {
            if (meal.mealType().equals(mealToEat)) {

            }
        }
    }
}
