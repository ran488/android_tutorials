package com.example.ranichol.firstapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Reading and writing of messages to the persistent store (e.g. file or database).
 * Created by ranichol on 3/24/2016.
 */
public class MessageStore {

    private static final String FILE_NAME = "myapp-journal";

    private Context ctx;

    public MessageStore(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Read previously saved messages from the persistent store (e.g. file or DB).
     *
     * @return all previously stored messages as a String
     */
    public String readMessages() {
        StringBuffer fileContents = new StringBuffer();
        FileInputStream fis = null;
        try {
            fis = ctx.openFileInput(FILE_NAME);
            int content;
            while ((content = fis.read()) != -1) {
                fileContents.append((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return fileContents.toString();
    }

    /**
     * Store the new message into the persistent store.
     *
     * @param newMessage new message to store.
     */
    public void storeMessage(String newMessage) {
        StringBuffer fileContents = new StringBuffer(this.readMessages());
        fileContents.append("\n");
        fileContents.append(newMessage);
        FileOutputStream outputStream = null;
        try {
            outputStream = ctx.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(fileContents.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Clean out persistent store - remove all messages (delete DB rows, delete file, etc.)
     */
    public void clearMessasges() {

    }
}
