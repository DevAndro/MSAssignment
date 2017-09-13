package com.akhil.msassignment.presenter;

import com.akhil.msassignment.view.AgendaListFragment;
import com.akhil.msassignment.view.CalenderFragment;

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    public void inject(AgendaListFragment agendaListFragment);
    public void inject(CalenderFragment calenderFragment);
}