package retrofit.aishwarya.com.customview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomActionBarClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomActionView mBottomActionView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomActionView = (BottomActionView) findViewById(R.id.bottom_bar);
        if (mBottomActionView != null) {
            mBottomActionView.setBottomBarClickListener(this);
        }
        initViewPager();
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavoritesFragment());
        adapter.addFragment(new LocationFragment());
        adapter.addFragment(new HotelsFragment());
        adapter.addFragment(new RecentsFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClicked(BottomActionView bottomActionView, int buttonPosition) {
        switch(buttonPosition) {
            case 0:
                mViewPager.setCurrentItem(0);
                Toast.makeText(this, "Favorites clicked", Toast.LENGTH_LONG).show();
                break;
            case 1:
                mViewPager.setCurrentItem(1);
                Toast.makeText(this, "Location clicked", Toast.LENGTH_LONG).show();
                break;
            case 2:
                mViewPager.setCurrentItem(2);
                Toast.makeText(this, "Hotels clicked", Toast.LENGTH_LONG).show();
                break;
            case 3:
                mViewPager.setCurrentItem(3);
                Toast.makeText(this, "Recents clicked", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
