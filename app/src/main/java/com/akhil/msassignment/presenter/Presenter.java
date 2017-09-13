package com.akhil.msassignment.presenter;

import android.app.Application;
import android.util.Log;

import com.akhil.msassignment.model.DaggerModelComponent;
import com.akhil.msassignment.model.ModelInteractor;
import com.akhil.msassignment.model.ModelModule;
import com.akhil.msassignment.view.AgendaItems;
import com.akhil.msassignment.view.ViewInteractor;

import java.util.List;

import javax.inject.Inject;

public class Presenter implements PresenterInteractor {

    private ViewInteractor mView;
    private Application mApplication;

    @Inject
    ModelInteractor mModel;

    public Presenter(ViewInteractor viewInteractor, Application application) {
        mView = viewInteractor;
        mApplication = application;
        buildDependency();
    }/*

    public Presenter(ModelInteractor modelInteractor) {
        buildDependency();
        mModel = modelInteractor;
    }*/

    private void buildDependency() {
        DaggerModelComponent.builder().modelModule(new ModelModule(this, mApplication)).build().inject(this);
    }

    @Override
    public void getWeatherData() {
        mModel.getWeatherData();
    }

    @Override
    public void getAgendaList() {
        mModel.getAgendaList();
    }

    @Override
    public void onCalendarData(List<AgendaItems> items) {
        mView.onAgendaItems(items);
    }
}