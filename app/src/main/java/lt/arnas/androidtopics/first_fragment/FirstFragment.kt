package lt.arnas.androidtopics.first_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {


    private val viewModel: FirstFragmentViewModel by viewModels()
    private var _binding: FragmentFirstBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUsers()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.itemsStateFlow.collect { response ->
                    val list = response?.userList

                    Log.i(TAG, "onViewCreated: $list")

//                    var myText = ""

                    if (list != null){
//                        for(item in list){
//                            myText += "${item}\n\n"
//                        }
                        val stringBuilder = buildString {
                            list.forEach { append("$it\n\n") }
                        }

                        binding.textView.text = stringBuilder
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        const val TAG = "my_first_fragment"

        fun newInstance() = FirstFragment()
    }



}