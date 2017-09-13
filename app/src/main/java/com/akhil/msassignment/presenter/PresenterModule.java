package com.akhil.msassignment.presenter;

import android.app.Application;

import com.akhil.msassignment.view.ViewInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    ViewInteractor mViewInteractor;
    Application mApplication;

    public PresenterModule(ViewInteractor viewInteractor, Application application) {
        mViewInteractor = viewInteractor;
        mApplication = application;
    }

    @Provides
    public PresenterInteractor inject() {
        return new Presenter(mViewInteractor, mApplication);
    }
}