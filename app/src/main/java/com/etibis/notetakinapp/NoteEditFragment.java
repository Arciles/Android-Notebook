package com.etibis.notetakinapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private EditText title, message;
    private AlertDialog confirmDialogObject;
    private Bundle bundle;
    private long noteID;

    public NoteEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);
        // Get Views from layout
        title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        Button button = (Button) fragmentLayout.findViewById(R.id.saveNote);

        // Populate Widgets with note data
        Intent intent = getActivity().getIntent();
        noteID = intent.getExtras().getLong(MainActivity.NOTE_ID_EXTRA, 0);
        bundle = this.getArguments();

        Boolean newFlag = bundle.getBoolean(NoteDetailActivity.NEW_NOTE_EXTRA);

        if (!newFlag) {
            title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
            message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));
        }


        buildConfirmDialog();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogObject.show();
            }
        });
        // Inflate the layout for this fragment
        return fragmentLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // TODO: add values to save
    }

    private void buildConfirmDialog() {

        final AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are You Sure?");
        confirmBuilder.setMessage("Are you sure you want to save the Note?");

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NoteDbAdapter dbAdapter = new NoteDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();
                if (bundle.getBoolean(NoteDetailActivity.NEW_NOTE_EXTRA)){
                    dbAdapter.createNote(title.getText() + "", message.getText()+ "", Note.Category.PERSONAL);
                } else {
                    dbAdapter.updateNote(noteID, title.getText() + "", message.getText() + "", Note.Category.PERSONAL);
                }
                // Go back to Main Activity
                dbAdapter.close();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing here
            }
        });

        confirmDialogObject = confirmBuilder.create();

    }

}
