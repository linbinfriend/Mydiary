package com.rex.mydiary;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends Activity {

    private ListView mylistview;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mylistview = new ListView(this);
		mylistview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));
		
		setContentView(mylistview);
		//setContentView(R.layout.activity_main);
		
		 
		 //update by linbin before 20130309
		//mDemoCollectionPagerAdapter =
        //        new DemoCollectionPagerAdapter(getSupportFragmentManager());
        //mViewPager = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setAdapter(mDemoCollectionPagerAdapter);

		//setContentView(R.layout.bottombtn);
		
		
		
		//add by linbin 20130306
		/*final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	    actionBar.setListNavigationCallbacks(
	            // Specify a SpinnerAdapter to populate the dropdown list.
	            new ArrayAdapter(
	                    actionBar.getThemedContext(),
	                    android.R.layout.simple_list_item_1,
	                    android.R.id.text1,
	                    new String[]{ "Tab 1", "Tab 2", "Tab 3" }),

	            // Provide a listener to be called when an item is selected.
	            new ActionBar.OnNavigationListener() {
	                public boolean onNavigationItemSelected(
	                        int position, long id) {
	                    // Take action here, e.g. switching to the
	                    // corresponding fragment.
	                    return true;
	                }
	            });

		
		*/
		
		//actionBar.setHomeButtonEnabled(false);
		
		// Create a tab listener that is called when the user changes tabs.
	   /* ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab,
	                FragmentTransaction ft) { }

	        public void onTabUnselected(ActionBar.Tab tab,
	                FragmentTransaction ft) { }

	        public void onTabReselected(ActionBar.Tab tab,
	                FragmentTransaction ft) { }
	    };
*/
	    
		
		// Specify that tabs should be displayed in the action bar.
	    //ctionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    
	    // Add 3 tabs.
	   /* for (int i = 0; i < 3; i++) {
	        actionBar.addTab(
	                actionBar.newTab()
		                .setText("Tab " + (i + 1))
	                    .setTabListener(tabListener));

	    }
*/
		
	}
	
	//added by linbin on 20130309
	private List<String>  getData(){
		
		List<String> data = new ArrayList<String>();
		data.add("第一篇日记");
		data.add("第二篇日记");
		data.add("第三篇日记");
		data.add("第四篇日记");
		return data;
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}



