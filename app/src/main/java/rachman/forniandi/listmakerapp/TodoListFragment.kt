package rachman.forniandi.listmakerapp

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rachman.forniandi.listmakerapp.databinding.FragmentTodolistBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoListFragment : Fragment(),TodoListAdapter.TodoListClickListener {

    private var _binding: FragmentTodolistBinding? = null
    //private var listener: OnFragmentInteractionListener?=null
    private lateinit var listDataManager:ListDataManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodolistBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            listDataManager =ViewModelProvider(this).get(ListDataManager::class.java)
        }

        val lists = listDataManager.readList()
        binding.listsRecyclerview.adapter = TodoListAdapter(lists,this)
        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
        binding.fabTodoList.setOnClickListener { _->
            //*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG) .setAction("Action", null).show()*//*
            //*val adapter = binding.includedMainLayout.listsRecyclerview.adapter as TodoListAdapter adapter.addNewItem()*//*
                    showCreateTodoListDialog()
        }




    }

    private fun showCreateTodoListDialog() {
        val dialogueTitle = getString(R.string.name_of_list)
        val positiveButtonTittle=getString(R.string.create_list)
        val dialog= activity?.let { AlertDialog.Builder(it) }
        val editTextDialog= EditText(activity)
        editTextDialog.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        dialog?.setTitle(dialogueTitle)
        dialog?.setView(editTextDialog)

        dialog?.setPositiveButton(positiveButtonTittle){
                dialog, _->
            //val adapter = binding.includedMainLayout.listsRecyclerview.adapter as TodoListAdapter
            val list = TaskList(editTextDialog.text.toString())
            addList(list)
            //listDataManager.saveList(list)
            //adapter.addList(list)
            dialog.dismiss()
            showTaskListItem(list)
        }
        dialog?.create()?.show()
    }

    private fun showTaskListItem(list: TaskList){
        /*val taskListItem= Intent(this,DetailActivity::class.java)
        taskListItem.putExtra(MainActivity.INTENT_LIST_KEY,list)
        startActivityForResult(taskListItem, MainActivity.LIST_DETAIL_REQUEST_CODE)*/

    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener){
            //listener= context
            //listDataManager =ListDataManager(context)
        }

    }*/

    /*override fun onDetach() {
        super.onDetach()
        listener = null
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list: TaskList)
    }

    override fun listItemClicked(list: TaskList) {
        //listener?.onTodoListClicked(list)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val todoAdapter = binding.listsRecyclerview.adapter as TodoListAdapter
        todoAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val listsOnTodolist = listDataManager.readList()
        binding.listsRecyclerview.adapter= TodoListAdapter(listsOnTodolist,this)
        //binding.includedMainLayout.listsRecyclerview.adapter = TodoListAdapter(listsOnTodolist,this)

    }
}