package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.util.List;

public interface BuffetService {
     List<Meal> getFoodPortions();
     void makeNewPortion();

     void removeBadMeals();

     void generateMeals(LocalDate timestamp);

     void eatMeal(MealType meal);
}
