package com.example.notekeep.models

import android.graphics.Color

/**
 * Represents the data about a course.
 * When the instances of the [Course] class created in the
 * [DataManager] class are displayed within the spinner in
 * [FirstFragment], we need to indicate what value from the
 * instance we want to display (the "title" value).
 * So we need a string representation of the [Course] class; which
 * is done by overriding the class's toString method
 *
 */
class Course(val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

/**
 * This class represents data about the notes in a [Course]
 */
data class Note (
    var course: Course? = null,
    var title: String? = null,
    var description: String? = null,
    var color: Int = Color.TRANSPARENT
    )