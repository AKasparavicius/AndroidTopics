package lt.arnas.androidtopics.first_fragment

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.common.MainActivity
import lt.arnas.androidtopics.databinding.FragmentFirstBinding
import lt.arnas.androidtopics.second_fragment.SecondFragment
import lt.vcs.demoapp.common.FragmentLifecyclesPresentation
import lt.vcs.demoapp.second_fragment.Item

class FirstFragment : FragmentLifecyclesPresentation() {

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
        onClickOpenButton()
        receiveDataFromSecondFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickOpenButton() {
        binding.openSecondFragmentButton.setOnClickListener {
//            val mainActivity = activity as MainActivity
//            mainActivity.openFragment()
            (activity as MainActivity).openFragment(SecondFragment.newInstance(), SecondFragment.TAG)
            transferDataToSecondFragment()
        }
    }

    private fun transferDataToSecondFragment() {
        val bundle = bundleOf(
            "ff_text" to "text_from_first_fragment",
            "ff_item" to Item(1, "txt01", "txt01")
        )
        setFragmentResult("first_fragment_result_key", bundle)
    }

    private fun receiveDataFromSecondFragment() {
        setFragmentResultListener("second_fragment_result_key") { requestKey, bundle ->
            val text = bundle.getString("sf_text")
            val item = bundle.getParcelable<Item>("sf_item")
            binding.textView.text = """
                        $text
                        +++++++++++++++++
                        ${item.toString()}
                        """.trimIndent()
        }
    }

    companion object {
        const val TAG = "first_fragment"
        fun newInstance() = FirstFragment()
    }



}