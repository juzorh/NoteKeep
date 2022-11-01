Practice app to store my Pluralsight courses.

## TODOS
- [x] Custom views (Compound components and Extending views) To
	add color patch to notes
- [x] RecyclerView
- [x] Navigation Drawer
- [x] Create custom view from scratch
- [ ] Save notes using ROOM architecture
- [ ] implement ViewModels
- [ ] Add automatic code testing
- [ ] Improve UI
- and more improvements

## Custom views
- ColorSelector.kt is a compound view class of the ImageView and View
	types which extends the LinearLayout class.

**XML**
```
<com.example.notekeep.custom.views.ColorSelector
	android:id="@+id/colorSelector"
        app:colors="@array/note_color_array"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
/>
```
	https://user-images.githubusercontent.com/49317280/199317541-bf460fae-af70-4055-a332-ece4e2018861.mp4


- ColorPicker.kt is an Extended view class which inherits from the SeekBar class
	. **in progress**.

