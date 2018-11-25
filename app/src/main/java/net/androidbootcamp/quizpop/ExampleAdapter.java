package net.androidbootcamp.quizpop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public EditText textViewName;
        public TextView textViewScore;



        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.editTextEnterName);
            textViewScore = itemView.findViewById(R.id.textViewFinalScore);

        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ViewGroup.getContext()).inflate(R.layout.activity_high_scores, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
