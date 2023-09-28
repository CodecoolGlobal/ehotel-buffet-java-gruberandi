package com.codecool.ehotel.service.BreakfastManager;

public interface BreakfastService {
    void serveBreakfast();
    void getOptimalPortions(int businessAmount, int touristAmount, int kidAmount);
}
