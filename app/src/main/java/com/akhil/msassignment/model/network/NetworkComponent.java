package com.akhil.msassignment.model.network;

import com.akhil.msassignment.ApplicationModule;
import com.akhil.msassignment.model.Model;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(Model model);
}
