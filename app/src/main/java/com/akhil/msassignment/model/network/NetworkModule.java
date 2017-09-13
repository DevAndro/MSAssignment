package com.akhil.msassignment.model.network;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    public NetworkModule() {
    }

    @Provides
    @Singleton
    public NetworkService providesNetworkService(Application application) {
        return new NetworkService();
    }
}
