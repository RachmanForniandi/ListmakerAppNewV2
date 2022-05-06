package rachman.forniandi.listmakerapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import rachman.forniandi.listmakerapp.MainActivity.Companion.INTENT_LIST_KEY
import rachman.forniandi.listmakerapp.databinding.ActivityDetailBinding
import rachman.forniandi.listmakerapp.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {

    lateinit var listKey:TaskList
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_detail)
        setContentView(binding.root)
        listKey = intent.getParcelableExtra<TaskList>(MainActivity.INTENT_LIST_KEY) as TaskList
        title = listKey.name

        binding.taskListRecyclerview.adapter = TaskListAdapter(listKey)

        /*binding.taskListRecyclerview.layoutManager =LinearLayoutManager(this)
        binding.taskListRecyclerview.adapter = TaskListAdapter(listKey)*/
        binding.btnAddTask.setOnClickListener {
            showCreateTaskDialog()
        }

    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,listKey)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK,intent)
        super.onBackPressed()

    }

    private fun showCreateTaskDialog() {
        val editTextDialog= EditText(this)
        editTextDialog.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(editTextDialog)
            .setPositiveButton(R.string.add_task){
                    dialog, _->
                val task = editTextDialog.text.toString()
                listKey.tasks.add(task)
                dialog.dismiss()
            }
            .create()
            .show()


    }
}