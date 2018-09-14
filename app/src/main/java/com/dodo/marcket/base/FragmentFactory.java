package com.dodo.marcket.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.dodo.marcket.business.homepage.fragment.HomePageFragment;
import com.dodo.marcket.business.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * fragment选择管理
 * xiejingwen
 */
public class FragmentFactory {

    private static List<Fragment> list;
    public static Fragment lastFragment;
    private static FragmentManager manager;

    public enum FragmentStatus {
        NONE,
        GUESS,
        SCORE,
        GOD,
        MIME
    }


    public static void clear() {
        if (list != null)
            list.clear();
        if (manager != null) {
            int count = manager.getBackStackEntryCount();
            while (count >= 0) {
                manager.popBackStackImmediate();
                count--;
            }
        }
        manager = null;
    }

    public static void changeFragment(FragmentManager manager, FragmentStatus status, int id) {
        FragmentFactory.manager = manager;
        FragmentTransaction transaction = manager.beginTransaction();
        if (list == null)
            list = new ArrayList<>();
        Fragment selectFragment = null;
        switch (status) {
            case NONE:
                return;
            case GUESS:
                selectFragment = HomePageFragment.getInstance();
                break;
            case SCORE:
                selectFragment = HomePageFragment.getInstance();
                break;
            case GOD:
                selectFragment = MineFragment.getInstance();
                break;
            case MIME:
                selectFragment = MineFragment.getInstance();
                break;
            default:
                break;
        }

        //change
        if (list.contains(selectFragment)) {
            transaction.hide(lastFragment).show(selectFragment).commitAllowingStateLoss();
        } else {
            if (list.size() == 0)
                transaction.add(id, selectFragment).commitAllowingStateLoss();
            else
                transaction.hide(lastFragment).add(id, selectFragment).commitAllowingStateLoss();
            list.add(selectFragment);
        }
        lastFragment = selectFragment;
    }
}