package com.arte.quicknotes.models;

import com.arte.quicknotes.database.NotesStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandra on 27/04/2016.
 */
public class NoteListMock /*implements NotesStorage*/ {

    private static int NUM_NOTES = 10;
    private static String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent porttitor ex lorem, quis volutpat elit placerat at. Ut in euismod velit. Aenean faucibus luctus odio in dignissim. Proin aliquet felis dignissim convallis laoreet.";
    private static List<Note> noteList;
    private static int nextId = 0;

    public static Note get(int id) {
        return null;
    }

    public static List<Note> getAll() {
        if (noteList == null) {
            createList();
        }
        return noteList;
    }

    public static void add(Note note) {
        note.setId(generateId());
        noteList.add(0, note);
    }

    public static void update(Note updateNote) {
        Note note = noteList.get(getPosition(updateNote));
        note.setTitle(updateNote.getTitle());
        note.setContent(updateNote.getContent());
    }

    public static void delete(Note note) {
        noteList.remove(getPosition(note));
    }

    private static void createList() {
        noteList = new ArrayList<>();
        for (int i = 0; i < NUM_NOTES; i++) {
            Note note = new Note();
            note.setId(generateId());
            note.setTitle("Note #" + (i + 1));
            note.setContent("#" + (i + 1) + " " + LOREM_IPSUM);
            noteList.add(note);
        }
    }

    private static int generateId() {
        return nextId++;
    }

    private static int getPosition(Note noteToFind) {
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (note.getId() == noteToFind.getId()) {
                return i;
            }
        }
        return -1;
    }
}