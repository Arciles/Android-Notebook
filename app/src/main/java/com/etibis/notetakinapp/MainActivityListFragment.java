package com.etibis.notetakinapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class MainActivityListFragment extends ListFragment {

    // ArrayList for Notes
    private ArrayList<Note> notes;
    // Adapter for listView
    private NoteAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notes = new ArrayList<Note>();

        notes.add(new Note("First Note", "", Note.Category.PERSONAL));
        notes.add(new Note("Second Note", "Random Message 2", Note.Category.TECHNICAL));
        notes.add(new Note("Third Note", "Random Message 3", Note.Category.FINANCE));

        adapter = new NoteAdapter(getContext(),notes);

        setListAdapter(adapter);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        lunchNoteDetailActivity(position, MainActivity.FragmentToLunch.VIEW);
    }

    // Creating menu for edir
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    // Handles menu clicks
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;
        // Returns selected items ID
        switch (item.getItemId()) {

            case R.id.edit:
                // TODO: lunch edit fragment with selected row
                lunchNoteDetailActivity(rowPosition, MainActivity.FragmentToLunch.EDIT);
                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void lunchNoteDetailActivity (int position, MainActivity.FragmentToLunch fragmentToLunch) {
        // Selection note in that position
        Note note = (Note) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getNoteId());
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());

        switch (fragmentToLunch) {
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLunch.EDIT);
                break;
        }

        startActivity(intent);
    }
}
