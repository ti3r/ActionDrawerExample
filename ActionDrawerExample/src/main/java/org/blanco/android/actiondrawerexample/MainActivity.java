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
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

/**
 * Created by Alexandro Blanco <ti3r.bubblenet@gmail.com>
 * on 7/8/13.
 * Main activity of the application; Start point for the Launcher.
 */
public class MainActivity extends Activity implements AdListener {

    public static final String TAG = "ActionDrawerExample";
    private static final String MY_AD_UNIT_ID = "a151dc6cc523440";
    private static final String MY_INTERSTITIAL_UNIT_ID = "a151dc6cc523440";
    ActionBarDrawerToggle mDrawerToggle;
    AdView adView;
    DrawerLayout drawerLayout;
    private InterstitialAd interstitial;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        prepareActionDrawer();
        prepareAdd();
    }

    private void prepareAdd() {
        // Create the interstitial
        interstitial = new InterstitialAd(this, MY_INTERSTITIAL_UNIT_ID);

        // Create ad request
        AdRequest adRequest = new AdRequest();

        // Begin loading your interstitial
        interstitial.loadAd(adRequest);

        // Set Ad Listener to use the callbacks below
        interstitial.setAdListener(this);
    }

    private void prepareActionDrawer() {
        getActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_act_drawer);
        ListView list = (ListView) findViewById(R.id.left_drawer);

        ArrayAdapter<String> opts = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_single_choice,
                getResources().getStringArray(R.array.main_act_drawer_opts));
        list.setAdapter(opts);
        list.setOnItemClickListener(
                new DrawerClickListener(getFragmentManager(), drawerLayout, list));

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                android.R.drawable.ic_menu_day, R.string.select_fragment, R.string.app_name);
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

    @Override
    public void onReceiveAd(Ad ad) {
        Log.d(TAG,"Received Ad");
        if (ad == interstitial){
            interstitial.show();
        }
    }

    @Override
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {

    }

    @Override
    public void onPresentScreen(Ad ad) {

    }

    @Override
    public void onDismissScreen(Ad ad) {

    }

    @Override
    public void onLeaveApplication(Ad ad) {

    }
}