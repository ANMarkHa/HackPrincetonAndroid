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

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

// Async tasks are good for doing things asynchronously, or in the background
public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    TextView viewToUpdateWithResult;

    public SimpleAsyncTask(TextView viewToUpdateWithResult) {
        this.viewToUpdateWithResult = viewToUpdateWithResult;
    }

    @Override
    protected String doInBackground(Void... params) {

        // perform something in the background, i.e. an HttpGet call
        return getHttp();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        /* do something like

            ProgressBar.setProgress(values);

        */
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // this function executes on the UI thread

        // do something with the result, i.e. update the UI

        Log.d("APP-TAG", s);
        viewToUpdateWithResult.setText(s);
    }

    // Internet calls need to be made in a background thread
    private String getHttp() {
        // some basic code to do an HttpGet call
        String body = null;
        Header[] headers;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpGet get = new HttpGet(new URI("http://www.appnexus.com"));

            HttpResponse response = httpClient.execute(get);

            headers = response.getAllHeaders();
            body = EntityUtils.toString(response.getEntity());
        }

        catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return body;
    }
}

