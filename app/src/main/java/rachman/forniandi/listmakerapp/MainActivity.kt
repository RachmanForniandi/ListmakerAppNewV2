package rachman.forniandi.listmakerapp

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import rachman.forniandi.listmakerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),TodoListFragment.OnFragmentInteractionListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val listDataManager:ListDataManager = ListDataManager(this)
    private var todoListFragment = TodoListFragment.newInstance()

    companion object{
        const val INTENT_LIST_KEY = "LIST"
        const val LIST_DETAIL_REQUEST_CODE=123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        //val lists = listDataManager.readList()

        /*val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/
        //binding.includedMainLayout.listsRecyclerview.layoutManager = LinearLayoutManager(this)
        //binding.includedMainLayout.listsRecyclerview.adapter = TodoListAdapter(lists,this)

        binding.fab.setOnClickListener { _->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            /*val adapter = binding.includedMainLayout.listsRecyclerview.adapter as TodoListAdapter
            adapter.addNewItem()*/
            showCreateTodoListDialog()
        }
        /*supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,todoListFragment)
            .commit()*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LIST_DETAIL_REQUEST_CODE){
            data.let {
                val list = data?.getParcelableExtra<TaskList>(INTENT_LIST_KEY)!!
                todoListFragment.saveList(list)
                //listDataManager.saveList(list)
                //updateLists()

            }
        }
    }

    /*private fun updateLists() {
        val listsUpdate = listDataManager.readList()
        binding.includedMainLayout.listsRecyclerview.adapter = TodoListAdapter(listsUpdate,this)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateTodoListDialog(){
        val dialogueTitle = getString(R.string.name_of_list)
        val positiveButtonTittle=getString(R.string.create_list)
        val dialog= AlertDialog.Builder(this)
        val editTextDialog= EditText(this)
        editTextDialog.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        dialog.setTitle(dialogueTitle)
        dialog.setView(editTextDialog)

        dialog.setPositiveButton(positiveButtonTittle){
            dialog, _->
            //val adapter = binding.includedMainLayout.listsRecyclerview.adapter as TodoListAdapter
            val list = TaskList(editTextDialog.text.toString())
            todoListFragment.addList(list)
            //listDataManager.saveList(list)
            //adapter.addList(list)
            dialog.dismiss()
            showTaskListItem(list)
        }
        dialog.create().show()
    }

    private fun showTaskListItem(list: TaskList){
        val taskListItem= Intent(this,DetailActivity::class.java)
        taskListItem.putExtra(INTENT_LIST_KEY,list)
        startActivityForResult(taskListItem, LIST_DETAIL_REQUEST_CODE)

    }

    /*override fun listItemClicked(list: TaskList) {
        showTaskListItem(list)
    }*/

    override fun onTodoListClicked(list: TaskList) {
        showTaskListItem(list)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}