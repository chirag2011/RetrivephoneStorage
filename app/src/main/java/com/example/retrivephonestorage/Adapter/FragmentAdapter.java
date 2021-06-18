package com.example.retrivephonestorage.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.retrivephonestorage.Fragment.FirstFragment;
import com.example.retrivephonestorage.Fragment.SecondFragment;
import com.example.retrivephonestorage.Fragment.ThirdFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      /*  Fragment fragment = null;

        switch (position)
        {
            case 0 :
                fragment = new FirstFragment();
                break;
            case 1 :
                fragment = new SecondFragment();
                break;
            case 2 :
                fragment = new ThirdFragment();
                break;
        }
        return fragment;*/
        return null;
    }

   /* @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "all";
        else if (position == 1)
            title = "pdf";
        else if (position == 2)
            title = "txt";
        return title;
    }*/

    @Override
    public int getCount() {
        return 0;
    }
}

