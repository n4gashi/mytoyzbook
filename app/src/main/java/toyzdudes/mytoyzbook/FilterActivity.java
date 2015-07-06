package toyzdudes.mytoyzbook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import toyzdudes.mytoyzbook.helpers.StorageHelper;
import toyzdudes.mytoyzbook.workers.FilterPage;
import toyzdudes.mytoyzbook.workers.MTBRestClient;
import toyzdudes.mytoyzbook.workers.Toy;


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
        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : CALL HTTP THEN INTENT
//                RequestParams params = new RequestParams();
//                params.put("filter1", StorageHelper.getStringSharedPref(this.getActivity(), "WHO_FILTER"));
//                params.put("filter2", StorageHelper.getStringSharedPref(this.getActivity(), "USING_FILTER"));
//                params.put("filter3", StorageHelper.getStringSharedPref(this.getActivity(), "SPECIFICATION_FILTER"));

                MTBRestClient.get(FilterActivity.this, "get_allproduct.php", null, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {

                            Intent in = new Intent(FilterActivity.this, ResultsActivity.class);
                            JSONArray products = response.getJSONArray("product");
                            ArrayList<Toy> toyz = new ArrayList<Toy>();

                            for (int i = 0; i < products.length(); i++) {
                                toyz.add(new Toy(products.getJSONObject(i)));
                            }

                            in.putParcelableArrayListExtra(ResultsActivity.RESULT_LIST_EXTRA, toyz);
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //in.putExtra(ResultsActivity.RESULT_LIST_EXTRA, new Toy(response));
                            startActivity(in);
                            FilterActivity.this.finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });

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
