package lt.arnas.androidtopics.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lt.arnas.androidtopics.R
import lt.vcs.demoapp.ActivityLifecyclesPresentation

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}