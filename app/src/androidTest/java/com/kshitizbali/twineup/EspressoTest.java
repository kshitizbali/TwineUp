package com.kshitizbali.twineup;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.kshitizbali.twineup.ui.main.ScrollingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    private String stringActivate;

    @Rule
    public ActivityTestRule<ScrollingActivity> activityRule
            = new ActivityTestRule<>(ScrollingActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringActivate = "Susie";
    }


    @Test
    public void scrollToItemCheckClickAndText() {

        // First, scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.rvSavings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,
                        click()));

        // Match the text in an item below the fold and check that it's displayed.
        /*String itemElementText = activityRule.getActivity().getResources()
                .getString(R.string.demo_activate)
                *//*+ String.valueOf(2)*//*;*/
        onView(withText(stringActivate)).check(matches(isDisplayed()));


        onView(withId(R.id.rvSavings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        onView(withId(R.id.rvSavings)).perform(scrollToPosition(5));

        onView(withId(R.id.rvSavings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5,
                        click()));


    }

   /* @Test
    public void itemInTheMiddleHasTextLoaded(){
        // First, scroll to the view holder using the isInTheMiddle() matcher.
        onView(withId(R.id.rvSavings))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));

        // Check that the item has the special text.
        String middleElementText =
                activityRule.getActivity().getResources()
                        .getString(R.string.demo_paused);
        onView(withText(middleElementText)).check(matches(isDisplayed()));
    }*/

}
