package net.ariflaksito.mymovie.ui.detail;

import android.content.Context;
import android.content.Intent;

import net.ariflaksito.mymovie.R;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class TvShowDetailActivityTest {
    private TvShowEntity dummyTvShow = FakeDataDummy.generateDummyTvShow();

    @Rule
    public ActivityTestRule<DetailActivity> tvShowActivityRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailActivity.class);
            result.putExtra(DetailActivity.EXTRA_MOVIE, dummyTvShow.getMovieId());
            result.putExtra(DetailActivity.EXTRA_IS_MOVIE, 0);
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTvShow() {
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShow.getTitle())));
    }

    @Test
    public void setFavorite(){
        onView(withId(R.id.action_bookmark)).perform(click());
    }

}