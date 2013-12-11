package info.androidhive.tabsswipe.adapter;
 
import edu.berkeley.cs160.wildebeest.strendly.BarGraphFragment;
import edu.berkeley.cs160.wildebeest.strendly.LineGraphFragment;
import edu.berkeley.cs160.wildebeest.strendly.PieChartFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Bar Graph fragment activity
            return new BarGraphFragment();
        case 1:
            // Pie Chart fragment activity
            return new PieChartFragment();
        case 2:
            // Line Graph fragment activity
            return new LineGraphFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}