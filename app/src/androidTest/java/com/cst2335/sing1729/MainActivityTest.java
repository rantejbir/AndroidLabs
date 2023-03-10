package com.cst2335.sing1729;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.Password));
        appCompatEditText2.perform(replaceText("12345"));

        ViewInteraction materialButton = onView(withId(R.id.recieve));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("You shall not pass")));
    }

    @Test
    public void testFindMissingUpperCase() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.Password));
        appCompatEditText2.perform(replaceText("password123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.recieve));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("You shall not pass")));
    }
    @Test
    public void testFindMissingLowerCase() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.Password));
        appCompatEditText2.perform(replaceText("PASSWORD123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.recieve));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("You shall not pass")));
    }
    @Test
    public void testFindMissingDigit() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.Password));
        appCompatEditText2.perform(replaceText("Password#$*"));

        ViewInteraction materialButton = onView(withId(R.id.recieve));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("You shall not pass")));
    }
    @Test
    public void testFindMissingSpecialCase() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.Password));
        appCompatEditText2.perform(replaceText("Password123"));

        ViewInteraction materialButton = onView(withId(R.id.recieve));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("You shall not pass")));
    }
    @Test
    public void testFindInvalidLength() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.Password));
        appCompatEditText2.perform(replaceText("passworrrrrrrrrrrrrrrrrrrrrrd123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.recieve));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("You shall not pass")));
    }
    @Test
    public void complexEnough() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.Password));
        appCompatEditText2.perform(replaceText("Password123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.recieve));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("You meet the requirements")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
