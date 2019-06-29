package com.kshitizbali.twineup.adapter_main_edit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kshitizbali.twineup.R;
import com.kshitizbali.twineup.database.SavingsTypeEntry;
import com.kshitizbali.twineup.utilities.Utils;

import java.util.List;

public class AdapterFragmentEdit extends RecyclerView.Adapter<AdapterFragmentEdit.ViewHolder> {

    private Context mContext;
    private List<SavingsTypeEntry> mSavingsList;
    private SavingsStatusClickListener savingsStatusClickListener;
    private SavingsActivateClickListerner savingsActivateClickListerner;
    private ListItemClickListener listItemClickListener;


    public AdapterFragmentEdit(Context context, SavingsStatusClickListener savingsStatusClickListener, SavingsActivateClickListerner savingsActivateClickListerner, ListItemClickListener listItemClickListener) {
        this.mContext = context;
        this.savingsActivateClickListerner = savingsActivateClickListerner;
        this.savingsStatusClickListener = savingsStatusClickListener;
        this.listItemClickListener = listItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_savings_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvSavingsName.setText(getmSavingsList().get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (getmSavingsList() != null) {
            return getmSavingsList().size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvSavingsName, tvStatus, tvPauseResume;
        public CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSavingsName = (TextView) itemView.findViewById(R.id.tvSavingsName);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            tvPauseResume = (TextView) itemView.findViewById(R.id.tvPauseResume);
            cv = (CardView) itemView.findViewById(R.id.cv);

            tvSavingsName.setOnClickListener(this);
            tvStatus.setOnClickListener(this);
            tvPauseResume.setOnClickListener(this);
            cv.setOnClickListener(this);
            /*Utils.setTextStyle(tvName);*/
            Utils.setTextViewUnderline(tvStatus, mContext.getResources().getString(R.string.demo_activate));
        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.tvStatus) {
                savingsStatusClickListener.onSavingsStatusClickListener(getmSavingsList().get(getAdapterPosition()).getName());
                if (tvStatus.getText().toString().equalsIgnoreCase(mContext.getString(R.string.demo_activate))) {
                    //tvStatus.setText(mContext.getResources().getString(R.string.deactivate));
                    Utils.setTextViewUnderline(tvStatus, mContext.getResources().getString(R.string.deactivate));
                    tvPauseResume.setText(mContext.getResources().getString(R.string.saving));
                    tvPauseResume.setBackground(mContext.getDrawable(R.drawable.button_background_green));
                } else {
                    /*tvStatus.setText(mContext.getResources().getString(R.string.demo_activate));*/
                    Utils.setTextViewUnderline(tvStatus, mContext.getResources().getString(R.string.demo_activate));
                    tvPauseResume.setText(mContext.getResources().getString(R.string.demo_paused));
                    tvPauseResume.setBackground(mContext.getDrawable(R.drawable.button_background));
                }

            } else if ((view.getId() == R.id.tvPauseResume)) {
                savingsActivateClickListerner.onSavingsActivateClickListerner(getmSavingsList().get(getAdapterPosition()).getName());
            } else if (view.getId() == R.id.cv) {
                listItemClickListener.onListItemClickListener(getmSavingsList().get(getAdapterPosition()).getName());
            }

        }


    }

    public List<SavingsTypeEntry> getmSavingsList() {
        return mSavingsList;
    }

    public void setmSavingsList(List<SavingsTypeEntry> mSavingsList) {
        this.mSavingsList = mSavingsList;
        notifyDataSetChanged();
    }

    public interface ListItemClickListener {
        void onListItemClickListener(String name);
    }

    public interface SavingsStatusClickListener {
        void onSavingsStatusClickListener(String name);
    }

    public interface SavingsActivateClickListerner {
        void onSavingsActivateClickListerner(String name);
    }
}
