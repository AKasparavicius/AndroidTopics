package lt.arnas.androidtopics.second_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.common.MainActivity
import lt.arnas.androidtopics.databinding.FragmentFirstBinding
import lt.arnas.androidtopics.databinding.FragmentSecondBinding
import lt.arnas.androidtopics.first_fragment.FirstFragmentViewModel
import lt.vcs.demoapp.common.FragmentLifecyclesPresentation
import lt.vcs.demoapp.second_fragment.Item

class SecondFragment : FragmentLifecyclesPresentation() {

    private val viewModel: SecondFragmentViewModel by viewModels()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportFragmentManager.fragments
//        receiveDataFromFirstFragment()
    }

    override fun onPause() {
        super.onPause()
        transferDataToFirstFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun receiveDataFromFirstFragment() {
        setFragmentResultListener("first_fragment_result_key") { requestKey, bundle ->
            val text = bundle.getString("ff_text")
            val item = bundle.getParcelable<Item>("ff_item")
            binding.textView.text = """
                        $text
                        +++++++++++++++++
                        ${item.toString()}
                        """.trimIndent()
        }
    }

    private fun transferDataToFirstFragment() {
        val bundle = bundleOf(
            "sf_text" to "text_from_second_fragment",
            "sf_item" to Item(1, "txt01_02", "txt02_02")
        )
        setFragmentResult("second_fragment_result_key", bundle)
    }


    companion object {
        const val TAG = "second_fragment"
        fun newInstance() = SecondFragment()
    }


}