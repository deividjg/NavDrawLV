package com.example.david.navdrawlv;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    ListView lista;
    ArrayList arrayList;
    BDContactos bd;
    Adaptador a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BDContactos(this);
        lista = (ListView)findViewById(R.id.lista);
        arrayList = new ArrayList<Contacto>();

        rellenaBD();
        rellenaArray();

        a = new Adaptador(this,arrayList);
        a.notifyDataSetChanged();
        lista.setAdapter(a);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawernav);
        Toolbar tb = (Toolbar)findViewById(R.id.toolbar);
        if(tb!=null){
            tb.setTitle("Contactos");
            setSupportActionBar(tb);
        }
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,tb, R.string.dw_abierto,R.string.dw_cerradpo);
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    protected void onResume() {
        toggle.syncState();
        super.onResume();
    }

    protected void rellenaBD() {
        if (bd.consultarContactos().size() < 1) {
            bd.insertarContacto(bd.nuevaID(), "David");
            bd.insertarContacto(bd.nuevaID(), "Pedro");
            bd.insertarContacto(bd.nuevaID(), "Valentín");
            bd.insertarContacto(bd.nuevaID(), "Jose Manuel");
            bd.insertarContacto(bd.nuevaID(), "Miguel");
            bd.insertarContacto(bd.nuevaID(), "Jorge");
            bd.insertarContacto(bd.nuevaID(), "Andrés");
            bd.insertarContacto(bd.nuevaID(), "Juanma");
        }
    }

    protected void rellenaArray(){
        arrayList.clear();
        arrayList = bd.consultarContactos();
    }
}
