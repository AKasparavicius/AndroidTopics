package lt.arnas.androidtopics.second_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.databinding.FragmentFirstBinding
import lt.arnas.androidtopics.databinding.FragmentSecondBinding
import lt.arnas.androidtopics.first_fragment.FirstFragmentViewModel
import lt.vcs.demoapp.common.FragmentLifecyclesPresentation

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() = SecondFragment()
    }


}