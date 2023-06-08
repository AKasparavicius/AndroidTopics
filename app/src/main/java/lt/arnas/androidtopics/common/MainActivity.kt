package lt.arnas.androidtopics.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.first_fragment.FirstFragment
import lt.vcs.demoapp.ActivityLifecyclesPresentation

class MainActivity : ActivityLifecyclesPresentation() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(FirstFragment.newInstance(), FirstFragment.TAG)

        if(
            supportFragmentManager.fragments.isEmpty() ||
                    supportFragmentManager.fragments[0] is FirstFragment
        ) {
            openFragment(FirstFragment.newInstance(), FirstFragment.TAG)
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.run {
//
//        }
//        super.onSaveInstanceState(outState)
//    }

    public fun openFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                fragment,
                tag
            )
            setReorderingAllowed(true)
            if (fragment is FirstFragment == false) {
                addToBackStack(tag)
            }
        }
    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(
            this,
        object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val fragment = supportFragmentManager
                    .findFragmentById(R.id.fragmentContainerView)

                if(fragment is FirstFragment){
                    finish()
                }

                supportFragmentManager.popBackStack()
            }
        }
        )
    }
}