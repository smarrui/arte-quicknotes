package com.arte.quicknotes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Sandra on 27/04/2016.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

// http://developer.android.com/intl/es/training/material/lists-cards.html

    private List<Note> nNoteList;

    public NotesAdapter(List<Note> notes) {
        nNoteList = notes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = nNoteList.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteExcept.setText(note.getExcept());
    }

    @Override
    public int getItemCount() {
        return nNoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView noteTitle;
        public TextView noteExcept;

        public ViewHolder(View view) {
            super(view);

            noteTitle = (TextView) itemView.findViewById(R.id.note_title);
            noteExcept = (TextView) itemView.findViewById(R.id.note_except);
        }
    }
}
