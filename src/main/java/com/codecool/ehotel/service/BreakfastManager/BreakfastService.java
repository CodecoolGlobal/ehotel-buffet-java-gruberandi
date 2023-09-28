package com.codecool.ehotel.service.BreakfastManager;

import java.time.LocalTime;

public interface BreakfastService {
    void serveBreakfast();
    void getOptimalPortions(int businessAmount, int touristAmount, int kidAmount, LocalTime actualTime);
}
