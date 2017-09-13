package com.akhil.msassignment;

import android.app.Application;

import com.akhil.msassignment.model.network.DaggerNetworkComponent;
import com.akhil.msassignment.model.network.NetworkComponent;
import com.akhil.msassignment.model.network.NetworkModule;

public class MSAApplication extends Application{
    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildDependency();
    }

    private void buildDependency() {
        mNetworkComponent = DaggerNetworkComponent.builder().applicationModule(new ApplicationModule(this)).networkModule(new NetworkModule()).build();
    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }
}