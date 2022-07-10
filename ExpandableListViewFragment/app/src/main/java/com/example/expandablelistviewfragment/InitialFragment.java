package com.example.expandablelistviewfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InitialFragment extends Fragment {
    private Context context;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;
    private ChangeFragmentListener changeFragmentListener;

    public InitialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        changeFragmentListener = (ChangeFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExpandableListView listView = view.findViewById(R.id.expListView);
        initializeData();
        listAdapter = new ExpandableListAdapter(context, listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);
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

    private final ExpandableListView.OnChildClickListener expandableChildClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
            final String selectedGroup = (String) listAdapter.getGroup(groupPosition);
            String selectedChild = (String) listAdapter.getChild(groupPosition,childPosition);
            switch (selectedGroup){
                case "Asia":
                case "Africa":
                case "Europe":
                    changeFragmentListener.onClick(selectedChild);
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    public interface ChangeFragmentListener {
        void onClick(String childName);
    }
}
