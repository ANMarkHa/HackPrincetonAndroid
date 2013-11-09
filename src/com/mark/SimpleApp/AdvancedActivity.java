/*
 *    Copyright 2013 APPNEXUS INC
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mark.SimpleApp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdvancedActivity extends Activity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        setContentView(mainLayout);

        TextView myTextView = new TextView(this);
        mainLayout.addView(myTextView);

        // do things in the background. pass the textview that will be updated with the result
        // see the implementation of SimpleAsyncTask for more details
        new SimpleAsyncTask(myTextView).execute();


        // we can also use handlers to execute on the UI thread
        handler = new Handler () {
            // messages will be handled here
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String message = (String) msg.obj;
                TextView text = new TextView(AdvancedActivity.this);
                text.setText(message);

            }
        };

        // send messages to the handler we made

        // send with an object
        handler.sendMessage(handler.obtainMessage(1, "123"));

        // send with a delay
        handler.sendEmptyMessageDelayed(1, 10000);

        runInBackground();
    }

    // if we were to do something in the background, we could access
    // the UI thread by sending a message to the handler we made
    private void runInBackground() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                handler.sendMessage(null);
            }
        }.start();
    }
}
