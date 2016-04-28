package com.etibis.notetakinapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class MainActivityListFragment extends ListFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Note> notes = new ArrayList<Note>();

        notes.add(new Note("First Note", "Random Message 1", Note.Category.PERSONAL));
        notes.add(new Note("Second Note", "Random Message 2", Note.Category.TECHNICAL));
        notes.add(new Note("Third Note", "Random Message 3", Note.Category.FINANCE));

        NoteAdapter adapter = new NoteAdapter(getContext(),notes);

        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }
}
