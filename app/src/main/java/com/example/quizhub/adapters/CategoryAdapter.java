package com.example.quizhub.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizhub.R;
import com.example.quizhub.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    
    private Context context;
    private List<Category> categories;
    
    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }
    
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        
        // Set category name
        holder.categoryTitle.setText(category.getName());
        
        // Set background image if available
        if (category.getBackgroundResource() != 0) {
            holder.categoryBackground.setImageResource(category.getBackgroundResource());
        }
        
        // Create gradient overlay
        int color = Color.parseColor(category.getColorHex());
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[] {color, Color.TRANSPARENT}
        );
        gradientDrawable.setAlpha(200); // Semi-transparent
        holder.gradientOverlay.setBackground(gradientDrawable);
        
        // Set click listener (to be implemented later)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to quiz preferences for this category
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return categories.size();
    }
    
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle;
        ImageView categoryBackground;
        View gradientOverlay;
        
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            categoryBackground = itemView.findViewById(R.id.categoryBackground);
            gradientOverlay = itemView.findViewById(R.id.gradientOverlay);
        }
    }
}