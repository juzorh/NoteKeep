package com.example.notekeep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeep.adapter.NoteListAdapter
import com.example.notekeep.databinding.FragmentNoteListBinding
import com.example.notekeep.repository.DataManager

class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNoteBtn.setOnClickListener {
            findNavController().navigate(R.id.noteListFragmentToFirstFragment)
        }

        binding.listNotes.apply{
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = NoteListAdapter(requireActivity(), DataManager.notes)
        }
    }

    override fun onResume() {
        super.onResume()

        /**
         * To notify the recyclerview adapter if the user modified
         * the data being displayed.
         * NB: DO NOT USE notifyDataSetChanged for large amount of data
         */
        binding.listNotes.adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}