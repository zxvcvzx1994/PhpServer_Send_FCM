package vo.cvcompany.com.myapplication.Module;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import vo.cvcompany.com.myapplication.R;

/**
 * Created by kh on 7/30/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "FCM" ;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.FCM_PREF), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_TOKEN),refreshedToken);
        editor.commit();
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
      //  sendRegistrationToServer(refreshedToken);
    }
}
