package com.example.activityforresultdemo

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.activityforresultdemo.fragment.FragmentResultApiBasicFragment
import com.example.activityforresultdemo.fragment.FragmentResultApiSecondaryFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentResultApiTest {

    @Test
    fun testFragmentResultListener() {
        val expectedResult = "result"
        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_ActivityforResultDemo) {
            FragmentResultApiBasicFragment()
        }
//        JJACK10
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment { fragment ->
            fragment.parentFragmentManager.setFragmentResult("key:basic", bundleOf("resultKey:basic" to expectedResult))
        }

        onView(ViewMatchers.withId(R.id.tv_text_test)).check(ViewAssertions.matches(ViewMatchers.withText(expectedResult)))
    }
}
