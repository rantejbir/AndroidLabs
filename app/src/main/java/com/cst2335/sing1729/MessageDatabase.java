package com.cst2335.sing1729;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {ChatMessage.class},version = 1)
public abstract class MessageDatabase extends RoomDatabase {
    static String DB_NAME="chat";
    public abstract ChatMessageDAO chatMessageDAO();
    private static MessageDatabase mInstance;
    public static synchronized MessageDatabase getInstance(Context ctx) {
        if(mInstance == null) {
            mInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                             MessageDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }

}
