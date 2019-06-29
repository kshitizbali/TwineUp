package com.kshitizbali.twineup.ui.main;


import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kshitizbali.twineup.R;
import com.kshitizbali.twineup.adapter_main_edit.AdapterFragmentActivity;
import com.kshitizbali.twineup.adapter_main_edit.AdapterFragmentEdit;
import com.kshitizbali.twineup.database.AppDatabase;
import com.kshitizbali.twineup.database.AppExecutors;
import com.kshitizbali.twineup.database.SavingsTypeEntry;
import com.kshitizbali.twineup.utilities.Utils;
import com.kshitizbali.twineup.viewmodel.MainSavingsViewModel;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitySavingsFragment extends Fragment {

    private AdapterFragmentActivity adapterFragmentActivity;



    public ActivitySavingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_savings, container, false);

        instantiateViews(rootView);
        setUpAppointmentsModel();
        return rootView;
    }

    /***Instantiate all the layout views***/
    private void instantiateViews(View view) {
        RecyclerView rv_activity = (RecyclerView) view.findViewById(R.id.rvDeposits);
        adapterFragmentActivity = new AdapterFragmentActivity(getActivity());
        adapterFragmentActivity.setHasStableIds(true);
        /*rv_appointments.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));*/
        rv_activity.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL);
        rv_activity.addItemDecoration(decoration);
        rv_activity.setAdapter(adapterFragmentActivity);

    }

    /***Creates and populates a custom demo list using room disk background operation***/
    private void setUpAppointmentsModel() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                adapterFragmentActivity.setActivityList(Utils.getActivityList());
            }
        });
    }


}
