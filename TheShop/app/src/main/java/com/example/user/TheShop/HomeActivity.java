package com.example.user.TheShop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.TheShop.adapter.CategoryAdapter;

public class HomeActivity extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.category_list);

        String [] categoryList = {"HEELS","WEDGES","KETS","SNEAKER","FLATSHOES"};
        int [] imageList = {R.drawable.spatu3,R.drawable.wedges2,R.drawable.kets3,R.drawable.sneaker2,R.drawable.flat2};

        listView.setAdapter(new CategoryAdapter(categoryList,imageList,getApplicationContext()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Log.d("position---", String.valueOf(position));
                Intent mainIntent = new Intent(HomeActivity.this,ProductActivity.class);
                mainIntent.putExtra("position",position);
                startActivity(mainIntent);


            }
        });

    }
}
