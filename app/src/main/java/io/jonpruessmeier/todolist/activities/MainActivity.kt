package io.jonpruessmeier.todolist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.view.View
import android.view.ViewGroup
import android.widget.*
import io.jonpruessmeier.todolist.R
import io.jonpruessmeier.todolist.model.ToDo
import io.jonpruessmeier.todolist.model.ToDoList
import io.jonpruessmeier.todolist.views.ToDoView

class MainActivity : AppCompatActivity() {
    

    private val toDos = ToDoList();
    private lateinit var addToDoText: EditText;
    private lateinit var addToDoBtn: Button;
    private lateinit var listView: LinearLayout;

    private val SPACE_HEIGHT: Int = 15;
    private val SPACE_WIDTH: Int = ViewGroup.LayoutParams.WRAP_CONTENT;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instantiating the Views:
        addToDoText = findViewById(R.id.addToDoEditText);
        addToDoBtn = findViewById(R.id.addToDoButton);
        listView = findViewById(R.id.todolist);

        createViewableList();

        addToDoBtn.setOnClickListener{
            submitToDo();
        }
    }



    private fun submitToDo(): Unit{
        //fill model:
        var newToDo: String = addToDoText.text.toString();
        toDos.addToDo(newToDo);

        //update views:
        createViewableList();
        addToDoText.setText("");
    }

    private fun checkBoxHandler(checkBox: CheckBox): Unit{
        var toDoView: ToDoView = checkBox.parent as ToDoView;
        var id = toDoView.toDoId;

        this.toDos.updateToDoByID(id);
        createViewableList();
    }


    private fun fillListWithViews(list: ArrayList<ToDo>): Unit{
        list.forEach{
            val toDoView = ToDoView(it, this);
            toDoView.checkBox.setOnClickListener{
                checkBoxHandler(it as CheckBox);
            }

            val space: Space = createSpaceView();
            listView.addView(toDoView);
            listView.addView(space);
        }
    }

    private fun createSpaceView(): Space{
        val space: Space = Space(this);
        space.minimumHeight = SPACE_HEIGHT;
        space.minimumWidth = SPACE_WIDTH;
        return space;
    }

    private fun createViewableList(): Unit{

        //clear all possible existing Views for recreating the Layout in order to the existing ToDos
        listView.removeAllViews();

        //getting the lists of ToDos with the open ones at the top
        val openToDos = toDos.getOpenToDos();
        val closedToDos = toDos.getClosedToDos();

        //updating the views:
        fillListWithViews(openToDos);
        fillListWithViews(closedToDos);

    }






}