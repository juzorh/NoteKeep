package com.example.notekeep.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeep.NoteListFragmentDirections
import com.example.notekeep.R
import com.example.notekeep.models.Note

class NoteListAdapter(
    private val context: Context,
    private val notes: ArrayList<Note>
): RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_note_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val textCourse = itemView.findViewById<TextView>(R.id.textCourse)
        private val textTitle = itemView.findViewById<TextView>(R.id.textTitle)
        var color: View = itemView.findViewById(R.id.noteColor)

        init {
            /**
             * @action - sends the position (an integer) of the clicked listview
             * item from the NoteListFragment to the FirstFragment using the
             * Navigation library.
             */
            itemView.setOnClickListener {
                val action: NoteListFragmentDirections
                .NoteListFragmentToFirstFragment = NoteListFragmentDirections.noteListFragmentToFirstFragment()

                /** notePosition is the name of the navigation argument */
                action.notePosition = adapterPosition

                /**
                 * Launch the FirstFragment page with the [Note]'s Int
                 * position stored in a bundle
                 */
                itemView.findNavController().navigate(action)
            }
        }

        fun bind(note: Note){
            textCourse.text = note.course?.title
            textTitle.text = note.title
            color.setBackgroundColor(note.color)
        }
    }
}