package com.kshitizbali.twineup.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavingsTypeDao {

    @Query("SELECT * FROM savings_type")
    LiveData<List<SavingsTypeEntry>> loadSavings();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSavingsType(SavingsTypeEntry... savingsTypeEntries);

   /* @Query("SELECT * FROM SavingsTypeEntry WHERE id = (:id) ORDER BY datetime(appointment_time)")
    LiveData<List<SavingsTypeEntry>>  getDoctorSpecificAppointments(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAppointments(SavingsTypeEntry... appointmentsEntries);

    @Query("UPDATE SavingsTypeEntry SET is_booked = (:book) WHERE id = (:id)")
    void bookAppointment(int id, String book);

    @Query("SELECT COUNT(id) FROM SavingsTypeEntry")
    int getNumberOfRows();*/

    @Query("DELETE FROM savings_type")
    void delete();
}
