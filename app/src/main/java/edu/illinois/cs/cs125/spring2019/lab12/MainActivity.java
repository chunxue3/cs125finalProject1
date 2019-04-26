package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppConstants.initialization(this.getApplicationContext());
    }

    public void startGame(final View view) {
        //Log.i("ImageButton","clicked");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
/**
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
/*public final class MainActivity extends AppCompatActivity {
    /**
     * Default logging tag for messages from the main activity.
     */
    /*private static final String TAG = "Lab12:Main";

    private Button btn1;
    private EditText editText;
    private TextView textView;

    /** Request queue for our API requests. */
    /*private static RequestQueue requestQueue;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    /*protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.enter1);
        editText = (EditText) findViewById(R.id.enterNumber);
        textView = (TextView) findViewById(R.id.outputDisplay);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.GET,
                            "http://numbersapi.com/" + editText.getText().toString() + "/trivia?notfound=floor&fragment",
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(final JSONObject response) {
                                    try {
                                        String c = b.getString("text");
                                        textView.setText(c);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        textView.setText("Got error, ");
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, "error.");
                            textView.setText("Got error, ");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    textView.setText("Got error, ");
                }
            }
        });
    }
}
*/

/**
 * private void sendRequestAndPrintReponse1(final TextView textView, final EditText editText) {
 *          String value;
 *          value = editText.getText().toString();
 *          //int findValue = Integer.parseInt(value);
 *          try {
 *              JsonObjectRequest request = new JsonObjectRequest(
 *                      Request.Method.GET,
 *                      "http://numbersapi.com/" + value + "/trivia?notfound=floor&fragment",
 *                      null,
 *                      new Response.Listener<JSONObject>() {
 *                          @Override
 *                          public void onResponse(final JSONObject response) {
 *                              Log.d(TAG, "Received response.");
 *                              try {
 *                                  Object a = response.getJSONObject("meta-data").get("text");
 *                                  String string = (String) a;
 *                                  textView.setText(string);
 *                              } catch (Exception e) {
 *                                  Log.e(TAG, response + "is invalid");
 *                              }
 *                          }
 *                      }, new Response.ErrorListener() {
 *                  @Override
 *                  public void onErrorResponse(final VolleyError error) {
 *                      Log.e(TAG, "error.");
 *                      textView.setText("Got error, ");
 *                  }
 *              });
 *              requestQueue.add(request);
 *          } catch (Exception e) {
 *              e.printStackTrace();
 *          }
 *      }
 */
