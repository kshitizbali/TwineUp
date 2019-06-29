package com.kshitizbali.twineup.ui.main;


import android.content.Context;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kshitizbali.twineup.R;
import com.kshitizbali.twineup.adapter_main_edit.AdapterFragmentEdit;
import com.kshitizbali.twineup.api.RetrofitClientInstance;
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
public class EditFragment extends Fragment implements AdapterFragmentEdit.SavingsActivateClickListerner, AdapterFragmentEdit.SavingsStatusClickListener, AdapterFragmentEdit.ListItemClickListener {


    public EditFragment() {
        // Required empty public constructor
    }

    private AdapterFragmentEdit adapterFragmentEdit;
    private String currentDate;
    private RecyclerView rv_savings;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.fragment_edit, container, false);

        instantiateViews(rootView);
        if (Utils.isInternetAvailable(getActivity())) {

            currentDate = Utils.getTomorrowsDate();
            OnLaunchTask(currentDate);
        }


        setUpAppointmentsModel();
        return rootView;
    }

    /***Instantiate all the layout views***/
    private void instantiateViews(View view) {
        rv_savings = (RecyclerView) view.findViewById(R.id.rvSavings);
        adapterFragmentEdit = new AdapterFragmentEdit(getActivity(), this, this, this);
        adapterFragmentEdit.setHasStableIds(true);
        /*rv_appointments.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));*/
        rv_savings.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL);
        rv_savings.addItemDecoration(decoration);
        rv_savings.setAdapter(adapterFragmentEdit);
        animateLayoutSlideUp(R.id.rvSavings, 300);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /***Fetches Data from API and saves it to Room Database***/
    private void OnLaunchTask(final String date) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                RetrofitClientInstance.getSavingsTypes(getActivity(), date);
            }
        });

    }

    /***Fetches data from Room Database***/
    private void setUpAppointmentsModel() {
        MainSavingsViewModel mainSavingsViewModel = ViewModelProviders.of(this).get(MainSavingsViewModel.class);
        mainSavingsViewModel.getAppointments().removeObservers(this);
        mainSavingsViewModel.getAppointments().observe(this, new Observer<List<SavingsTypeEntry>>() {
            @Override
            public void onChanged(List<SavingsTypeEntry> appointmentsEntries) {
                adapterFragmentEdit.setmSavingsList(appointmentsEntries);
            }
        });
    }

    /***Saving status TextView (Pause/Saving) Click Listener***/
    @Override
    public void onSavingsStatusClickListener(String name) {

    }

    /***Saving Activate TextView (Activate/Deactivate) Click Listener***/
    @Override
    public void onSavingsActivateClickListerner(String name) {

    }

    /***Tries to animate list when UI accessed**/
    private void animateLayoutSlideUp(int resource, int slideDuration) {
        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(resource);
        slide.setInterpolator(AnimationUtils.loadInterpolator(getActivity(), android.R.interpolator
                .linear_out_slow_in));
        slide.setDuration(slideDuration);
        Objects.requireNonNull(getActivity()).getWindow().setEnterTransition(slide);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setUpAppointmentsModel();
    }


    /*** Custom Dialog Which shows  details of recyclerview list onClicks***/
    private void showCustomDialog(View view, String savingsFor) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_confirm, viewGroup, false);

        TextView tvSavingsFor = (TextView) dialogView.findViewById(R.id.tvSavingsFor);
        Button buttonOk = (Button) dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = (Button) dialogView.findViewById(R.id.buttonCancel);

        tvSavingsFor.setText(String.format("For: %s", savingsFor));

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.dismiss();

                    }
                });
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    /***Recycler item click listener,shows the custom dialog***/
    @Override
    public void onListItemClickListener(String name) {
        showCustomDialog(rootView, name);
    }
}
