package com.kshitizbali.twineup.database;

import android.content.Context;

import com.kshitizbali.twineup.model.RetroGetSavings;
import com.kshitizbali.twineup.utilities.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;

public class DbNetworkOperations {

    public static void insertAllAppointments(Context context, Response<RetroGetSavings> response) {

        final AppDatabase appDatabase = AppDatabase.getInstance(context);

        List<RetroGetSavings.DoctorsBean> fetchedDocs = Objects.requireNonNull(response.body()).getDoctors();

        for (int i = 0; i < fetchedDocs.size(); i++) {

           final SavingsTypeEntry savingsTypeEntry = new SavingsTypeEntry(fetchedDocs.get(i).getDoctor_id(),
                    fetchedDocs.get(i).getFirst_name(),
                    fetchedDocs.get(i).getAverage().getRolling_average(),
                    fetchedDocs.get(i).getImage_url(),
                    fetchedDocs.get(i).getYears_practiced(),
                    fetchedDocs.get(i).getResidency_program());

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    appDatabase.savingsTypeDao().insertSavingsType(savingsTypeEntry);
                }
            });

        }

    }


}
