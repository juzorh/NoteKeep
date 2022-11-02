package com.example.notekeep

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import com.example.notekeep.custom.views.ColorSelector
import com.example.notekeep.app.POSITION_NOT_SET
import com.example.notekeep.databinding.FragmentFirstBinding
import com.example.notekeep.models.Course
import com.example.notekeep.models.Note
import com.example.notekeep.repository.DataManager
import android.os.PowerManager
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * @notePosition - set defaults to -1 if this fragment was launched
     * with the addNoteBtn:Button in NoteListFragment.
     * Can be changed if the fragment was launched from the listNotes:ListView
     * in NoteListFragment.
     */
    private var notePosition: Int? = null

    /**
     * @notColor - keeps track of the last color received from
     * [ColorSelector.ColorSelectListener] interface implementation below
     */
    private var noteColor: Int = Color.TRANSPARENT /** No color selected */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Initializes the Course and Note collections
         */
        val adapterCourses = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())

        adapterCourses.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerCourses.adapter = adapterCourses

        notePosition = getNotePosition()

        /**
         * Display note if [notePosition] is set; else, create an
         * empty [Note] with null properties.
         * Then add the empty note to the [DataManager.notes] repository.
         * */
        if (notePosition != POSITION_NOT_SET)
            displayNote()
        else{
            val emptyNote = Note()

            DataManager.notes.add(emptyNote)
            notePosition = DataManager.notes.lastIndex
        }

        binding.colorSelector.setListener { color ->
            noteColor = color
        }
    }

    /**
     * Returns the position of the note selected in [NoteListFragment]'s listViewAdapter() method
     * else it returns -1 if the note position isn't set.
     */
    private fun getNotePosition(): Int {
        var notePosition: Int = POSITION_NOT_SET // Default value if no note was selected

        arguments?.let {
            val args = FirstFragmentArgs.fromBundle(it)

            notePosition = args.notePosition
        }

        return notePosition
    }

    /**
     * Display the list of Notes
     */
    private fun displayNote() {
        /** Get the selected note */
        val note = DataManager.notes[notePosition!!]

        /** Fill the EditText fields of the fragment with the
         * selected note's title and description
         **/
        binding.noteTitle.setText(note.title)
        binding.noteDescription.setText(note.description)
        binding.colorSelector.selectedColorValue = note.color

        noteColor = note.color

        /**
         * fill the spinner field with the "Course" for the "Note" selected
         */
        val coursePosition =
            DataManager.courses.values.indexOf(note.course)
        binding.spinnerCourses.setSelection(coursePosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * This function is called when the activity isn't visible
     * to the user.
     * For instance, when the back button is pressed
     */
    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition!!]

        note.title = binding.noteTitle.text.toString()
        note.description = binding.noteDescription.text.toString()
        note.course = binding.spinnerCourses.selectedItem as Course
        note.color = this.noteColor
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt(NOTE_POSITION, notePosition!!)
//    }
}