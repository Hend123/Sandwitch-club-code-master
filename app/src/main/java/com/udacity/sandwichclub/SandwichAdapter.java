package com.udacity.sandwichclub;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udacity.sandwichclub.model.Sandwich;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichHolder> {
    Context context;
    String[] sandwiches;

    public SandwichAdapter(Context context, String[] sandwiches) {
        this.context = context;
        this.sandwiches = sandwiches;
    }

    @Override
    public SandwichAdapter.SandwichHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new SandwichHolder(row);
    }

    @Override
    public void onBindViewHolder(SandwichAdapter.SandwichHolder holder,  int position) {
        final int index = position;
       String sandwich = sandwiches[position];
       holder.sandwichName.setText(sandwich);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetailActivity(index);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sandwiches.length;
    }

    public class SandwichHolder extends RecyclerView.ViewHolder {
        TextView sandwichName;
        RelativeLayout root;
        public SandwichHolder(View itemView) {
            super(itemView);
            sandwichName = itemView.findViewById(R.id.text1);
            root = itemView.findViewById(R.id.root);

        }
    }
    private void launchDetailActivity(int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        context.startActivity(intent);
    }
}
