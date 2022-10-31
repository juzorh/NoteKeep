package com.example.notekeep.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeep.R
import com.example.notekeep.models.Course

class CourseListAdapter(
    private val context: Context,
    private val course: List<Course>
): RecyclerView.Adapter<CourseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_course_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = course[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int {
        return course.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val courseTitle = itemView.findViewById<TextView>(R.id.courseTitle)

        fun bind(course: Course) {
            courseTitle.text = course.title
        }

    }
}