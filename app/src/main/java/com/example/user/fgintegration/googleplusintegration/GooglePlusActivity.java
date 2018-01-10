package com.example.user.fgintegration.googleplusintegration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.user.fgintegration.MainActivity;
import com.example.user.fgintegration.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by USER on 09-01-2018.
 */

public class GooglePlusActivity extends AppCompatActivity  implements
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "GooglePlusActivity";
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.googleplus_activity);
        mGoogleApiClient = ((MyApplication)
                getApplication()).getGoogleApiClient(GooglePlusActivity.this, this);
        CircleImageView profilePhoto = (CircleImageView)findViewById(R.id.profile_image);
        TextView profileUsername = (TextView)findViewById(R.id.profile_name);
        TextView profileEmail = (TextView)findViewById(R.id.profile_email);
        String profileDisplayName = returnValueFromBundles(MainActivity.PROFILE_DISPLAY_NAME);
        String profileUserEmail = returnValueFromBundles(MainActivity.PROFILE_USER_EMAIL);
        String profileImageLink = returnValueFromBundles(MainActivity.PROFILE_IMAGE_URL);
        Picasso.with(GooglePlusActivity.this).load(profileImageLink).into(profilePhoto);
        assert profileUsername != null;
        profileUsername.setText(profileDisplayName);
        assert profileEmail != null;
        profileEmail.setText(profileUserEmail);
    }

    private String returnValueFromBundles(String key){
        Bundle inBundle = getIntent().getExtras();
        String returnedValue = inBundle.get(key).toString();
        return returnedValue;
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
