package com.example.ranichol.firstapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by ranichol on 3/22/2016.
 */
public class FloatingButtonListener implements View.OnClickListener {

    private Activity fromActivity;

    public FloatingButtonListener(Activity from) {
        this.fromActivity = from;
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, R.string.info_label, Snackbar.LENGTH_LONG)
                .setAction(R.string.info_link_text, new InfoClickListener()).show();
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
            if (webIntent.resolveActivity(fromActivity.getPackageManager()) != null) {
                startActivity(fromActivity, webIntent, null);
            }
        }
    }
}
