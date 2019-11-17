package ru.otus.probiv;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Maxim Firsov on 2019-11-17.
 * PowerDot
 * firsoffmaxim@gmail.com
 */
public class ProbivApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // setup Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
