package com.akhil.msassignment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.akhil.msassignment.R;
import com.akhil.msassignment.presenter.DaggerPresenterComponent;
import com.akhil.msassignment.presenter.PresenterInteractor;
import com.akhil.msassignment.presenter.PresenterModule;

import java.util.List;

import javax.inject.Inject;

public class CalenderFragment extends Fragment implements ViewInteractor {

    @Inject
    PresenterInteractor mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildDependency();
    }

    private void buildDependency() {
        DaggerPresenterComponent.builder().presenterModule(new PresenterModule(this, getActivity().getApplication())).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*Button btn = (Button)getActivity().findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getWeatherData();
            }
        });*/
    }

    @Override
    public void onDateSelected() {

    }

    @Override
    public void onAgendaItems(List<AgendaItems> items) {

    }
}