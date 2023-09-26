package com.codecool.ehotel.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record Meal(MealType mealType, LocalTime prepDate) {

}
