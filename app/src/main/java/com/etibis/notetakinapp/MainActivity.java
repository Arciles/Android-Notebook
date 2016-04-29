package com.etibis.notetakinapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // Constants for putExtra method key
    public static final String NOTE_ID_EXTRA = "com.esatibis.notetakingapp.NOTE_ID";
    public static String NOTE_TITLE_EXTRA = "com.esatibis.notetakingapp.NOTE_TITLE";
    public static String NOTE_MESSAGE_EXTRA = "com.esatibis.notetakingapp.NOTE_MESSAGE";
    public static String NOTE_CATEGORY_EXTRA = "com.esatibis.notetakingapp.NOTE_CATEGORY";
    public static final String NOTE_FRAGMENT_TO_LOAD_EXTRA =  "com.esatibis.notetakingapp.NOTE_FRAGMENT_TO_LOAD";
    public enum FragmentToLunch {VIEW, EDIT}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
