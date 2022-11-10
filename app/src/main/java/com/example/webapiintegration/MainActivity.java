package com.example.webapiintegration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ImageView add;
    MyAdapter adapter;
    List<MyContact> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        add=findViewById(R.id.add);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(MainActivity.this);
        ls=new ArrayList<>();
        adapter=new MyAdapter(MainActivity.this,ls);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Add.class));
                //hello
                //hello
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        StringRequest request=new StringRequest(Request.Method.GET, "http://192.168.137.222/smda/get.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj=new JSONObject(response);
                            if(obj.getInt("code")==1)
                            {
                                JSONArray contacts=obj.getJSONArray("contacts");
                                for (int i=0; i<contacts.length();i++)
                                {
                                    JSONObject contact=contacts.getJSONObject(i);
                                    ls.add(new MyContact(contact.getString("name"),contact.getString("phno"),contact.getString("address")));
                                    adapter.notifyDataSetChanged();
                                }

                            }
                            else{
                                Toast.makeText(MainActivity.this,obj.get("msg").toString(),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Error Connecting Server",Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(request);
    }
}