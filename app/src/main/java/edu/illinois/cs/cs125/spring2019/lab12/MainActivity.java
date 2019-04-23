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
import org.json.JSONObject;

/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /**
     * Default logging tag for messages from the main activity.
     */
    private static final String TAG = "Lab12:Main";


    /**
     * Request queue for our API requests.
     */
    private static RequestQueue requestQueue;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);

        Button btn1 = (Button) findViewById(R.id.enter1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView textView = (TextView) findViewById(R.id.outputDisplay);
                EditText editText = (EditText) findViewById(R.id.enterNumber);
                sendRequestAndPrintReponse1(textView, editText);
            }
        });
    }

     private void sendRequestAndPrintReponse1(final TextView textView, final EditText editText) {
         String value;
         if (editText == null || editText.length() == 0) {
             value = "random";
         } else {
             value = editText.getText().toString();
         }
         //int findValue = Integer.parseInt(value);
         try {
             JsonObjectRequest request = new JsonObjectRequest(
                     Request.Method.GET,
                     "http://numbersapi.com/" + value + "/trivia",
                     null,
                     new Response.Listener<JSONObject>() {
                         @Override
                         public void onResponse(final JSONObject response) {
                             Log.d(TAG, "Received response.");
                             try {
                                 String string = response.getString("text");
                                 textView.setText(string);
                             } catch (Exception e) {
                                 Log.e(TAG, response + "is invalid");
                             }
                         }
                     }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(final VolleyError error) {
                     Log.e(TAG, "error.");
                     textView.setText("Got error, ");
                 }
             });
             requestQueue.add(request);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}