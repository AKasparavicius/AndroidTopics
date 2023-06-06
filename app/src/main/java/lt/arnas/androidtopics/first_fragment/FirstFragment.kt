package lt.arnas.androidtopics.first_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lt.arnas.androidtopics.R
import lt.vcs.demoapp.common.FragmentLifecyclesPresentation

class FirstFragment : FragmentLifecyclesPresentation() {


    private val viewModel: FirstFragmentViewModel by viewModels()


    private lateinit var viewModel: FirstFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    companion object {

        fun newInstance() = FirstFragment()
    }



}