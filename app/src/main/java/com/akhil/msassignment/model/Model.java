package com.akhil.msassignment.model;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.akhil.msassignment.MSAApplication;
import com.akhil.msassignment.model.json.WeatherData;
import com.akhil.msassignment.model.network.NetworkService;
import com.akhil.msassignment.presenter.PresenterInteractor;
import com.akhil.msassignment.view.AgendaItems;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class Model implements ModelInteractor {
    private static final String TAG = Model.class.getSimpleName();
    private PresenterInteractor mPresenter;
    private Application mApplication;

    @Inject
    public NetworkService mNetworkService;

    private Subscription mSubscription;

    private List<AgendaItems> mAgendaItems;

    public Model(PresenterInteractor presenterInteractor, Application application) {
        mPresenter = presenterInteractor;
        mApplication = application;
        Log.d("Akhilesh", "incoming application-> " + mApplication);
        buildDependency();
        mAgendaItems = new ArrayList<AgendaItems>();
    }

    private void buildDependency() {
        ((MSAApplication) mApplication).getNetworkComponent().inject(this);
    }

    @Override
    public void getWeatherData() {
        Log.d("Akhilesh", "getting weather data mNetworkService-> " + mNetworkService);
        String cityQuery = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D\\\"amsterdam\\\")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        Observable<WeatherData> response = (Observable<WeatherData>) mNetworkService.getReadyObserverable(mNetworkService.getNetworkServiceApi().getWeatherData(cityQuery), WeatherData.class, false, false);
        response.observeOn(Schedulers.io());
        mSubscription = response.subscribe(new Observer<WeatherData>() {
            @Override
            public void onCompleted() {
                Log.d("Akhilesh", "weather data loading completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Akhilesh", "got error " + e.getMessage());
            }

            @Override
            public void onNext(WeatherData weatherData) {
                Log.d("Akhilesh", "received weather data-> " + weatherData);
            }
        });
    }

    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public void getAgendaList() {
        /*Observable observable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                Cursor cursor = mApplication.getBaseContext().getContentResolver()
                        .query(
                                Uri.parse("content://com.android.calendar/events"),
                                new String[] { "calendar_id", "title", "description",
                                        "dtstart", "dtend", "eventLocation" }, null,
                                null, null);
                cursor.moveToFirst();
                String[] calendars = new String[cursor.getCount()];
                for (int i = 0; i < calendars.length; i++) {
                    String[] str = new String[4];
                    str[0] = cursor.getString(1);  //event name
                    str[1] = cursor.getString(3);  //date
                    str[2] = cursor.getString(5);  //location
                    str[3] = "NA";
                    cursor.moveToNext();

                }
            }
        });*/
        mAgendaItems.clear();
        Observable observable = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                Cursor cursor = mApplication.getContentResolver()
                        .query(
                                Uri.parse("content://com.android.calendar/events"),
                                new String[] { "calendar_id", "title", "description",
                                        "dtstart", "dtend", "eventLocation" }, null,
                                null, null);
                Log.d("Akhilesh", "cursor size-> " + cursor.getCount());
                cursor.moveToFirst();
                String[] calendars = new String[cursor.getCount()];
                for (int i = 0; i < calendars.length; i++) {
                    String[] str = new String[4];
                    str[0] = cursor.getString(1);  //event name
                    str[1] = getDate(Long.parseLong(cursor.getString(3)));  //date
                    str[2] = cursor.getString(5);  //location
                    str[3] = "NA";  //weather
                    cursor.moveToNext();
                    subscriber.onNext(str);
                    Log.d("Akhilesh", "cursor event name..-> " + str[0]);
                }
                cursor.close();
                subscriber.onCompleted();
            }
        });


        observable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Observer() {
                    @Override
                    public void onCompleted() {
                        mPresenter.onCalendarData(mAgendaItems);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        String[] strArr = (String[])o;
                        AgendaItems item = new AgendaItems();
                        Log.d("Akhilesh", "incoming event name -> " + strArr[0]);
                        item.setAgenda(strArr[0]);
                        item.setDate(strArr[1]);
                        item.setLocation(strArr[2]);
                        item.setWeatherInfo(strArr[3]);
                        mAgendaItems.add(item);
                    }
                });
    }
}