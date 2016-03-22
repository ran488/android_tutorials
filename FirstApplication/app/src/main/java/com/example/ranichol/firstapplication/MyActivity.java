package com.example.ranichol.firstapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.ranichol.firstapplication.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab;
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Get the source", Snackbar.LENGTH_LONG)
                            .setAction("Browse", new InfoClickListener()).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /** Pressing the Send message button will fire this method.
     *
     * @param view the view that was clicked
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Handle clicks on the "info" icon. Send user to the source code on GitHub.
     *
     */
    public class InfoClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Uri webpage;
            webpage = Uri.parse(String.valueOf("https://github.com/ran488/android_tutorials"));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

            Intent chooser = Intent.createChooser(webIntent, "Choose Browser");
            // Verify the intent will resolve to at least one activity
            if (webIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }
        }
    }
}
