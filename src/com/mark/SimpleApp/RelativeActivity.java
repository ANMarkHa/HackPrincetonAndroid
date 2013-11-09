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
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class RelativeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // in this activity we define two text views and a button in a RelativeLayout in Java.
        // RelativeLayout is unique in that we can position views in relation to each other

        RelativeLayout mainLayout = new RelativeLayout(this);

        TextView first = new TextView(this);
        first.setId(1);
        first.setText("first");

        TextView second = new TextView(this);
        second.setId(2);
        second.setText("second");

        Button third = new Button(this);
        third.setId(3);
        third.setText("third");

        // put the second view below the first view
        RelativeLayout.LayoutParams secondParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        secondParams.addRule(RelativeLayout.BELOW, first.getId());
        second.setLayoutParams(secondParams);

        // put the third view below the third view
        RelativeLayout.LayoutParams thirdParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        thirdParams.addRule(RelativeLayout.BELOW, second.getId());
        third.setLayoutParams(thirdParams);

        mainLayout.addView(first);
        mainLayout.addView(second);
        mainLayout.addView(third);

        // if we click the "third" button, "finish" the activity, or kill it
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // we can also add a listview
        ListView list = new ListView(this);
        RelativeLayout.LayoutParams listViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        listViewLayoutParams.addRule(RelativeLayout.BELOW, third.getId());
        list.setLayoutParams(listViewLayoutParams);
        list.setAdapter(listAdapter);

        mainLayout.addView(list);

        // set our activity's content view to the layout we just defined
        setContentView(mainLayout);
    }

    // a list adapter handles the creation of each of the individual items in the list
    ListAdapter listAdapter = new ListAdapter() {
        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
        }

        @Override
        public int getCount() {
            return 0;
        }

        // the "item" methods here are useful in that we can associate an item in a list
        // and have it correspond to an item in an array, or some other data structure
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        // really, this is the only essential method to implement
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = getLayoutInflater().inflate(R.layout.main, null);

            Object item = getItem(position);
            // implement an Object class that has stuff like
            // item.getName();
            // item.getType();
            // etc.

            // and use that data to populate the view
            // nameView.setText(name);
            // typeView.setText(type);

            return view;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    };
}
