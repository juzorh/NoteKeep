package com.example.notekeep.repository

import com.example.notekeep.models.Course
import com.example.notekeep.models.Note

/**
 * This class holds a collection of [Course]'s and [Note]'s
 *
 * I want DataManager to be in a singleton pattern so that all
 * the activities and fragments can access the same instance of data
 * instead of creating a different instance any time I want to
 * use the DataManager class.
 *
 * This is achieved by changing it 'class' keyword to 'object'
 */
object DataManager {
    val courses = HashMap<String, Course>()
    val notes = ArrayList<Note>()

    /**
     * Automatically runs whenever an instance of the "DataManager" class
     * is created
     */
    init {
        initializeCourses()
        initializeNotes()
    }

    /**
     * populate the hashmap with courses
     */
    private fun initializeCourses(){
        var course = Course("android_intents", "Android Programming with Intents")
        courses[course.courseId] = course

        course = Course("android_async", "Android Async Programming and Services")
        courses[course.courseId] = course

        course = Course("java_lang", "Java Fundamentals: The Java Language")
        courses[course.courseId] = course

        course = Course("java_core", "Java Fundamentals: The Core Platform")
        courses[course.courseId] = course
    }

    /**
     * populate the arraylist
     */
    private fun initializeNotes(){
        // android_intents
        var course = courses["android_intents"]!!

        var note = Note(course, "Dynamic intent resolution",
            "Wow, intents are powerful; they delegate much more than just" +
                    "a component invocation")
        notes.add(note)

        note = Note(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        // android_async
        course = courses["android_async"]!!

        note = Note(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)

        note = Note(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)

        // java_lang
        course = courses["java_lang"]!!

        note = Note(course, "Parameters",
            "Leverage variable-length parameter lists")
        notes.add(note)

        note = Note(course, "Anonymous classes",
            "Anonymous classes simplify implementing one-use types")
        notes.add(note)

        // java_core
        course = courses["java_core"]!!

        note = Note(course, "Compiler options",
            "The -jar option isn't compatible with with the -cp option")
        notes.add(note)

        note = Note(course, "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }
}