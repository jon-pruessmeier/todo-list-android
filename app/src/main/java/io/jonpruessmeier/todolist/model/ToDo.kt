package io.jonpruessmeier.todolist.model

import java.util.*

data class ToDo (val id: Int,
                 var text: String,
                 var isChecked: Boolean = false,
                 val dateCreated: Date = Calendar.getInstance().time)
