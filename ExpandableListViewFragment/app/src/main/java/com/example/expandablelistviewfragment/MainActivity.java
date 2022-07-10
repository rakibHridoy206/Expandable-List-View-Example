package com.example.expandablelistviewfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements
        InitialFragment.ChangeFragmentListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InitialFragment initialFragment = new InitialFragment();
        fragmentTransaction.add(R.id.fragmentContainer, initialFragment).commit();
    }

    @Override
    public void onClick(String childName) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        DataFragment dataFragment = new DataFragment();
        Bundle data = new Bundle();
        data.putString("DATA_PASS", childName);
        dataFragment.setArguments(data);
        ft.replace(R.id.fragmentContainer, dataFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
}
