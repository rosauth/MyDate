package com.android.mydate.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.mydate.R;


/**
 * Created by delaroy on 3/27/17.
 */
public class UsersActivity extends AppCompatActivity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        textViewName = (TextView) findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra("user_name");
        textViewName.setText("Welcome " + nameFromIntent);
    }
}
