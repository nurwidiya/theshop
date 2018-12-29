package com.example.user.TheShop;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.TheShop.adapter.ProductAdapter;
import com.example.user.TheShop.modal.ProductDomain;

import java.util.ArrayList;

public class ProductActivity extends Activity {

    GridView gridView;
    TextView txtItemCount;
    int itemCount;

    ArrayList<ProductDomain> productList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productList = new ArrayList<>();

        gridView = findViewById(R.id.grid_product);

        int position = getIntent().getIntExtra("position", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.description_layout, null));
        builder.create();

        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();

        switch (position) {
            case 0:
                String[] clothList = {"PINK", "BLACK", "BROWN"};
                int[] clothimageList = {R.drawable.sepatu1, R.drawable.sepatu2, R.drawable.sepatu3};
                String[] clothpriceList = {"200 K", "100 K", "120 K"};


                ProductDomain productDomain;
                for (int i = 0; i < clothList.length; i++) {
                    productDomain = new ProductDomain(clothList[i], clothimageList[i], clothpriceList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));


            case 1:
                String[] electronicList = {"BLACK", "BROWN", "BLUE",};
                int[] electroimageList = {R.drawable.wedges1, R.drawable.wedges4, R.drawable.wedges5};

                String[] electropriceList = {"500 K", "250 K", "100 K"};


                for (int i = 0; i < electronicList.length; i++) {
                    productDomain = new ProductDomain(electronicList[i], electroimageList[i], electropriceList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 2:

                String[] softwareList = {"BLUE", "RED", "WHITE"};
                int[] softwareimageList = {R.drawable.kets1, R.drawable.kets4, R.drawable.kets3};

                String[] softwarepriceList = {"200 K", "250 K", "100 K"};

                for (int i = 0; i < softwareList.length; i++) {
                    productDomain = new ProductDomain(softwareList[i], softwareimageList[i], softwarepriceList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 3:

                String[] cellList = {"BLUE", "PINK", "WHITE"};
                int[] cellimageList = {R.drawable.sneaker, R.drawable.sneaker3, R.drawable.sneaker2};

                String[] cellpriceList = {"200 K", "250 K", "100 K"};

                for (int i = 0; i < cellList.length; i++) {
                    productDomain = new ProductDomain(cellList[i], cellimageList[i], cellpriceList[i]);
                    productList.add(productDomain);
                }
                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 4:

                String[] autoList = {"BROWN", "MAROON", "BLACK"};
                int[] autoimageList = {R.drawable.flat1, R.drawable.flat5, R.drawable.flat4};

                String[] autopriceList = {"200 K", "250 K", "100 K"};

                for (int i = 0; i < autoList.length; i++) {
                    productDomain = new ProductDomain(autoList[i], autoimageList[i], autopriceList[i]);
                    productList.add(productDomain);
                }
                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
                productDetails(productList.get(i).getProductName(), productList.get(i).getProductPrice(), productList.get(i).getImageId(), i);
            }
        });
    }

    public void productDetails(final String productName, String productPrice, int imgId, final int position) {
        final AlertDialog alert;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //Inflate and set the layout for the dialog
        //Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.description_layout, null);
        builder.setView(view);
        alert = builder.create();
        alert.show();

        TextView txtProduct = view.findViewById(R.id.txt_product_name);
        TextView txtPrice = view.findViewById(R.id.txt_price);
        ImageView imageView = view.findViewById(R.id.img_product);
        Button btnCart = view.findViewById(R.id.button);

        txtProduct.setText(productName);
        txtPrice.setText(productPrice);
        imageView.setImageResource(imgId);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //cartArray.add(productList.get(position));
                itemCount++;
                updateHotCount(itemCount);
                alert.dismiss();

                ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu,menu);

        final View notifications = menu.findItem(R.id.cart_item).getActionView();

        txtItemCount = (TextView) notifications.findViewById(R.id.cart_badge);
        updateHotCount(itemCount++);
        txtItemCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHotCount(itemCount++);
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle item selection
        switch (item.getItemId()) {
            case R.id.cart_item:
                //newGame();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }


    public void updateHotCount(final int new_number) {

        itemCount = new_number;
        if (itemCount <0 ) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (itemCount ==0)
                    txtItemCount.setVisibility(View.GONE);
                else {
                    txtItemCount.setVisibility(View.VISIBLE);
                    txtItemCount.setText(Integer.toString(itemCount));
                    // supportInvalidateOptionsMenu();
                }
            }
        });

    }
    }

