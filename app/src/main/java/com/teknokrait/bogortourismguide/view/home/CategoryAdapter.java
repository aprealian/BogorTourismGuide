package com.teknokrait.bogortourismguide.view.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Category;
import com.teknokrait.bogortourismguide.view.dev.RecyclerViewOnItemClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirius on 3/12/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.PopularViewHolder> {

    private Context context;
    private List<Category> categories;
    private int selectedPosition;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public CategoryAdapter(Context context, List<Category> categories, @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.categories = categories;
        this.context = context;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public CategoryAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        return new CategoryAdapter.PopularViewHolder(row);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.PopularViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.getNamaTextView().setText(category.getTitle());
        int id = context.getResources().getIdentifier(category.getImage(), "drawable", context.getPackageName());
        holder.categoryImageView.setImageResource(id);
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public CategoryAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getItemCount() {
        if (this.categories == null) {
            this.categories= new ArrayList<>();
        }
        return categories.size();
    }

    public Category getItem(int i) {
        setSelectedPosition(i);
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addCategory(Category category) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }
        this.categories.add(category);
    }

    public void addCategories(List<Category> categories) {
        if (this.categories == null) {
            this.categories= new ArrayList<>();
        }
        this.categories.addAll(categories);
    }

    public void clear() {
        this.categories = new ArrayList<>();
    }

    class PopularViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private TextView titleTextView;
        private ImageView categoryImageView;


        public PopularViewHolder(View itemView) {
            super(itemView);
            categoryImageView = (ImageView) itemView.findViewById(R.id.categoryImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(this);
        }

        public TextView getNamaTextView() {
            return titleTextView;
        }
        public ImageView getWisataImageView() {
            return categoryImageView;
        }


        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }

    }


}
