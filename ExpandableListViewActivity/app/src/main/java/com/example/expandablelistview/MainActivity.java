package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListAdapter listAdapter;
    private ExpandableListView listView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.expListView);
        initializeData();
        initializeAdapter();
        listView.setOnChildClickListener(expandableChildClickListener);
    }

    private void initializeData(){
        //Adding Header List
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Asia");
        listDataHeader.add("Europe");
        listDataHeader.add("Africa");

        //Adding Child into list
        List<String> asiaList = new ArrayList<>();
        asiaList.add("Bangladesh");
        asiaList.add("Maldives");
        asiaList.add("Indonesia");
        asiaList.add("Nepal");

        List<String> europeList = new ArrayList<>();
        europeList.add("Switzerland");
        europeList.add("Norway");
        europeList.add("Finland");

        List<String> africaList = new ArrayList<>();
        africaList.add("Egypt");
        africaList.add("Nigeria");

        listHashMap.put(listDataHeader.get(0), asiaList);
        listHashMap.put(listDataHeader.get(1),europeList);
        listHashMap.put(listDataHeader.get(2),africaList);
    }

    private void initializeAdapter() {
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);
    }

    private final ExpandableListView.OnChildClickListener expandableChildClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
            final String selectedGroup = (String) listAdapter.getGroup(groupPosition);
            String selectedChild = (String) listAdapter.getChild(groupPosition,childPosition);
            Intent intent;
            switch (selectedGroup){
                case "Asia":
                    intent = new Intent(MainActivity.this, AsiaActivity.class);
                    intent.putExtra(Constants.ASIA_ACTIVITY_DATA_KEY, selectedChild);
                    startActivity(intent);
                    break;
                case "Europe":
                    intent = new Intent(MainActivity.this, EuropeActivity.class);
                    intent.putExtra(Constants.EUROPE_ACTIVITY_DATA_KEY, selectedChild);
                    startActivity(intent);
                    break;
                case "Africa":
                    intent = new Intent(MainActivity.this, AfricaActivity.class);
                    intent.putExtra(Constants.AFRICA_ACTIVITY_DATA_KEY, selectedChild);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            return true;
        }
    };
}
