package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface BuffetService {
     List<Meal> getFoodPortions();
     void makeNewPortion(MealType mealtype, LocalTime timestamp, int amount);
     void collectFoodWaste(LocalTime timeCheck);
     void generateMeals(LocalTime timestamp, int amount);
     void eatMeal(MealType meal);
}
