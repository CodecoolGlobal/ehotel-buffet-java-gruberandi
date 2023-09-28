package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuffetProvider implements BuffetService{

    private Random random = new Random();

    public List<Meal> Meals = new ArrayList<>();

    public int loss = 0;

    public void generateMeals(LocalTime timestamp, int amount) {
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
    public void makeNewPortion(MealType mealtype, LocalTime timestamp, int amount) {
        Meal meal = new Meal(mealtype, timestamp);
        for (int i = 0; i < amount; i++) {
            Meals.add(meal);
        }
    };

    public void collectFoodWaste(LocalTime timeCheck) {
        LocalTime endOfBreakfast = LocalTime.of(10,0);
        ArrayList<Meal> rottenFoods = new ArrayList<>();
        for (Meal meal : Meals) {
            LocalTime endDate = meal.prepDate().plusMinutes(90);
            if (meal.mealType().getDurability().equals(MealDurability.SHORT) && (endDate.isBefore(timeCheck) || endDate.equals(timeCheck))) {
                rottenFoods.add(meal);
                System.out.println(meal.mealType());
            } else if (meal.mealType().getDurability().equals(MealDurability.MEDIUM) && timeCheck.equals(endOfBreakfast)) {
                loss += meal.mealType().getCost();
                rottenFoods.add(meal);
                System.out.println(meal.mealType());
            }

        }
        for (Meal meal : rottenFoods) {
            Meals.remove(meal);
        }
    }

    public void eatMeal(MealType mealToEat) {
        List<Meal> filteredMeals = filterMeal(mealToEat);
        removeFreshestMeal(filteredMeals);
    }

    public List<Meal> filterMeal(MealType mealToEat) {
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

    public int getLoss() {return loss;}


}
