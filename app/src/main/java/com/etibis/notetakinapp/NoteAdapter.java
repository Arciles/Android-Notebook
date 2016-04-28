package com.etibis.notetakinapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by esattahaibis on 2016-04-27.
 */

public class NoteAdapter extends ArrayAdapter<Note> {

    NoteAdapter(Context context, ArrayList<Note> notes){
        super(context, 0, notes);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Note note = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
        TextView messege = (TextView) convertView.findViewById(R.id.listItemNoteText);

        title.setText(note.getTitle());
        messege.setText(note.getMessage());

        return convertView;

    }
}
