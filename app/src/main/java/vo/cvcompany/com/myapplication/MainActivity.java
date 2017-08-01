package vo.cvcompany.com.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vo.cvcompany.com.myapplication.Module.MySingleton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    private static final    String url="http://192.168.1.10/table_query.php";
    @BindView(R.id.etMessage)
    EditText etMessage;
    @BindView(R.id.etTitle)
    EditText etTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSendNotification)
    public void btnSendNotification(){
        SharedPreferences preferences = getSharedPreferences("token.txt", MODE_PRIVATE);
       final  String token =  preferences.getString(getString(R.string.FCM_TOKEN), FirebaseInstanceId.getInstance().getToken());
        Log.i(TAG, "btnSendNotification: 1111");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onResponse: "+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Log.i(TAG, "getParams: 1111111111111111 "+token);
                params.put("token", token);
                return params;
            }
        };
        MySingleton.getMySingleton(this).addVolley(stringRequest);
    }
    @OnClick(R.id.btnReceive)
    public void btnReceive(){

        String url_receive="http://192.168.1.10/init_notification.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_receive, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onResponse: "+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String title, message;
                title = etTitle.getText().toString();
                message = etMessage.getText().toString();
                if(title.length()==0 || message.length()==0){
                    Toast.makeText(MainActivity.this, "Chua nhap", Toast.LENGTH_SHORT).show();
                }else {

                    params.put("title", title);
                    params.put("message", message);

                }
                return params;
            }
        };
        MySingleton.getMySingleton(this).addVolley(stringRequest);
    }


}
