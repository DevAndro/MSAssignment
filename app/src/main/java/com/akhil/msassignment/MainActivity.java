package com.akhil.msassignment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.akhil.msassignment.presenter.PresenterInteractor;
import com.akhil.msassignment.view.AgendaListFragment;
import com.akhil.msassignment.view.CalenderFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String TAG_CALENDAR_FRAGMENT = "tag_calendar_fragment";
    private static final String TAG_AGENDA_LIST_FRAGMENT = "tag_agenda_list_fragment";

    private String[] mRequiredPermission = {Manifest.permission.READ_CALENDAR};
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Inject
    PresenterInteractor mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(MainActivity.this, mRequiredPermission, PERMISSION_REQUEST_CODE);
        setContentView(R.layout.activity_main);
    }

    private void init() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(TAG_CALENDAR_FRAGMENT);
        if (null == fragment) {
            fragment = new CalenderFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.calendar_place_holder, fragment, TAG_CALENDAR_FRAGMENT);
            ft.commit();
        }
        fragment = fm.findFragmentByTag(TAG_AGENDA_LIST_FRAGMENT);
        if (null == fragment) {
            FragmentTransaction ft = fm.beginTransaction();
            fragment = new AgendaListFragment();
            ft.replace(R.id.agenda_list_place_holder, fragment, TAG_AGENDA_LIST_FRAGMENT);
            ft.commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                    init();
                } else {
                    finish();
                }
        }
    }
}
