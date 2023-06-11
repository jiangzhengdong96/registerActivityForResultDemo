package com.example.activityforresultdemo

import com.example.activityforresultdemo.R
import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RegisterActivityForResultTest {

    companion object {
        private const val TEST_RESULT= "callback result for: "
    }

    @get:Rule
    val activityRule: ActivityScenarioRule<CommonActivity> = ActivityScenarioRule(CommonActivity::class.java)

    @Test
    fun registerForActivityResultTest() {
        activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        activityRule.scenario.onActivity { activity: CommonActivity ->
            val launcher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                result.data?.getStringExtra("my_result_key")?.let {
                    activity.changeTvTextForUITest(it)
                }
            }

            val intent = Intent(activity, SecondaryActivity::class.java)
            launcher.launch(intent)
        }
        onView(withId(R.id.btn_close)).perform(click())
        activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.tv_name_test)).check(matches(withText(TEST_RESULT)))
    }
}