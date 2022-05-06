package rachman.forniandi.listmakerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rachman.forniandi.listmakerapp.databinding.TodoListViewHolderBinding

class TodoListAdapter(private val lists: ArrayList<TaskList>, val clickListener: TodoListClickListener): RecyclerView.Adapter<TodoListAdapter.TodolistViewHolder>() {

    private var todolist = mutableListOf("Android Development","Ios Development","Web Development","Machine Learning")

    fun addNewItem(listName:String){
        if (listName.isEmpty()){
            todolist.add("Todo List " + (todolist.size+1))
        }else{
            todolist.add(listName)
        }

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodolistViewHolder {
        val binding:TodoListViewHolderBinding = TodoListViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodolistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodolistViewHolder, position: Int) {
        val data = lists[position]
        holder.number.text = (position + 1).toString()
        holder.subject.text = data.name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(data)
        }

    }
    fun addList(list: TaskList){
        lists.add(list)
        notifyItemInserted(lists.size-1)
    }
    override fun getItemCount(): Int {
        return lists.size
    }


    inner class TodolistViewHolder(binding:TodoListViewHolderBinding): RecyclerView.ViewHolder(binding.root){
        val number = binding.itemNumber
        val subject = binding.itemString
    }

    interface TodoListClickListener{
        fun listItemClicked(list: TaskList)
    }


}


