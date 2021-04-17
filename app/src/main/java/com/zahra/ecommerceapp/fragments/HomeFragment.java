package com.zahra.ecommerceapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zahra.ecommerceapp.R;
import com.zahra.ecommerceapp.adapters.CategoryAdapter;
import com.zahra.ecommerceapp.adapters.EventsRecyclerViewAdapter;
import com.zahra.ecommerceapp.adapters.NewProductsAdapter;
import com.zahra.ecommerceapp.adapters.PopularProductsAdapter;
import com.zahra.ecommerceapp.models.CategoryModel;
import com.zahra.ecommerceapp.models.EventModel;
import com.zahra.ecommerceapp.models.NewProductsModel;
import com.zahra.ecommerceapp.models.PopularProductsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment {

    LinearLayout linearLayout;
    ProgressDialog progressDialog;
    RecyclerView catRecyclerview, newProductRecyclerview, popularRecyclerview,beautyProductsRecyclerView;

    //Category recyclerview
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //New Product Recyclerview
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;

    //Popular Products
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList;

    //FireStore
    FirebaseFirestore db;


    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    LinkedList<EventModel> prodArrList = new LinkedList<>();
    public String url="https://makeup.p.rapidapi.com/products.json?rapidapi-key=8bbcc4dcefmsh497a1e42e9f16fcp18f779jsn2cbc03566eba&product_category=lipstick";


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progressDialog = new ProgressDialog(getActivity());
        catRecyclerview = root.findViewById(R.id.rec_category);
        newProductRecyclerview = root.findViewById(R.id.new_product_rec);
        popularRecyclerview = root.findViewById(R.id.popular_rec);
        beautyProductsRecyclerView = root.findViewById(R.id.beautyProdRecyclerView);



        db = FirebaseFirestore.getInstance();

        linearLayout = root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);

        //Image Slider
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);

        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1,"Discount On Shoes Items", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount On Perfume", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"70% OFF", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        progressDialog.setTitle("Welcome To Ecommerce App (: ");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        //Category
        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(),categoryModelList);
        catRecyclerview.setAdapter(categoryAdapter);

        //Read Data
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();


                            }
                        } else {

                            Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        //New Products
        newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecyclerview.setAdapter(newProductsAdapter);

        //Read Data
        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();

                            }
                        } else {

                            Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        //Popular Products
        popularRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductsModelList = new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularRecyclerview.setAdapter(popularProductsAdapter);

        //Read Data
        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularProductsModel popularProductsModel = document.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel);
                                popularProductsAdapter.notifyDataSetChanged();

                            }
                        } else {

                            Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });




        StringRequest strReq =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Res",response);

                try{
//                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(response);

                    if(jsonArray.length()>0)
                    {
                        for(int i=0;i <20;i++)
                        {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String name = jsonObject1.getString("name");

                            String imgUrl = jsonObject1.getString("image_link");
                            String brand = jsonObject1.getString("brand");

                            String priceSign  = jsonObject1.getString("price_sign");
                            String currency =  jsonObject1.getString("currency");
                            String price = jsonObject1.getString("price");

                            Integer id = jsonObject1.getInt("id");

                            String description =  jsonObject1.getString("description");


//                            Log.d("Prod",name + " " + imgUrl + " "+ brand + " " + priceSign + " " + currency + " " + price + " " );
                            Log.d( "", String.valueOf(id));
//

                            prodArrList.add(new EventModel( name,  imgUrl,  brand,  priceSign, currency, price, id,description));


                        }

                        Log.d("prodSize", String.valueOf(prodArrList.size()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(getActivity(),prodArrList);
                        beautyProductsRecyclerView.setAdapter(eventsRecyclerViewAdapter);
                        beautyProductsRecyclerView.setLayoutManager(linearLayoutManager);
                        eventsRecyclerViewAdapter.notifyDataSetChanged();

                        Log.d("length",String.valueOf(jsonArray.length()));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("error", error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(strReq);

        return root;
    }
}