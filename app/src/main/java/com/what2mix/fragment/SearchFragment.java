package com.what2mix.fragment;

        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import com.what2mix.R;

public class SearchFragment extends Fragment {
private ImageView btAddIngredients;
private LinearLayout llIngredientsView;
private EditText etIngredients;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btAddIngredients = view.findViewById(R.id.btAddIngredients);
        llIngredientsView = view.findViewById(R.id.IngredientsLinearLayout);
        etIngredients = view.findViewById(R.id.etIngredients);

        btAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addIngredients();
            }
        });

    }

    private void addIngredients(){
        final TextView textView = new TextView(getContext());
        textView.setText(etIngredients.getText().toString());
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.ingredients_background);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.GONE);
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        textView.setLayoutParams(params);

        llIngredientsView.addView(textView);

    }



}
