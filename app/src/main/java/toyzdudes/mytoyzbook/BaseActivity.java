package toyzdudes.mytoyzbook;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class BaseActivity extends ActionBarActivity
{

    public void hideActionBar()
    {
        if (Build.VERSION.SDK_INT < 16)
        {
            // Hide the action bar
            if(getSupportActionBar() != null)
                getSupportActionBar().hide();
        }
        else
        {
            if(getActionBar() != null)
                getActionBar().hide();

            if(getSupportActionBar() != null)
                getSupportActionBar().hide();

        }
    }

    public void hideStatusBar()
    {
        if (Build.VERSION.SDK_INT < 16)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    protected ImageView getBackground()
    {
        // Background ImageView
        ImageView bg = new ImageView(this);
        bg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        bg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bg.setImageResource(R.drawable.mtb_background);

        return bg;
    }

}
