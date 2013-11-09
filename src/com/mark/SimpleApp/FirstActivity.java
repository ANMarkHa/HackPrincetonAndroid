package com.mark.SimpleApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // attach the activity to the the main.xml layout file in our res/layout folder
        setContentView(R.layout.main);

        // we can find our xml views by looking them up by id
        TextView text = (TextView) findViewById(R.id.mytext);

        // but we can also define them in Java, programatically
        Button clickMe = new Button(this);
        clickMe.setText("Click me!");
        // define layout properties in layout params
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        clickMe.setLayoutParams(lp);

        clickMe.setGravity(Gravity.CENTER);

        // define an "OnClickListener" - what we do when the button is clicked
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "You did it!", Toast.LENGTH_SHORT);

                // make an intent
                Intent intent = new Intent(getApplicationContext(), RelativeActivity.class);
                intent.setData(null);
                intent.putExtra("extra-key", true);

                // start the RelativeActivity based on the intent we defined
                startActivity(intent);

                // In RelativeActivity's OnCreate() method,
                // we would do something like this to get the data
                Intent receivedIntent = getIntent();
                receivedIntent.getData();
                boolean value = receivedIntent.getBooleanExtra("extra-key", false);


            }
        });

        // find the main layout from our main.xml file based on the id - see main.xml
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main);
        // remember to add the view to the parent layout
        mainLayout.addView(clickMe);
    }
}
