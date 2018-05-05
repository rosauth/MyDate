package com.android.mydate.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.mydate.R;
import com.android.mydate.model.Chat;

import java.util.ArrayList;

/**
 * Created by rosa on 4/29/2018.
 */

public class ChatActivity extends AppCompatActivity{
    // TODO: Need logic for displaying chats from 2 side

    private ArrayList<Chat> convList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ListView list = (ListView) findViewById(R.id.list_conversation);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.chatNameToolbar);
        //setSupportActionBar(toolbar);
    }

    private class ChatAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return convList.size();
        }

        @Override
        public Chat getItem(int i) {
            return convList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Chat chat = getItem(i);
            if (chat.isSent()){
                view = getLayoutInflater().inflate(R.layout.chat_sent, null);
            }
            else {
                view = getLayoutInflater().inflate(R.layout.chat_received, null);
            }

//            TextView
            return null;
        }
    }
}
