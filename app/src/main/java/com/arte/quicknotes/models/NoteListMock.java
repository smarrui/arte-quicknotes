package com.arte.quicknotes.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandra on 27/04/2016.
 */
public class NoteListMock {

   private static List<Note> noteList;

    public static List<Note> getList() {
        if (noteList == null) {
            createList();
        }
        return noteList;
    }

    public static void addNote(Note note) {
        noteList.add(note);
    }
    private static void createList() {
        noteList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Note note = new Note();
            note.setTitle("Note #" + (i + 1));
            note.setContent("The content of note #" + (i + 1) + " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent porttitor ex lorem, quis volutpat elit placerat at. Ut in euismod velit. Aenean faucibus luctus odio in dignissim. Proin aliquet felis dignissim convallis laoreet. Donec pretium malesuada felis, at tincidunt risus consectetur eu. Aenean dapibus ligula ipsum, quis aliquam nisl facilisis sit amet. Proin velit est, fermentum sit amet felis vitae, lobortis fermentum nisl. Aliquam sagittis, odio ut blandit malesuada, est dui pellentesque diam, eu auctor turpis turpis pharetra elit. Morbi et nulla ac ex convallis consectetur.");
            noteList.add(note);
        }
    }
}