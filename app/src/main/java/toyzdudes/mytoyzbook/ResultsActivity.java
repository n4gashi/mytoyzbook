package toyzdudes.mytoyzbook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import toyzdudes.mytoyzbook.workers.Toy;


public class ResultsActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView mToyzListView = null;
    public static final String RESULT_LIST_EXTRA = "result_list_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        this.mToyzListView = (ListView)findViewById(R.id.resultList);
        ArrayList<Toy> toyzList = new ArrayList<>();
        try {
            toyzList.add(new Toy(new JSONObject("{ \"id\":1, \"titre\":\"Toyz1\" }")));
            toyzList.add(new Toy(new JSONObject("{ \"id\":2, \"titre\":\"Toyz2\", image:\"http://mytoyzbook.alwaysdata.net/img/810.jpg\" }")));
            toyzList.add(new Toy(new JSONObject("{ \"id\":3, \"titre\":\"Toyz3\" }}")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ToyzAdapter adapter = new ToyzAdapter(this, toyzList);
        this.mToyzListView.setAdapter(adapter);
        this.mToyzListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ViewGroup getView()
    {
        // Root ViewGroup
        RelativeLayout root = new RelativeLayout(this);
        ListView listView = new ListView(this);

        root.addView(listView);

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO : GO VERS INTENT
    }
}
