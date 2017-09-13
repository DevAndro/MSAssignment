package com.akhil.msassignment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhil.msassignment.R;
import com.akhil.msassignment.presenter.DaggerPresenterComponent;
import com.akhil.msassignment.presenter.PresenterInteractor;
import com.akhil.msassignment.presenter.PresenterModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AgendaListFragment extends Fragment implements ViewInteractor {

    @Inject
    PresenterInteractor mPresenter;

    private RecyclerView mRecyclerView = null;

    private List<AgendaItems> mAgendaItems;
    private DataAdapter mAdaper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAgendaItems = new ArrayList<AgendaItems>();
        buildDependency();
        mPresenter.getAgendaList();
    }

    private void buildDependency() {
        DaggerPresenterComponent.builder().presenterModule(new PresenterModule(this, getActivity().getApplication())).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agenda_list_fragment, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.agenda_list_recycler_view_id);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mAdaper = new DataAdapter();
        mRecyclerView.setAdapter(mAdaper);
        return view;
    }

    private class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
            holder.agendaTextId.setText(mAgendaItems.get(position).getAgenda());
            holder.dateFieldId.setText(mAgendaItems.get(position).getDate());
            holder.locationTextId.setText(mAgendaItems.get(position).getLocation());
            holder.weatherInfoId.setText(mAgendaItems.get(position).getWeatherInfo());
            holder.view.setTag(mAgendaItems.get(position));
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private View view;

            TextView dateFieldId;
            TextView agendaTextId;
            TextView locationTextId;
            TextView weatherInfoId;

            public ViewHolder(View view) {
                super(view);
                this.view = view;
                dateFieldId = (TextView) view.findViewById(R.id.date_field_id);
                agendaTextId = (TextView) view.findViewById(R.id.agenda_text_id);
                locationTextId = (TextView) view.findViewById(R.id.location_text_id);
                weatherInfoId = (TextView) view.findViewById(R.id.weather_info_id);
            }
        }

        @Override
        public int getItemCount() {
            if (null != mAgendaItems) {
                return mAgendaItems.size();
            }
            return 0;
        }
    }

    @Override
    public void onDateSelected() {

    }

    @Override
    public void onAgendaItems(List<AgendaItems> items) {
        Log.d("Akhilesh", "incoming agenda items-> " + items);
        mAgendaItems = items;
        if (null != mAdaper) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdaper.notifyDataSetChanged();
                }
            });
        }
    }
}