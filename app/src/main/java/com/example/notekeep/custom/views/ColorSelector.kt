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
 * generate four different constructors in the Java bytecode.
 */

/**
 * @attributeSet: Contains any custom attributes defined for a given
 * instance of the view. The attribute values can then be pulled out
 * of the object and applied to the view.
 *
 * Steps for defining custom attributes:
 * 1. Define the view's custom attr. in a <declare-styleable> resource
 *      element in res/values/attr.xml.
 * 2. Specify values for the attributes in the custom view's XML layout.
 * 3. Retrieve attr. values at runtime in the custom view
 *      class. defined in the constant [typedArray] below.
 * 4. Apply the retrieved attr values to the layout where the
 *      custom view is implemented.
 *      in this case, the fragment_first.xml using
 *      "app:colors="@array/note_color_array".
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

    /**
     * TODO: Use layout binding to reduce the findViewById() call
     */
    //private val binding: ColorSelectorBinding

    /**
     * This index will keep track of which color is selected as
     * the user is interacting with this view
     */
    private var selectedColorIndex = 0

    init {
        orientation = HORIZONTAL

        /**
         * Add custom color attributes
         */
        val typedArray = context.obtainStyledAttributes(
            attributeSet, R.styleable.ColorSelector
        )
        listOfColors = typedArray.getTextArray(R.styleable.ColorSelector_colors)
            .map {
                /**
                 * Converts the array of HEX String colors defined in
                 * res/colors.xml to Integers
                 */
                Color.parseColor(it.toString())
            }

        /**
         * TypedArray are a shared resource and must be recycled after
         * each use to free up resources and memory.
         */
        typedArray.recycle()

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
     * Used to set the note's color to the color selector
     * compound view
     */
    var selectedColorValue: Int = android.R.color.transparent
        set(value){
            var index = listOfColors.indexOf(value)
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

    /**
     * lambda listeners to be implemented by an activity or
     * fragment to communicate events between the custom view
     * and the activities or fragments so that the user can
     * save the selected color.
     */
    private var colorSelectListener: ((Int) -> Unit)? = null /** Can be made to accept an array of listener functions */

    /**
     * accepts a function that receives the selected color (INT) as
     * a parameter
     */
    fun setListener(color: (Int) -> Unit) {
        this.colorSelectListener = color
    }
    /**
     * The activities/fragments can call this method to be notified
     * of color changes
     */


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

        this.colorSelectListener?.let { function ->
            function(color)
        }
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
}