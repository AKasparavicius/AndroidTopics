package lt.arnas.androidtopics.first_fragment

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.databinding.FragmentFirstBinding
import lt.vcs.demoapp.common.FragmentLifecyclesPresentation

class FirstFragment : FragmentLifecyclesPresentation() {

    private val viewModel: FirstFragmentViewModel by viewModels()
    private var _binding: FragmentFirstBinding ? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickOpenButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickOpenButton() {
        binding.openSecondFragmentButton.setOnClickListener {
            supportFragmentManager.commit {
                replace(
                    R.id.fragmentContainerView,
                    FirstFragment.newInstance(),
                    "first_fragment"
                )
                setReorderingAllowed(true)
            }
        }
    }


    companion object {

        fun newInstance() = FirstFragment()
    }



}