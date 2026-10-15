package com.example.quizhub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizhub.adapters.CategoryAdapter;
import com.example.quizhub.models.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    
    private RecyclerView categoriesRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        
        // Set up back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        
        // Initialize RecyclerView
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Load and display categories
        loadCategories();
    }
    
    private void loadCategories() {
        categoryList = new ArrayList<>();
        
        // Add categories with their corresponding background colors
        categoryList.add(new Category("General Math", "#FF4500", R.drawable.bg_math));
        categoryList.add(new Category("Science", "#4CAF50", R.drawable.bg_science));
        categoryList.add(new Category("Australian History", "#9C27B0", R.drawable.bg_history));
        categoryList.add(new Category("Capitals of Countries", "#2196F3", R.drawable.bg_capital));
        categoryList.add(new Category("Sports", "#FF9800", R.drawable.bg_sports));
        
        // Set up adapter
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoriesRecyclerView.setAdapter(categoryAdapter);
    }
    
    /**
     * Reads the quiz data JSON file
     */
    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("quiz_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}