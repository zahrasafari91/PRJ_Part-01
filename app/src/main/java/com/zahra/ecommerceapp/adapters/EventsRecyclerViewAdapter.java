package com.zahra.ecommerceapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zahra.ecommerceapp.R;
import com.zahra.ecommerceapp.models.EventModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>{


    private Context context;



    private SQLiteDatabase prodDB;

    Intent intent ;

    private LayoutInflater mInflater;
    Bitmap image;


    LinkedList<EventModel> eventModelArrayList;

    public EventsRecyclerViewAdapter(Context context, LinkedList<EventModel> prodList) {

        this.context = context;
        mInflater= LayoutInflater.from(context);

        eventModelArrayList = prodList;
//        intent = new Intent(context, Cart.class);
    }
    public  class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection;
            InputStream is;

            try {
                url=new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                is=urlConnection.getInputStream();
                image= BitmapFactory.decodeStream(is);
                return image;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View cardView= mInflater.inflate(R.layout.product_card,parent,false);
        return new ViewHolder(cardView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {

            EventModel product = eventModelArrayList.get(position);

            Picasso.get().load(product.getImgUrl()).into(holder.imgView);

            holder.priceTxtView.setText(product.getPrice());
            holder.prodNameTxtView.setText(product.getName());
            holder.brandNameTxtView.setText(product.getBrand());
            holder.prodDescriptionTxtView.setText(product.getDescription());
            holder.idTxtView.setText(String.valueOf(product.getId()));

            String imageUrl = eventModelArrayList.get(position).getImgUrl();





//            holder.imgView.setImageResource(R.drawable.ic_launcher_background);

        }
        catch(Exception e)
        {
            Log.d("error", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return eventModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView titleTxtView, priceTxtView;
        public final ImageView imgView;
        public final Button buyNowBtn, addToCartBtn;

        TextView prodNameTxtView, brandNameTxtView, idTxtView, prodDescriptionTxtView;

        int position;


        final EventsRecyclerViewAdapter mAdapter;

        public ViewHolder(View itemView, EventsRecyclerViewAdapter adapter) {
            super(itemView);


            imgView = itemView.findViewById(R.id.productImgView);
            titleTxtView = itemView.findViewById(R.id.productName);
            priceTxtView= itemView.findViewById(R.id.priceTxtView);
            prodNameTxtView = itemView.findViewById(R.id.productName);
            brandNameTxtView = itemView.findViewById(R.id.brandValTxtView);
            idTxtView = itemView.findViewById(R.id.idTxtView);
            prodDescriptionTxtView = itemView.findViewById(R.id.prodDescTextView);


            buyNowBtn=itemView.findViewById(R.id.buyNowBtn);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
            this.mAdapter=adapter;

            addToCartBtn.setOnClickListener(v -> {

//                    int pos = getLayoutPosition();
//
//                    EventModel selectedModel = eventModelArrayList.get(pos);
//                        prodDB=MainActivity.context.openOrCreateDatabase("WordDatabase",MainActivity.context.MODE_PRIVATE,null);
//                        prodDB.execSQL("CREATE TABLE IF NOT EXISTS cart(id INTEGER PRIMARY KEY,prodId VARCHAR, title VARCHAR, imgUrl VARCHAR, price VARCHAR, count INTEGER  )");
//
//                        String id = String.valueOf(selectedModel.getId());
//                        Log.d("id",id);
//                        Cursor query=prodDB.rawQuery("SELECT * FROM cart WHERE prodId=?",new String[] {id});
//                        prodDB.beginTransaction();
//                        String prodId= "";
//                        int prodIdIndex=query.getColumnIndex("prodId");
//                        int countIndex=query.getColumnIndex("count");
//
//                        query.moveToFirst();
//
//                        int cnt=0;
//
//                        while(query.moveToNext())
//                        {
//                            cnt++;
//                            query.moveToNext();
//                        }
//
//
//                        query.getCount();
//
////                           int newCount = query.getInt(countIndex)
////                            query.moveToNext();
//
//                        if(cnt ==0)
//                        {
//                            String sql="INSERT INTO cart(prodId,title,imgUrl,price,count) VALUES(?,?,?,?,?)";
//                            SQLiteStatement statement= prodDB.compileStatement(sql);
//
//                            statement.clearBindings();
//
////                            prodDB.insert()
//
//                           int newCount=0;
//                           newCount+=1;
//                            statement.bindString(1,Long.toString(selectedModel.getId()));
//                            statement.bindString(2,selectedModel.getTitle());
//                            statement.bindString(3,selectedModel.getImgUrl());
//                            statement.bindString(4,selectedModel.getPrice());
//                            statement.bindLong(5,newCount);
//
//                            statement.executeInsert();
//
//
//                            Log.d("Cart",selectedModel.getTitle() );
//                            Log.d("Cart",price.getText().toString());
//
//
//                            Toast.makeText(MainActivity.context, "The data has been updated to the database", Toast.LENGTH_SHORT).show();
//                        }else{
//
//
////                            String sql="INSERT INTO cart(prodId , title , imgUrl , price , count) VALUES(?,?,?,?,?)";
////
////                            SQLiteStatement statement= prodDB.compileStatement(sql);
////                            statement.clearBindings();
////                            statement.bindLong(1,i);
////                            statement.bindString(2,mWordList.get(i).toString());
////                            statement.executeInsert();
//                        }

//
                    /*
                    int prodIdIndex=query.getColumnIndex("prodId");
                    int titleIndex=query.getColumnIndex("title");
                    int imgUrlIndex=query.getColumnIndex("imgUrl");
                    int priceIndex=query.getColumnIndex("price");


                    query.moveToFirst();



*/



//                        intent.putExtra("model", new ArrayList<EventModel>().add(selectedModel));

//                        context.startActivity(intent);



            });





        }
    }
}
