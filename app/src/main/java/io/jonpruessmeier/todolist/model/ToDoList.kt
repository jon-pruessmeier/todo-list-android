package io.jonpruessmeier.todolist.model

class ToDoList {

    private val list: ArrayList<ToDo> = ArrayList();
    private var highestID: Int = 0;

    fun addToDo(text: String, isChecked: Boolean = false): Unit{
        var toDo = ToDo(highestID, text, isChecked);
        list.add(toDo);
        highestID++;
    }

    fun updateToDoByID(id: Int): Unit{
       list.forEach{
           if(it.id === id){
               it.isChecked = !it.isChecked
           }
       }
    }

    fun removeToDoByID(id: Int): Unit{
        this.list.removeAt(id);
    }

    fun getAllToDos(): ArrayList<ToDo>{
        return this.list;
    }

    fun getOpenToDos(): ArrayList<ToDo>{
        var openToDos = ArrayList<ToDo>();
        this.list.forEach{
            if(!it.isChecked){
                openToDos.add(it);
            }
        }
        return openToDos;
    }

    fun getClosedToDos(): ArrayList<ToDo>{
        var closedToDos = ArrayList<ToDo>();
        this.list.forEach{
            if(it.isChecked){
                closedToDos.add(it);
            }
        }
        return closedToDos;
    }


}