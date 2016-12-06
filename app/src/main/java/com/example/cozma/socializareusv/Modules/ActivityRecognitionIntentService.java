package com.example.cozma.socializareusv.Modules;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

/**
 * Created by cozma on 02.12.2016.
 */

public class ActivityRecognitionIntentService extends IntentService {
    Handler mainHandler;
    public ActivityRecognitionIntentService(){
        super("ActivityRecognitionIntentService");
        mainHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            DetectedActivity mostProbAct = result.getMostProbableActivity();
            int confidence = mostProbAct.getConfidence();
            final String mostProbActName = getActivityName(mostProbAct.getType());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), mostProbActName, Toast.LENGTH_SHORT).show();
                }
            });
            Intent i = new Intent("ActRec");
            i.putExtra("act", mostProbActName);
            i.putExtra("confidence", confidence);
        } else{
            Log.e("ActivityRecognition", "Intent had no Activity Recognition Data");
        }
        mainHandler = new Handler(Looper.getMainLooper());
    }

    private String getActivityName(int activityType){
        switch (activityType){
            case DetectedActivity.IN_VEHICLE:
                return "in_vehicle";
            case DetectedActivity.ON_BICYCLE:
                return "on_bycycle";
            case DetectedActivity.ON_FOOT:
                return "on_foot";
            case DetectedActivity.STILL:
                return "still";
            case DetectedActivity.RUNNING:
                return "running";
            case DetectedActivity.TILTING:
                return "tilting";
            case DetectedActivity.UNKNOWN:
                return "unknown";
        }
        return "unknown";
    }

}
