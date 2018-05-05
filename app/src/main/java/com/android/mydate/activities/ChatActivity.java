package com.android.mydate.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
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
    private ChatAdapter chatAdapter;
    private EditText input_chat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        convList = new ArrayList<Chat>();
        ListView list = (ListView) findViewById(R.id.list_conversation);
        chatAdapter = new ChatAdapter();
        list.setAdapter(chatAdapter);
        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setStackFromBottom(true);

        input_chat = (EditText) findViewById(R.id.input_chat);

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

        @SuppressLint("InflateParams")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Chat mChat = getItem(i);
            if (mChat.isSent()){
                view = getLayoutInflater().inflate(R.layout.chat_sent, null);
            }
            else {
                view = getLayoutInflater().inflate(R.layout.chat_received, null);
            }

            //TODO Edit to provide conversation between users
            TextView chat_time = (TextView) view.findViewById(R.id.chat_time);
            chat_time.setText(DateUtils.getRelativeDateTimeString(ChatActivity.this,
                    mChat.getDate().getTime(), DateUtils.SECOND_IN_MILLIS, DateUtils.DAY_IN_MILLIS, 0));

            TextView chat_content = (TextView) view.findViewById(R.id.chat_content);
            chat_content.setText(mChat.getChat_content());

            TextView chat_status = (TextView) view.findViewById(R.id.chat_status);
            if (mChat.isSent()) {
                if (mChat.getStatus() == Chat.STATUS_SENT)
                    chat_status.setText("Delivered");
                else {
                    if (mChat.getStatus() == Chat.STATUS_SENDING)
                        chat_status.setText("Sending...");
                    else {
                        chat_status.setText("Failed");
                    }
                }
            }
            else
                chat_status.setText("");

            return view;
        }
    }
}
