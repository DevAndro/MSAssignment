package com.akhil.msassignment.model.network;

import com.akhil.msassignment.model.json.WeatherData;

import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkServiceApi {
//@GET("/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D\"amsterdam\")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
//public Observable<WeatherData> getWeatherData();
    @GET("/v1/public/yql")
    public Observable<WeatherData> getWeatherData(@Query("q") String query);
}
