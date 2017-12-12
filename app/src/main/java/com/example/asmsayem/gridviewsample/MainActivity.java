package com.example.asmsayem.gridviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the widgets reference from XML layout
        GridView gv = findViewById(R.id.gv);
        Button btn = findViewById(R.id.btn);

        // Initializing a new String Array
        String[] plants = new String[]{
                "Catalina ironwood",
                "Cabinet cherry",
                "Pale corydalis",
                "Pink corydalis",
                "Belle Isle cress",
                "Land cress",
                "Coast polypody",
                "Water fern"
        };

        // Populate a List from Array elements
        final List<String> plantsList = new ArrayList<String>(Arrays.asList(plants));

        // Create a new ArrayAdapter
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, plantsList);

        // Data bind GridView with ArrayAdapter (String Array elements)
        gv.setAdapter(gridViewArrayAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                pos = position;

                String secondItemText = plantsList.get(pos);

                Log.d("+++Position", "onItemClick: Position got: " + pos);


                // Remove/delete second item from ArrayAdapter
                // Delete index position 1 item from ArrayAdapter
                // ArrayAdapter is zero based index
                plantsList.remove(pos);

                // Update the GridView
                gridViewArrayAdapter.notifyDataSetChanged();

                // Confirm the deletion
                Toast.makeText(getApplicationContext(),
                        "Removed : " + secondItemText, Toast.LENGTH_SHORT).show();
            }
        });
/*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the second item from ArrayAdapter
                String secondItemText = plantsList.get(1);

                // Remove/delete second item from ArrayAdapter
                // Delete index position 1 item from ArrayAdapter
                // ArrayAdapter is zero based index
                plantsList.remove(1);

                // Update the GridView
                gridViewArrayAdapter.notifyDataSetChanged();

                // Confirm the deletion
                Toast.makeText(getApplicationContext(),
                        "Removed : " + secondItemText, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
