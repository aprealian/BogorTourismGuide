package com.teknokrait.bogortourismguide.home;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Color;
import com.teknokrait.bogortourismguide.data.Tourism;
import com.teknokrait.bogortourismguide.dev.RecyclerViewOnItemClickListener;
import com.teknokrait.bogortourismguide.test.MaterialPaletteAdapter;

import java.util.List;

/**
 * Created by sirius on 3/12/2017.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private List<Tourism> data;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public PopularAdapter(@NonNull List<Tourism> data,
                                  @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_popular, parent, false);
        return new PopularAdapter.PopularViewHolder(row);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.PopularViewHolder holder, int position) {
        Tourism popular = data.get(position);
        holder.getNameTextView().setText(popular.getName());
        holder.getDescriptionTextView().setText(popular.getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    class PopularViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private TextView nameTextView;
        private TextView descriptionTextView;


        public PopularViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            itemView.setOnClickListener(this);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getDescriptionTextView() {
            return descriptionTextView;
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }

}
