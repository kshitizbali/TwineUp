package com.kshitizbali.twineup.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kshitizbali.twineup.database.AppDatabase;
import com.kshitizbali.twineup.database.SavingsTypeEntry;

import java.util.List;

public class MainSavingsViewModel extends AndroidViewModel {

    private LiveData<List<SavingsTypeEntry>> savingsList;

    public MainSavingsViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        savingsList = database.savingsTypeDao().loadSavings();
    }

    public LiveData<List<SavingsTypeEntry>> getAppointments() {
        return savingsList;
    }
}
