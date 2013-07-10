/**
 * ActionDrawerExample is a small application to demonstrate how to use
 * the action drawer widget that Android Platform provides to Developers
 * to display Menus.
 * Copyright (C) 2013 Alexandro Blanco <ti3r.bubblenet@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.blanco.android.actiondrawerexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.blanco.tests.viewpager.PlainColorFragment;

import java.util.HashMap;

import static org.blanco.android.actiondrawerexample.MainActivity.TAG;

/**
 * Created by Alexandro Blanco <ti3r.bubblenet@gmail.com> on 7/9/13.
 */
public class DrawerClickListener implements AdapterView.OnItemClickListener {

    String[] mColors;
    DrawerLayout mDrawerLayout;
    ListView mDrawer;
    FragmentManager mFragmentManager;
    HashMap<String,Bundle> mFragmentSavedInstance;

    public DrawerClickListener(FragmentManager fragmentManager,
                               DrawerLayout mDrawerLayout, ListView mDrawer) {
        this.mFragmentManager = fragmentManager;
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawer = mDrawer;
        mColors = mDrawer.getContext().getResources().getStringArray(R.array.colors);
        mFragmentSavedInstance = new HashMap<String, Bundle>();
        Log.d(TAG,"Listener Initialized");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String color = (mColors != null && i < mColors.length)?
                mColors[i] : "#FFFFFFFF"; //White if not found
        mDrawerLayout.closeDrawer(mDrawer);

        Fragment fragment = null;
        if (!mFragmentSavedInstance.containsKey(color)){
            mFragmentSavedInstance.put(color,new Bundle());
            //create a new fragment for this new selected color
            fragment = PlainColorFragment.newInstance(Color.parseColor(color));
        }else{
            fragment =
               mFragmentManager.getFragment(mFragmentSavedInstance.get(color),color);
        }
        //mFragmentManager.sa
        mFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment,color)
                .commit();
    }
}
