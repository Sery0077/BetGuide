package dial.pro.betguide.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.onesignal.OneSignal
import dial.pro.betguide.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OneSignal.initWithContext(this)
        OneSignal.setAppId("34dc3743-cce3-4b8f-808a-f1c1b0365aba")
        
    }

    override fun onResume() {
        findNavController(R.id.fragment_container).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> window.setBackgroundDrawableResource(R.drawable.background)
                R.id.mainFragment -> BackgroundHelper.background_1?.let {
                    window.setBackgroundDrawable(it)
                }
                else -> {
                    BackgroundHelper.background_2?.let {
                        window.setBackgroundDrawable(it)
                    }
                }
            }
        }
        super.onResume()
    }
}