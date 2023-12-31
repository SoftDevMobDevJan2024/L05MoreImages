package au.edu.swin.sdmd.l04_moreimages

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.AllOf.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testImageChangeOnClick() {
        val materialButton = onView(allOf(withId(R.id.college)))
        materialButton.perform(click())
        val imageView = onView(
            allOf(withId(R.id.imageView), withContentDescription("college")))
        imageView.check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun testImageSaveOnRotation() {
        val materialButton = onView(allOf(withId(R.id.college)))
        materialButton.perform(click())
        var imageView = onView(
            allOf(withId(R.id.imageView), withContentDescription("college")))
        imageView.check(ViewAssertions.matches(isDisplayed()))

        mActivityScenarioRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        imageView = onView(
            allOf(withId(R.id.imageView), withContentDescription("college")))
        imageView.check(ViewAssertions.matches(isDisplayed()))

    }
}