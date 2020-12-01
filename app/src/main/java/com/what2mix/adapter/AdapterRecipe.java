package com.what2mix.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.what2mix.R;
import com.what2mix.activity.RecipeActivity;
import com.what2mix.domain.Recipe;
import com.what2mix.fragment.SearchFragment;

import java.util.List;

public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.MyViewHolder> {

    private List<Recipe> recipes;
    private Context context;

    public AdapterRecipe(List<Recipe> list, Context context) {
        this.recipes = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recipe, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Recipe recipe = recipes.get(position);

        holder.title.setText(recipe.getTitle());
        holder.date.setText(recipe.getCreatedAt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeActivity.class);
                Recipe recipe = recipes.get(position);
                intent.putExtra("recipe", recipe);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;

        public MyViewHolder (View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvRecipeAdapterTitle);
            date = itemView.findViewById(R.id.tvRecipeAdapterDate);
        }
    }
}
