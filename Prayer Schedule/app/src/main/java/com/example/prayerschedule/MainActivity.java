package com.example.prayerschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button jsnPub, jsnLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsnLoc = findViewById(R.id.btn_jsonLocal);
        jsnPub = findViewById(R.id.btn_jsonPublic);

        jsnPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), InfoSolatActivity.class);
                startActivity(intent);
            }
        });

        jsnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), KuliahActivity.class);
                startActivity(intent);
            }
        });
    }
}
