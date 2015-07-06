package toyzdudes.mytoyzbook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import toyzdudes.mytoyzbook.workers.FilterPage;


public class FilterActivity extends BaseActivity {

    public static final int NUM_PAGES = 3;
    private NonSwipeableViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.hideActionBar();
        //setContentView(R.layout.activity_filter);
        setContentView(this.getView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (NonSwipeableViewPager) findViewById(R.id.filter_viewpager);
        mPagerAdapter = new FilterPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    protected ViewGroup getView() {
        // Root ViewGroup
        RelativeLayout root = new RelativeLayout(this);
        ImageView bg = this.getBackground();

        // VIEW ALL button (skip filters)
        Button viewAllBtn = new Button(this);
        viewAllBtn.setText("VOIR TOUS");

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        final float scale = getResources().getDisplayMetrics().density;

        int btnMargin = (int) (30 * scale + 0.5f);
        buttonParams.setMargins(0,0,0,btnMargin);

        int btnPadding = (int) (20 * scale + 0.5f);
        viewAllBtn.setLayoutParams(buttonParams);
        viewAllBtn.setPadding(btnPadding, 0, btnPadding, 0);

        NonSwipeableViewPager filtersArray = new NonSwipeableViewPager(this);
        filtersArray.setId(R.id.filter_viewpager);
        filtersArray.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        root.addView(bg);
        root.addView(filtersArray);
        root.addView(viewAllBtn);

        return root;
    }

    @Override
    public void onBackPressed() {
        if(this.mPager.getCurrentItem() > 0) {
            this.mPager.setCurrentItem(this.mPager.getCurrentItem()-1, true);
        } else {
            super.onBackPressed(); // This will pop the Activity from the stack.
        }
    }

    private class FilterPagerAdapter extends FragmentStatePagerAdapter {
        public FilterPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FilterFragment frag = FilterFragment.newInstance(position);
            return frag;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        public void back()
        {

        }
    }
}
