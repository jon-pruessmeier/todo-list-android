package io.jonpruessmeier.todolist.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import io.jonpruessmeier.todolist.R
import io.jonpruessmeier.todolist.model.ToDo


class ToDoView constructor (todo: ToDo, context: Context?) : LinearLayout(context) {
    val textView : TextView = TextView(context);
    val checkBox : CheckBox = CheckBox(context);
    val toDoId: Int = todo.id;

    init {
        textView.text = todo.text;
        checkBox.isChecked = todo.isChecked;
        textView.setTextColor(Color.BLACK);

        this.addView(textView);
        this.addView(checkBox);
        this.orientation = LinearLayout.HORIZONTAL;
        this.setBorder();
    }

    private fun setBorder(): Unit {
     var borderDraw: Drawable = resources.getDrawable(R.drawable.todo_border);
     this.background = borderDraw;
    }


    fun update(todo: ToDo): Unit {
        this.textView.text = todo.text;
        this.checkBox.isChecked = todo.isChecked;
    }

}