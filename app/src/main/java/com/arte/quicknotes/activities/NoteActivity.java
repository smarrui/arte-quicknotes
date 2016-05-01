package com.arte.quicknotes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;
import com.arte.quicknotes.models.NoteListMock;

public class NoteActivity extends AppCompatActivity {

    public static final String PARAM_NOTE = "param_note";

    private EditText mTitle;
    private EditText mContent;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        setupActivity();
        loadNote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);

        if (mNote == null) {
            MenuItem deleteItem = menu.findItem(R.id.action_delete);
            deleteItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveNote();
                return true;
            case R.id.action_delete:
                deleteNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = (EditText) findViewById(R.id.et_title);
        mContent = (EditText) findViewById(R.id.et_content);
    }

    private void loadNote() {
        Note note = (Note) getIntent().getSerializableExtra(PARAM_NOTE);
        if (note == null) {
            return;
        }
        mNote = note;
        mTitle.setText(note.getTitle());
        mContent.setText(note.getContent());
    }

    private void saveNote() {
        String title = mTitle.getText().toString();
        String content = mContent.getText().toString();

        Note note = null;
        if (mNote == null) {
            note = new Note();
        } else {
            note = mNote;
        }
        note.setTitle(title);
        note.setContent(content);
        if (mNote == null) {
            NoteListMock.add(note);
        } else {
            NoteListMock.update(note);
        }

        finish();
    }

    private void deleteNote() {
        NoteListMock.delete(mNote);
        finish();
    }
}
