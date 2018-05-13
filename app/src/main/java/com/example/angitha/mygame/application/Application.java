package com.example.angitha.mygame.application;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;

import static com.example.angitha.mygame.utils.Constants.KEY_CURRENT_VERSION;
import static com.example.angitha.mygame.utils.Constants.KEY_UPDATE_REQUIRED;
import static com.example.angitha.mygame.utils.Constants.KEY_UPDATE_URL;

public class Application extends android.app.Application {

    private static Application mInstance = null;
    private static final String TAG = Application.class.getSimpleName();

    //Setters
    public static synchronized Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initializeRemoteConfig();
    }

    @Override
    public void onTerminate() {
        mInstance = null;
        super.onTerminate();
    }

    public void initializeRemoteConfig(){
        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        // set in-app defaults
        Map<String, Object> remoteConfigDefaults = new HashMap();
        remoteConfigDefaults.put(KEY_UPDATE_REQUIRED, false);
        remoteConfigDefaults.put(KEY_CURRENT_VERSION, "1.0.0");
        remoteConfigDefaults.put(KEY_UPDATE_URL,
                "https://play.google.com/store/apps/details?"+getPackageName());

        firebaseRemoteConfig.setDefaults(remoteConfigDefaults);
        firebaseRemoteConfig.fetch(60) // fetch every minutes
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "remote config is fetched.");
                            firebaseRemoteConfig.activateFetched();
                        }
                    }
                });
    }
}
