package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);

        RecyclerView recyclerView = findViewById(R.id.sandwiches_recyclerview);
        SandwichAdapter sandwichAdapter = new SandwichAdapter(this,sandwiches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.setAdapter(sandwichAdapter);
    }


}
