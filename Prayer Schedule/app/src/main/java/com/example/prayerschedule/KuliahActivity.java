package com.example.prayerschedule;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prayerschedule.adapter.kuliah_adapter;
import com.example.prayerschedule.models.Kuliah;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KuliahActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private static final String url_kuliah = "http://192.168.43.5/API%20Kuliah/Api.php";
    ProgressDialog pDialog;
    private static final String TAG = "tag";
    String tag_json_obj = "json_obj_req";
    String tag_json_arry = "json_array_req";
    EditText search;
    private kuliah_adapter kuliahAdapter;

    ArrayList<Kuliah> kuliah_list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mahasiswa);
        //search = (EditText) findViewById(R.id.et_search2);


        //getting recycleView from XML

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        kuliah_list = new ArrayList<>();
        loadKuliah();

//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Kuliah> newList = new ArrayList<>();
        for (Kuliah peminjamanModel : kuliah_list) {
            String nama = peminjamanModel.getNama().toLowerCase();
            String nim = peminjamanModel.getNim().toLowerCase();
            if (nama.contains(newText)) {
                newList.add(peminjamanModel);
                Log.d("apa gt", "isinya: "+nama);
                Log.d("coba", "Isii: "+newList.size());
            } else if (nim.contains(newText)) {
                newList.add(peminjamanModel);
                Log.d("apa gt", "isinya: "+nim);
                Log.d("coba", "Isii: "+newList.size());
            }
        }
        kuliahAdapter = new kuliah_adapter(this, newList);

        kuliahAdapter.setFilter(newList);
        recyclerView.setAdapter(kuliahAdapter);

        return true;


    }

//    public void filter(String text){
//        text = text.toLowerCase();
//        ArrayList<Kuliah> newList = new ArrayList<>();
//
//        for (Kuliah kuliah_models : kuliah_list){
//            String nama = kuliah_models.getNama().toLowerCase();
//            if (nama.contains(text.toLowerCase())) {
//                newList.add(kuliah_models);
//            }
//        }
//        kuliahAdapter.setFilter(newList);
//    }

    public void loadKuliah(){
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_kuliah,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray kuliahs = new JSONArray(response);
                            for (int i = 0; i<kuliahs.length(); i++){
                                JSONObject kuliahObject = kuliahs.getJSONObject(i);

                                String jNim = kuliahObject.get("nim").toString();
                                String jNama = kuliahObject.get("nama").toString();
                                String jKodeMatkul = kuliahObject.get("kode_matkul").toString();
                                String jNamaMatkul = kuliahObject.get("nama_matkul").toString();
                                String jSks = kuliahObject.get("sks").toString();

                                Kuliah kuliah = new Kuliah(jNim, jNama, jKodeMatkul, jNamaMatkul, jSks);
                                kuliah_list.add(kuliah);
                            }

                            kuliah_adapter kuliahAdapter = new kuliah_adapter(KuliahActivity.this, kuliah_list);
                            recyclerView.setAdapter(kuliahAdapter);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        pDialog.hide();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KuliahActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }



}
