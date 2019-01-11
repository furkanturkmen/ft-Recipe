package com.furkanturkmen.recipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Recipe> recipes = new ArrayList<>();
    private RecipeService mRecipeApiService;

    private PagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        requestData();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), recipes);
        mPager.setAdapter(mSectionsPagerAdapter);

    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Recipe> recipes;

        public SectionsPagerAdapter(FragmentManager fm, List<Recipe> recipes) {
            super(fm);
            this.recipes = recipes;
        }

        @Override
        public Fragment getItem(int position) {
            return RecipeFragment.newInstance(recipes.get(position));
        }

        @Override
        public int getCount() {
            return recipes.size();
        }
    }

    private void requestData() {
        RecipeService service = RecipeService.retrofit.create(RecipeService.class);
        Call<RecipeResult> call = service.getRecipe();
        call.enqueue(new Callback<RecipeResult>() {
            @Override
            public void onResponse(Call<RecipeResult> call, Response<RecipeResult> response) {
                if (response.body() != null) {
                    recipes.addAll(response.body().recipes);
                }
                mSectionsPagerAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<RecipeResult> call, Throwable t) {

            }
        });

    }


}