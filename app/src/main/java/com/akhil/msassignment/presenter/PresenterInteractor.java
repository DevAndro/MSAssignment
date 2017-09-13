package com.akhil.msassignment.presenter;

import com.akhil.msassignment.view.AgendaItems;

import java.util.List;

public interface PresenterInteractor {
    public void getWeatherData();
    public void getAgendaList();
    public void onCalendarData(List<AgendaItems> items);
}