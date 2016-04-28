package com.arte.quicknotes.database;

import com.arte.quicknotes.models.Note;

import java.util.List;

/**
 * Created by Sandra on 28/04/2016.
 */
public interface NotesStorage {

    Note get(int id);

    List<Note> getAll();

    void add(Note note);

    void delete(Note note);

    void update(Note note);
}
