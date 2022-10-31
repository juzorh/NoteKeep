package com.example.notekeep.ui.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notekeep.R
import com.example.notekeep.adapter.CourseListAdapter
import com.example.notekeep.databinding.FragmentCoursesBinding
import com.example.notekeep.repository.DataManager

class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listCourse.apply{
            layoutManager = GridLayoutManager(requireActivity(), resources.getInteger(R.integer.course_grid_span))
            adapter = CourseListAdapter(requireActivity(), DataManager.courses.values.toList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}