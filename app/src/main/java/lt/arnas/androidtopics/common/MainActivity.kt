package lt.arnas.androidtopics.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import lt.arnas.androidtopics.R
import lt.arnas.androidtopics.first_fragment.FirstFragment
import lt.vcs.demoapp.ActivityLifecyclesPresentation

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment()
    }

    private fun openFragment() {
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