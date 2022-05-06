package rachman.forniandi.listmakerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rachman.forniandi.listmakerapp.databinding.TaskViewHolderBinding
import rachman.forniandi.listmakerapp.databinding.TodoListViewHolderBinding

class TaskListAdapter(private val list: TaskList): RecyclerView.Adapter<TaskListAdapter.TaskListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListHolder {
        val binding:TaskViewHolderBinding = TaskViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskListHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListHolder, position: Int) {
        holder.detailTask.text = list.tasks[position]
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

    inner class TaskListHolder (binding: TaskViewHolderBinding): RecyclerView.ViewHolder(binding.root){
        val detailTask = binding.textviewTask
    }
}