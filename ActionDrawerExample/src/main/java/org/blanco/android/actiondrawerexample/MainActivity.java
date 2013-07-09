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

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Alexandro Blanco <ti3r.bubblenet@gmail.com>
 * on 7/8/13.
 * Main activity of the application; Start point for the Launcher.
 */
public class MainActivity extends Activity {

    public static final String TAG = "ActionDrawerExample";
    ActionBarDrawerToggle mDrawerToggle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        prepareActionDrawer();
    }

    private void prepareActionDrawer() {
        getActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_act_drawer);
        ListView list = (ListView) findViewById(R.id.left_drawer);

        ArrayAdapter<String> opts = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_single_choice,
                getResources().getStringArray(R.array.main_act_drawer_opts));
        list.setAdapter(opts);
        list.setOnItemClickListener(
                new DrawerClickListener(getFragmentManager(), drawerLayout, list));

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                android.R.drawable.ic_menu_day, R.string.app_name, R.string.app_name);
        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}