package com.android.mydate.model;

/**
 * Created by rosa on 5/5/2018.
 */

public class Chat {

    private String id_chat;
    private String id_sender;
    private String id_receiver;
    private String chat_content;

    public Chat(String id_chat, String id_sender, String id_receiver, String chat_content) {
        this.id_chat = id_chat;
        this.id_sender = id_sender;
        this.id_receiver = id_receiver;
        this.chat_content = chat_content;
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

    public boolean isSent(){
        // TODO Edit this logic
        return true;
    }
}


