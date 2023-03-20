package com.cst2335.sing1729;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ChatMessage {
    public boolean mCleared;
    @ColumnInfo(name = "message")
    private String message;
    @ColumnInfo(name = "TimeSent")
    private String timeSent;
    @ColumnInfo(name = "SendOrReceive")
    private boolean isSentButton;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    public ChatMessage() {
    }

    public ChatMessage(String m, String t, boolean sent) {
        this.message = m;
        this.timeSent = t;
        this.isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public void setSentButton(boolean isSentButton) {
        this.isSentButton = isSentButton;
    }
}
