package toyzdudes.mytoyzbook;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;


import toyzdudes.mytoyzbook.helpers.DebugHelper;
import toyzdudes.mytoyzbook.helpers.StorageHelper;
import toyzdudes.mytoyzbook.workers.FilterPage;
import toyzdudes.mytoyzbook.workers.MTBRestClient;
import toyzdudes.mytoyzbook.workers.Toy;

public class FilterFragment extends Fragment {

    private String mTitle;
    private String mFilterPrefName;
    private ArrayList<FilterPage> mFilters;
    private NonSwipeableViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.filter_page, container, false);
        Button button = null;
        TextView title = (TextView)rootView.findViewById(R.id.filter_name);
        TextView label = null;

        this.mTitle = getArguments().getString("FILTER_TITLE");
        this.mFilterPrefName = getArguments().getString("FILTER_PREF_NAME");
        this.mFilters = getArguments().getParcelableArrayList("FILTER_LIST");
        this.mViewPager = (NonSwipeableViewPager)container;

        for(final FilterPage filter : mFilters)
        {
            final int btnIndex = filter.getBtnIndex();
            final String constValue = filter.getConstValue();

            switch(btnIndex)
            {
                case 1:
                    button = (Button)rootView.findViewById(R.id.first_button);
                    label = (TextView)rootView.findViewById(R.id.first_text);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FilterFragment.this.buttonClick(FilterFragment.this.mFilterPrefName, constValue);
                            DebugHelper.log('d', FilterFragment.this.mFilterPrefName);
                        }
                    });
                    break;
                case 2:
                    button = (Button)rootView.findViewById(R.id.second_button);
                    label = (TextView)rootView.findViewById(R.id.second_text);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FilterFragment.this.buttonClick(FilterFragment.this.mFilterPrefName, constValue);
                        }
                    });
                    break;
                case 3:
                    button = (Button)rootView.findViewById(R.id.third_button);
                    label = (TextView)rootView.findViewById(R.id.third_text);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FilterFragment.this.buttonClick(FilterFragment.this.mFilterPrefName, constValue);
                        }
                    });
                    break;
                default:
                    break;
            }

            if(title != null)
            {
                Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Pacifico-Light.ttf");
                title.setText(this.mTitle);
                title.setTypeface(typeface);
                title.setTextSize(getResources().getDimension(R.dimen.filter_title_size));
            }

            if (button != null)
                button.setBackgroundResource(filter.getDrawable());

            if(label != null)
                label.setText(filter.getTitle());

        }

        return rootView;
    }

    public static FilterFragment newInstance(int itemPos)
    {
        FilterFragment frag = new FilterFragment();
        Bundle bundle = new Bundle();

        ArrayList<FilterPage> filters = new ArrayList<>();

        switch(itemPos)
        {
            case 0:
                bundle.putString("FILTER_TITLE", "Pour qui ?");
                bundle.putString("FILTER_PREF_NAME", "WHO_FILTER");
                filters.add(new FilterPage("Pour lui", "FOR_HIM_VALUE", R.drawable.ic_filter_man, "homme", 1));
                filters.add(new FilterPage("Pour elle", "FOR_HER_VALUE", R.drawable.ic_filter_woman, "femme", 2));
                filters.add(new FilterPage("Pour eux", "FOR_THEM_VALUE", R.drawable.ic_filter_couple, "couple", 3));
                break;
            case 1:
                bundle.putString("FILTER_TITLE", "Quelle utilisation ?");
                bundle.putString("FILTER_PREF_NAME", "USING_FILTER");
                filters.add(new FilterPage("Point G", "GPOINT_VALUE", R.drawable.ic_filter_pointg, "pointg", 1));
                filters.add(new FilterPage("Pour elle", "CLITO_VALUE", R.drawable.ic_filter_clito, "clitoris", 2));
                filters.add(new FilterPage("Pour eux", "ANAL_VALUE", R.drawable.ic_filter_anal, "anal", 3));
                break;
            case 2:
                bundle.putString("FILTER_TITLE", "Caractéristique principale ?");
                bundle.putString("FILTER_PREF_NAME", "SPECIFICATION_FILTER");
                filters.add(new FilterPage("Vibrant", "VIBRATE_VALUE", R.drawable.ic_filter_vibrating, "vibrant", 1));
                filters.add(new FilterPage("Silicone", "SILICONE_VALUE", R.drawable.ic_filter_silicon, "silicone", 2));
                filters.add(new FilterPage("Waterproof", "WATERPROOF_VALUE", R.drawable.ic_filter_waterproof, "etanche", 3));
                break;
            default:
                break;
        }

        bundle.putParcelableArrayList("FILTER_LIST", filters);
        frag.setArguments(bundle);

        return frag;
    }

    public void buttonClick(String prefName, String constValue)
    {
        // Set userpref
        StorageHelper.editSharedPref(this.getActivity(), prefName, constValue);

        if(this.mViewPager.getCurrentItem() == FilterActivity.NUM_PAGES - 1)
        {
            // TODO : CALL HTTP THEN INTENT
            RequestParams params = new RequestParams();
            params.put("filter1", StorageHelper.getStringSharedPref(this.getActivity(), "WHO_FILTER"));
            params.put("filter2", StorageHelper.getStringSharedPref(this.getActivity(), "USING_FILTER"));
            params.put("filter3", StorageHelper.getStringSharedPref(this.getActivity(), "SPECIFICATION_FILTER"));

            MTBRestClient.get("get_productbyfilters.php", params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {

                        Intent in = new Intent(FilterFragment.this.getActivity(), ResultsActivity.class);
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
                        getActivity().finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

        this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() + 1, true);
    }
}
