package com.kshitizbali.twineup.adapter_main_edit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kshitizbali.twineup.R;
import com.kshitizbali.twineup.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdapterFragmentActivity extends RecyclerView.Adapter<AdapterFragmentActivity.ViewHolder> {

    private Context mContext;

    List<String> activityList = new ArrayList<>();

    public AdapterFragmentActivity(Context context) {
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_deposits_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvDepositsName.setText(getActivityList().get(position));

    }

    @Override
    public int getItemCount() {
        if (getActivityList() != null) {
            return  getActivityList().size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvDepositsName, tvPauseResume;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDepositsName = (TextView) itemView.findViewById(R.id.tvDepositsName);
            tvPauseResume = (TextView) itemView.findViewById(R.id.tvPauseResume);

            //tvDepositsName.setOnClickListener(this);
            //tvPauseResume.setOnClickListener(this);
            // tvPauseResume.setOnClickListener(this);
            /*Utils.setTextStyle(tvName);*/
            // Utils.setTextViewUnderline(tvStatus,mContext.getResources().getString(R.string.demo_activate));
        }

        @Override
        public void onClick(View view) {

        }


    }

    public List<String> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<String> activityList) {
        this.activityList = activityList;
    }
}
