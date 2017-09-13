package com.akhil.msassignment.model;

import android.app.Application;

import com.akhil.msassignment.presenter.PresenterInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    PresenterInteractor mPresenterInteractor;
    Application mApplication;

    public ModelModule(PresenterInteractor presenterInteractor, Application application) {
        mPresenterInteractor = presenterInteractor;
        mApplication = application;
    }

    @Provides
    public ModelInteractor injectModel() {
        return new Model(mPresenterInteractor, mApplication);
    }
}