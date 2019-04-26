package edu.illinois.cs.cs125.spring2019.lab12;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /**
     * Default logging tag for messages from the main activity.
     */
    private static final String TAG = "Lab12:Main";

    private Button btn1;
    private EditText city;
    private EditText state;
    private TextView textView;
    private double latitude;
    private double longtitude;
    private String key = "AIzaSyAwRH6Junl7BV235LakrC3jzV7fCQeEtDg";

    /** Request queue for our API requests. */
    /*private static RequestQueue requestQueue;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.enter1);
        city = (EditText) findViewById(R.id.city_name);
        state = (EditText) findViewById(R.id.state_name);
        textView = (TextView) findViewById(R.id.outputDisplay);

        // Set up the queue for our API requests
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    final String findplace_url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=" + city.getText().toString() + "%20" + state.getText().toString() + "&inputtype=textquery&fields=geometry&key=" + key;
                    Log.d("here", findplace_url);
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.GET,
                            findplace_url,
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(final JSONObject response) {
                                    try {
                                        JSONObject abc = response.getJSONArray("candidates").getJSONObject(0);
                                        JSONObject geometry = (JSONObject) abc.get("geometry");
                                        JSONObject location = (JSONObject) geometry.get("location");
                                        latitude = location.getDouble("lat");
                                        longtitude = location.getDouble("lng");
                                        textView.setText(Double.toString(longtitude) + "," + Double.toString(latitude));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());

                        }
                    });
                    requestQueue.add(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
