package rachman.forniandi.listmakerapp

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import rachman.forniandi.listmakerapp.databinding.FragmentTaskDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TaskDetailFragment : Fragment() {

    private var _binding: FragmentTaskDetailBinding? = null
    lateinit var list: TaskList

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            list = it?.getParcelable(ARG_LIST)!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.taskListRecyclerview.adapter= TaskListAdapter(list)
        binding.btnAddTask.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
        activity?.let {
            val editTextDialog= EditText(it)
            editTextDialog.inputType = InputType.TYPE_CLASS_TEXT
            AlertDialog.Builder(it)
                .setTitle(R.string.task_to_add)
                .setView(editTextDialog)
                .setPositiveButton(R.string.add_task){
                        dialog, _->
                    val task = editTextDialog.text.toString()
                    list.tasks.add(task)
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private val ARG_LIST="list"

        fun newInstance(list: TaskList): TaskDetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_LIST,list)

            val fragment = TaskDetailFragment()
            fragment.arguments = bundle
            return fragment
        }

    }
}