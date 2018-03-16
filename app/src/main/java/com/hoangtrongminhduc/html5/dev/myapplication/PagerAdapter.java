package com.hoangtrongminhduc.html5.dev.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HTML5 on 16/03/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter{
    PagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new Tem();
                break;
            case 1:
                frag = new Humi();
                break;

        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
