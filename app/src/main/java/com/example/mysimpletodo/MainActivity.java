package com.example.mysimpletodo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList items;

 /*    {
       /* try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
        }
  }*/

    Button buttonAdd;
    EditText editText;
    RecyclerView rvitems;
    itemsAdapter ItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        editText = findViewById(R.id.editText);
        rvitems = findViewById(R.id.rvitems);


        loadItems();


        itemsAdapter.onLongClickListener onLongClickListener = new itemsAdapter.onLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                items.remove(position);
                ItemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item removed", Toast.LENGTH_SHORT).show();
                saveItems();

            }
        };
        ItemsAdapter = new itemsAdapter(items, onLongClickListener);
        rvitems.setAdapter(ItemsAdapter);
        rvitems.setLayoutManager (new LinearLayoutManager(this));

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = editText.getText().toString();
                items.add(todoItem);
                ItemsAdapter.notifyItemInserted(items.size()-1);
                editText.setText("");
                Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });

    }
    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }
    private void loadItems(){
        try {
            items = new ArrayList(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading items", e);
            items = new ArrayList();
        }


    }
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing items", e);
        }
    }
    }

