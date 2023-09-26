package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Meal;
import java.util.List;

public interface BuffetService {
     List<Meal> getFoodPortions();
     void makeNewPortion();

     void removeBadMeals();

     void generateMeals();
}
