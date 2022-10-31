package com.example.notekeep.custom.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.notekeep.R
import com.example.notekeep.databinding.ColorSelectorBinding

/**
 * All kotlin codes are transcribed to Java Bytecode.
 * Java has no concept of default parameters in classes or functions.
 * @JvmOverloads tells the compiler to generate a constructor for
 * each parameter in our primary constructor. In this case, it will
 * generate four different constructors in the Java bytecode
 */
class ColorSelector @JvmOverloads
    constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
    defRes: Int = 0
) : LinearLayout(context, attributeSet, defStyle, defRes) {

    /**
     * Default list of colors
     */
    private var listOfColors = listOf(Color.BLUE, Color.RED, Color.GREEN)
    //private val binding: ColorSelectorBinding
    /**
     * This index will keep track of which color is selected as
     * the user is interacting with this view
     */
    private var selectedColorIndex = 0

    init {
        orientation = LinearLayout.HORIZONTAL

        /**
         * Inflate the color_selector.xml into the parent
         * container (LinearLayout).
         */
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        inflater.inflate(R.layout.color_selector, this)



        /** Show the currently selected color */
        findViewById<View>(R.id.selectedColor)
            .setBackgroundColor(listOfColors[selectedColorIndex])

        /**
         * onClick listeners for the arrows
         */
        findViewById<ImageView>(R.id.colorSelectorArrowLeft)
            .setOnClickListener {
                selectPreviousColor()
            }

        findViewById<ImageView>(R.id.colorSelectorArrowRight)
            .setOnClickListener {
                selectNextColor()
            }
        /** END*/

        /**
         * Listener for when the checkbox is checked or unchecked
         */
        findViewById<CheckBox>(R.id.colorEnabled).setOnCheckedChangeListener { _, _ ->
            /**
             * broadcastColor is called here so that the listeners
             * notify that it needs to show or hide the color
             */
            broadcastColor()
        }
    }

    /**
     * Interface listeners to be implemented by an activity or
     * fragment to communicate events between the custom view
     * and the activities or fragments so that the user can
     * save the selected color.
     */
    private var colorSelectListener: ColorSelectListener? = null
    interface ColorSelectListener{
        fun onColorSelected(color: Int)
    }

    /**
     * The activities/fragments can call this method to be notified
     * of color changes
     */
    fun setColorSelectListener(listener: ColorSelectListener){
        this.colorSelectListener = listener
    }

    /**
     * This method is used to communicate the chosen color to
     * the [colorSelectListener]
     */
    private fun broadcastColor(){
        val checkBox = findViewById<CheckBox>(R.id.colorEnabled)

        val color = if (checkBox.isChecked){
            listOfColors[selectedColorIndex]
        } else {
            Color.TRANSPARENT // No color selected
        }

        this.colorSelectListener?.onColorSelected(color)
    }

    /**
     * Move forward through the list of colors
     */
    private fun selectNextColor() {
        if (selectedColorIndex == listOfColors.lastIndex){
            selectedColorIndex = 0
        } else {
            selectedColorIndex++
        }

        findViewById<View>(R.id.selectedColor)
            .setBackgroundColor(listOfColors[selectedColorIndex])

        broadcastColor()
    }

    /**
     * Move backwards through the list of colors
     */
    private fun selectPreviousColor() {
        if (selectedColorIndex == 0) {
            selectedColorIndex = listOfColors.lastIndex
        }else{
            selectedColorIndex--
        }

        findViewById<View>(R.id.selectedColor)
            .setBackgroundColor(listOfColors[selectedColorIndex])

        broadcastColor()
    }

    /**
     * Used to set the note's color to the color selector
     * compound view
     */
    fun setSelectedColor(color: Int){
        var index = listOfColors.indexOf(color)
        if (index == -1) {
            findViewById<CheckBox>(R.id.colorEnabled).isChecked = false
            index = 0
        } else {
            findViewById<CheckBox>(R.id.colorEnabled).isChecked = true
        }
        selectedColorIndex = index

        findViewById<View>(R.id.selectedColor)
            .setBackgroundColor(listOfColors[selectedColorIndex])
    }
}