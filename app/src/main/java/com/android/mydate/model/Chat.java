package com.android.mydate.model;

import java.util.Date;

/**
 * Created by rosa on 5/5/2018.
 */

public class Chat {

    private String id_chat;
    private String id_sender;
    private String id_receiver;
    private String chat_content;
    private Date date;
    public static final int STATUS_SENDING = 0;
    public static final int STATUS_SENT = 1;
    public static final int STATUS_FAILED = 2;
    private int status = STATUS_SENT;

    public Chat(String id_chat, String id_sender, String id_receiver, String chat_content) {
        this.id_chat = id_chat;
        this.id_sender = id_sender;
        this.id_receiver = id_receiver;
        this.chat_content = chat_content;
        this.date = date;
    }

    public String getId_chat() {
        return id_chat;
    }

    public void setId_chat(String id_chat) {
        this.id_chat = id_chat;
    }

    public String getId_sender() {
        return id_sender;
    }

    public void setId_sender(String id_sender) {
        this.id_sender = id_sender;
    }

    public String getId_receiver() {
        return id_receiver;
    }

    public void setId_receiver(String id_receiver) {
        this.id_receiver = id_receiver;
    }

    public String getChat_content() {
        return chat_content;
    }

    public void setChat_content(String chat_content) {
        this.chat_content = chat_content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSent(){
        // TODO return id from sender
        return true;
    }
}


