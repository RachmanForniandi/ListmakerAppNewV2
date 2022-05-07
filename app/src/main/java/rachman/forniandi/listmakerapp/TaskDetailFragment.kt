package rachman.forniandi.listmakerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
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