package com.example.deberapijson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btAuxiliar (View view){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(" https://jsonplaceholder.typicode.com/users"
                ,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView information = (TextView) findViewById(R.id.txtPepito);
        StringBuilder stringBuilder = new StringBuilder();

        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String nombre = jsonObject.getString("name");
            String correo = jsonObject.getString("email");

            stringBuilder.append("Nombre: ").append(nombre).append("\n");
            stringBuilder.append("Correo: ").append(correo).append("\n\n");
        }

        information.setText(stringBuilder.toString());
    }
}