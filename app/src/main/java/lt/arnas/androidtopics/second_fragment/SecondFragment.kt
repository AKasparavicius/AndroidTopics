package lt.arnas.androidtopics.second_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.first_fragment.FirstFragmentViewModel
import lt.vcs.demoapp.common.FragmentLifecyclesPresentation

class SecondFragment : FragmentLifecyclesPresentation() {

    private val viewModel: SecondFragmentViewModel by viewModels()

    private lateinit var viewModel: SecondFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    companion object {
        fun newInstance() = SecondFragment()
    }


}