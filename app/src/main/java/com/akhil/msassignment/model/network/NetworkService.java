package com.akhil.msassignment.model.network;

import android.support.v4.util.LruCache;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.CallAdapter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;

public class NetworkService {
    private static final String BASE_URL = "https://query.yahooapis.com";
    private static final int LRU_CACHE_MAX_SIZE = 5;
    private LruCache<Class<?>, Observable<?>> mServiceApiObserverable;
    private NetworkServiceApi mNetworkServiceApi;

    public NetworkService() {
        this(BASE_URL);
    }

    public NetworkService(String baseUrl) {
        mServiceApiObserverable = new LruCache<>(LRU_CACHE_MAX_SIZE);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mNetworkServiceApi = retrofit.create(NetworkServiceApi.class);
    }

    /**
     * Clears observable's cache
     */
    public void clearCache() {
        if (null != mServiceApiObserverable)
            mServiceApiObserverable.evictAll();
    }

    /**
     * Either returns cached observable or create a new one.
     *
     * @param plainObserverable
     * @param cls
     * @param cachedObservable
     * @param useCache
     * @return New or cached observable
     */
    public Observable<?> getReadyObserverable(Observable<?> plainObserverable, Class<?> cls, boolean cachedObservable, boolean useCache) {
        Observable<?> readyObserverable = null;

        if (useCache) {
            readyObserverable = mServiceApiObserverable.get(cls);
        }
        if (null != readyObserverable) {
            return readyObserverable;
        }

        readyObserverable = plainObserverable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Log.d("Akhilesh", "ready observerable is-> " + readyObserverable);

        if (cachedObservable) {
            readyObserverable = readyObserverable.cache();
            mServiceApiObserverable.put(cls, readyObserverable);
        }
        return readyObserverable;
    }

    public NetworkServiceApi getNetworkServiceApi() {
        return mNetworkServiceApi;
    }
}
