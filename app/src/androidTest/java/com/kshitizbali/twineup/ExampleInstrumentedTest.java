package com.kshitizbali.twineup;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.kshitizbali.twineup.ui.main.ScrollingActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<ScrollingActivity> activityRule
            = new ActivityTestRule<>(ScrollingActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();
        /*Context appContext = InstrumentationRegistry.getTargetContext();*/


        assertEquals("com.kshitizbali.twineup", appContext.getPackageName());

       /* onView(withId(R.id.tvSavingsName)).perform(click());

        onView(withId(R.id.tvStatus)).perform(click());

        onView(withId(R.id.tvPauseResume)).perform(click());

        onView(withText(R.string.monthly_deposit)).check(matches(isDisplayed()));*/
    }
}
