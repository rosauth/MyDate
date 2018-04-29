package com.android.mydate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by rosa on 4/29/2018.
 */

public class ChatActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.chatNameToolbar);
        //setSupportActionBar(toolbar);
    }
}
