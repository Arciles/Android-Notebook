package com.etibis.notetakinapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        createAndAddFragment();
    }

    private void createAndAddFragment () {
        Intent intent = getIntent();
        MainActivity.FragmentToLunch ftl = (MainActivity.FragmentToLunch) intent.getSerializableExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Switch Between edit and view fragments depending on the parameter that passed
        switch (ftl) {
            case VIEW:
                NoteViewFragment noteViewFragment = new NoteViewFragment();
                setTitle(R.string.view_fragment_title);
                fragmentTransaction.add(R.id.noteContainer, noteViewFragment, "NOTE_VIEW_FRAGMENT");
                break;
            case EDIT:
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                setTitle(R.string.edit_fragment_title);
                fragmentTransaction.add(R.id.noteContainer, noteEditFragment, "NOTE_EDIT_FRAGMENT");
                break;
        }

        fragmentTransaction.commit();
    }
}