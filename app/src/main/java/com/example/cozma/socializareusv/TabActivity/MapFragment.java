package com.example.cozma.socializareusv.TabActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cozma.socializareusv.CoreModules.UserClasses.User;
import com.example.cozma.socializareusv.CustomTextViews.MapWrapperLayout;
import com.example.cozma.socializareusv.Modules.ActivityRecognitionIntentService;
import com.example.cozma.socializareusv.Modules.GPSLocation;
import com.example.cozma.socializareusv.R;
import com.example.cozma.socializareusv.Utils.Util;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static com.example.cozma.socializareusv.R.id.map;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static GoogleMap mMap;
    public static View mainView;

    private MapWrapperLayout mapWrapperLayout;
    private SupportMapFragment supportMapFragment;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient mGoogleApiClientActivityRecognition;

    static Marker myMarker;
    GPSLocation gpsLocation;

    Context context;
    Geocoder geocoder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mainView == null) {
            loadMap(inflater, container);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            checkLocationEnable(getContext());

            gpsLocation = new GPSLocation();
            mGoogleApiClient = new GoogleApiClient
                    .Builder(getContext())
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            geocoder = new Geocoder(getActivity(), Locale.getDefault());

            mGoogleApiClientActivityRecognition = new GoogleApiClient.Builder(getActivity())
                    .addApi(ActivityRecognition.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            mGoogleApiClientActivityRecognition.connect();
        }
        return mainView;
    }

    private void loadMap(LayoutInflater inflater, ViewGroup container) {
        /**using a map fragment in a tab(which is a fragment) will create a problem. Refreshing the map, a duplicate id error will appear
         * To solve this issue, make sure the mainView will not be recreated*/
        if (mainView != null) {
            ViewGroup parent = (ViewGroup) mainView.getParent();
            GPSLocation gpslocation = new GPSLocation();
            gpslocation.getLocation(getActivity(), gpsLocationResult);
            if (parent != null) {
                parent.removeView(mainView);
            }
        }
        try {
            mainView = inflater.inflate(R.layout.activity_client_map_fragment, container, false);
            mapWrapperLayout = (MapWrapperLayout) mainView.findViewById(map);

            FragmentManager fm = getChildFragmentManager();
            if (supportMapFragment == null) {
                supportMapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragmentMap_frameLayout, supportMapFragment).commit();
                supportMapFragment.getMapAsync(this);
            }

        } catch (InflateException e) {
            e.printStackTrace();
            /**this means the map is already created so there is no need to be recreated, just return it*/
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.640275, 26.258869), 16));

        gpsLocation.getLocation(getActivity(), gpsLocationResult);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                getLocationInfo(latLng.latitude, latLng.longitude);
            }
        });
    }


    /**
     * Start update my location
     */
    public GPSLocation.LocationResult gpsLocationResult = new GPSLocation.LocationResult() {
        @Override
        public void gotLocation(Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            Log.i(TAG, "Location: " + lat + " " + lon);

            User.getInstance().getCurrentUser().getProfile().setLatitude(lat);
            User.getInstance().getCurrentUser().getProfile().setLongitude(lon);


            if (myMarker != null) {
                if (!(myMarker.getPosition().latitude == lat && myMarker.getPosition().longitude == lon)) {
                    myMarker.setPosition(new LatLng(lat, lon));
                }
            } else {
                myMarker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.current_location)));
            }
        }
    };


    /**
     * This function is for enable gps
     */
    public void checkLocationEnable(Context context) {
        if (!Util.isLocationEnabled(context)) {
            Util.askGpsPermission(context);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void getLocationInfo(double lat, double lng) {

        HttpGet httpGet = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?latlng="+ lat+","+lng +"&key=" + getResources().getString(R.string.google_maps_key));
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        showObjectLog(jsonObject);
        try {
            String locationType = jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("types").getString(0);
            Log.d("Premise", jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("types").getString(0));
            Toast.makeText(getActivity(), locationType, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void showObjectLog(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(o);
        customInfoLog("GSON Object", "Content", json);
    }
    public static void customInfoLog(String activityName, String viewId, String infoMessage) {
        Log.i("--->", " \n");
        Log.i("--->", activityName + "\n---------------------------------------------");
        Log.i("--->" + viewId + "       ", infoMessage);
        Log.i("--->", "---------------------------------------------\n");
        Log.i("--->", " ");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent i = new Intent(getActivity(), ActivityRecognitionIntentService.class);
        PendingIntent mActivityRecognitionPendingIntent = PendingIntent.getService(getActivity(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.e("ActivityRecogn", "Connected");

        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mGoogleApiClientActivityRecognition, 0, mActivityRecognitionPendingIntent);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("ActivityRecogn", "Connection suspended");

    }

}

