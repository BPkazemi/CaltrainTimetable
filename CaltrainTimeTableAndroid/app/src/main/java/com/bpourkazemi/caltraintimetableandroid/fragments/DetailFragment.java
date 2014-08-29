package com.bpourkazemi.caltraintimetableandroid.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bpourkazemi.caltraintimetableandroid.R;
import com.bpourkazemi.caltraintimetableandroid.adapters.TextPagerAdapter;
import com.bpourkazemi.caltraintimetableandroid.utils.CaltrainDataHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner caltrainStationsSpinner;
    private ViewPager northboundViewPager, southboundViewPager;
    private TextPagerAdapter northboundTimesAdapter, southboundTimesAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        caltrainStationsSpinner = (Spinner) rootView.findViewById(R.id.caltrain_stations);

        // TODO: Replace this with an array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.caltrain_stations, R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        caltrainStationsSpinner.setAdapter(adapter);
        // Register changes from the spinner
        caltrainStationsSpinner.setOnItemSelectedListener(this);

        // TODO: Swap the spinner with a selectable listview + sexy animation

        // Setup the viewpagers
        southboundViewPager = (ViewPager) rootView.findViewById(R.id.southbound_pager);
        northboundViewPager = (ViewPager) rootView.findViewById(R.id.northbound_pager);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: These will need to change with the spinner selection
        ArrayList<String> southboundTimes =
                new ArrayList<String>(
                        Arrays.asList("12:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00"));

        ArrayList<String> northboundTimes =
                new ArrayList<String>(
                        Arrays.asList("12:30", "1:30", "2:30", "3:30", "4:30", "5:30", "6:30", "7:30", "8:30"));

        southboundTimesAdapter = new TextPagerAdapter(southboundTimes, getActivity());
        northboundTimesAdapter= new TextPagerAdapter(northboundTimes, getActivity());
        northboundViewPager.setAdapter(northboundTimesAdapter);
        southboundViewPager.setAdapter(southboundTimesAdapter);

        // TODO: Place in earlier part of the lifecycle so it gets generated quickly
        CaltrainDataHandler dataHandler = new CaltrainDataHandler();
//        CaltrainDataHandler dataHandler = new CaltrainDataHandler(getActivity());
//        dataHandler.init();
//        StopInfo[] stopInformation = dataHandler.getStopInformation();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selectedStation = parent.getItemAtPosition(pos).toString();
        Toast.makeText(getActivity(), "Selected: " + selectedStation, Toast.LENGTH_LONG).show();

        // TODO: Update southboundTimes and northboundTimes selection
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}
