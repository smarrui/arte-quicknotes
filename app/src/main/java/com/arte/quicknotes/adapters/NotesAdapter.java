package com.arte.quicknotes.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public interface Events {
        void onNoteClicked(Note note);
    }

    private List<Note> nNoteList;

    public NotesAdapter(List<Note> notes, Events events) {
        nNoteList = notes;
        mEvents = events;
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

    private List<Note> mNoteList;
    private Events mEvents;

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
        final Note note = nNoteList.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteExcept.setText(note.getExcept());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEvents.onNoteClicked(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nNoteList.size();
    }
}
