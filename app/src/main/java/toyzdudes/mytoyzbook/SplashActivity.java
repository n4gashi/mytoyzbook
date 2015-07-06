package toyzdudes.mytoyzbook;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class SplashActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.hideActionBar();
        setContentView(this.getView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, FilterActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    protected ViewGroup getView() {
        // Root ViewGroup
        RelativeLayout root = new RelativeLayout(this);

        // Background ImageView
        ImageView bg = this.getBackground();

        // Logo ImageView
        ImageView logo = new ImageView(this);
        logo.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams logoLp = (RelativeLayout.LayoutParams) logo.getLayoutParams();
        logoLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        logoLp.addRule(RelativeLayout.CENTER_VERTICAL);
        final float scale = getResources().getDisplayMetrics().density;
        int logoMargin = (int) (50 * scale + 0.5f);
        logoLp.setMargins(logoMargin, 0, logoMargin, 0);

        logo.setLayoutParams(logoLp);
        logo.setImageResource(R.drawable.logo);

        root.addView(bg);
        root.addView(logo);

        return root;
    }
}
