package com.akhil.msassignment.model;

import com.akhil.msassignment.presenter.Presenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class})
public interface ModelComponent {
    public void inject(Presenter presenter);
}