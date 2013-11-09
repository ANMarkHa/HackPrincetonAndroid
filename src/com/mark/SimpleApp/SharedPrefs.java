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

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String NAME_KEY = "name";

    public static void prefsExample (Context context) {
        // get sharedprefs
        SharedPreferences preferences = context.getSharedPreferences("MY-APP-TAG", Context.MODE_PRIVATE);

        // get editor to edit the prefs
        SharedPreferences.Editor editor = preferences.edit();

        // insert values
        editor.putLong("Key", 1000000);
        editor.putString("key2", "asdf");
        editor.putString(NAME_KEY, "Mark");

        // save the changes
        editor.apply();
    }

    public static void getPrefs(Context context) {
        // get sharedprefs
        SharedPreferences preferences = context.getSharedPreferences("MY-APP-TAG", Context.MODE_PRIVATE);

        String defaultValue = "";
        long key1value = preferences.getLong("Key", 0);
        String key2value = preferences.getString("key2", null);
        String nameValue = preferences.getString(NAME_KEY, "default name");

        // do something with these values
    }
}
