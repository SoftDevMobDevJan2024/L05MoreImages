package au.edu.swin.sdmd.l04_moreimages

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatDelegate

const val KEY_IMAGE = "image_key"

class MainActivity : AppCompatActivity() {

    private var uiMode: Int = Configuration.UI_MODE_NIGHT_NO

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "stopped")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.imageView)

        /**
         * This is the code to restore the state.
         *
         * The override function onRestoreInstanceState can also be used, however
         * this is called at a different point in the lifecycle.
         */
        savedInstanceState?.let {
            image.contentDescription = it.getString(KEY_IMAGE)

            image.apply {
                when (contentDescription) {
                    "station" -> setImageDrawable(
                        getDrawable(
                            R.drawable.station
                        )
                    )
                    "college" -> setImageDrawable(
                        getDrawable(
                            R.drawable.college
                        )
                    )
                    "theatre" -> setImageDrawable(
                        getDrawable(
                            R.drawable.theatre
                        )
                    )
                }
            }
            Log.i("LIFECYCLE", "onRestoreInstanceState")
        }


        val station = findViewById<Button>(R.id.station)
        station.setOnClickListener {
            image.setImageDrawable(
                getDrawable(R.drawable.station))
            image.contentDescription = "station"
        }

        val onClickTheatre = View.OnClickListener {
            image.setImageDrawable(
                getDrawable(R.drawable.theatre))
            image.contentDescription = "theatre"
        }

        val theatre = findViewById<Button>(R.id.theatre)
        theatre.setOnClickListener(onClickTheatre)

        // change theme button
        val btnTheme = findViewById<ToggleButton>(R.id.theme)
        btnTheme.apply {
            textOff = resources.getString(R.string.Light)
            textOn = resources.getString(R.string.Dark)
            isChecked  = if (isDarkMode()) true else false
            setOnClickListener {
                // important: must use this.context
                switchDayNightMode(this.context)
            }
        }

//        val switchTheme = findViewById<Switch>(R.id.switchTheme)
//        switchTheme.apply {
//            isChecked  = if (isDarkMode()) true else false
//            text  = if (isDarkMode()) "Dark" else "Light"
//            setOnClickListener {
//                switchDayNightMode(this.context)
//            }
//        }
    }

    /**
     * @effects
     *  switch between Day and Night mode on device
     */
    private fun switchDayNightMode(context: Context) {
        val currUIMode = context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK

        uiMode = when(currUIMode) {
            Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.MODE_NIGHT_NO
            else ->
                AppCompatDelegate.MODE_NIGHT_YES
        }

        AppCompatDelegate.setDefaultNightMode(uiMode)
    }


    private fun isDarkMode() :Boolean {
        return uiMode == Configuration.UI_MODE_NIGHT_YES
    }

    /**
     * This is needed to save state. The variables to save need to be
     * added to a Bundle with a key, in this case "image".
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val image = findViewById<ImageView>(R.id.imageView)
        outState.putString(KEY_IMAGE, image.contentDescription.toString())

        Log.i("LIFECYCLE", "onSaveInstanceState")
    }



    fun onClickCollege(v: View) {
        val image = findViewById<ImageView>(R.id.imageView)
        image.setImageDrawable(
            getDrawable(R.drawable.college))
        image.contentDescription = "college"
    }
}