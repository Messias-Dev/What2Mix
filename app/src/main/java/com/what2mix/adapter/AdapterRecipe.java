package com.what2mix.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.what2mix.R;
import com.what2mix.domain.Recipe;

import java.util.List;

public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.MyViewHolder> {

    private List<Recipe> recipes;

    public AdapterRecipe(List<Recipe> list) {
        this.recipes = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recipe, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        holder.title.setText(recipe.getTitle());
        holder.date.setText(recipe.getCreatedAt());
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
