package com.tobiaswinik.tp05.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tobiaswinik.tp05.MainActivity;
import com.tobiaswinik.tp05.R;
import com.tobiaswinik.tp05.entities.Ciudad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class GameFragment extends PrimaryFragment {

    View layoutRoot;
    Button btn01, btn02, btn03, btn04;
    private MapView mMapView;
    private GoogleMap googleMap;
    Type ciudadType = new TypeToken<ArrayList<Ciudad>>() {}.getType();
    ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
    ArrayList<Ciudad> ciudadesRandom = new ArrayList<Ciudad>();
    TareaAsincronicaObtenerPais tareaAsync = new TareaAsincronicaObtenerPais();
    Ciudad ciudadCorrecta;
    int puntaje = 0;
    int jugadas = 0;

    public GameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null){
            layoutRoot = inflater.inflate(R.layout.fragment_juego, container, false);
            ObtenerReferencias();
            tareaAsync.execute();
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume(); // needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
            }

        }

        SetearListeners();

        this.setearTitulo("¿Que ciudad es?");

        return layoutRoot;
    }

    private void SetearListeners() {
        btn01.setOnClickListener(btnCiudad_Click);
        btn02.setOnClickListener(btnCiudad_Click);
        btn03.setOnClickListener(btnCiudad_Click);
        btn04.setOnClickListener(btnCiudad_Click);
    }

    View.OnClickListener btnCiudad_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String strCiudadClickeada;
            Button btnPresionado;

            btnPresionado = (Button) v;
            strCiudadClickeada = btnPresionado.getText().toString();

            if (strCiudadClickeada == ciudadCorrecta.getName()){
                Toast.makeText(getActivity(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                puntaje++;
                Log.d("AccesoAPI", String.valueOf(puntaje));
                Juego();
            }
            else {
               Toast.makeText(getActivity(), "Pifiaste hermano", Toast.LENGTH_SHORT).show();
               Juego();
            }
        }
    };

    public void Juego(){
        ciudadesRandom.clear();
        for(int i = 1; i <= 4; i++){
            ciudadesRandom.add(obtenerCiudadRandom(ciudades));
        }
        Log.d("AccesoAPI", ciudadesRandom.toString());
        setearCiudadesABotones(btn01, btn02, btn03, btn04);
        ciudadCorrecta = setearCiudadCorrecta();
        Log.d("AccesoAPI", ciudadCorrecta.toString());
        mMapView.getMapAsync(mMapView_getMapAsync);
        jugadas++;
        if (jugadas > 10){
            Toast.makeText(getActivity(), "Juego terminado", Toast.LENGTH_SHORT).show();
            MainActivity actividadContenedora = (MainActivity) getActivity();
            SystemClock.sleep(3000);
            actividadContenedora.irAFragmentRanking();

        }
    }

    private void ObtenerReferencias() {
        mMapView = (MapView) layoutRoot.findViewById(R.id.mapView);
        btn01 = (Button) layoutRoot.findViewById(R.id.btn01);
        btn02 = (Button) layoutRoot.findViewById(R.id.btn02);
        btn03 = (Button) layoutRoot.findViewById(R.id.btn03);
        btn04 = (Button) layoutRoot.findViewById(R.id.btn04);
    }


    protected OnMapReadyCallback mMapView_getMapAsync = new OnMapReadyCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap mMap) {
            googleMap = mMap;
            googleMap.getUiSettings().setZoomGesturesEnabled(false);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            LatLng coordsCorrectas = new LatLng(ciudadCorrecta.lat, ciudadCorrecta.lng);
            MarkerOptions markerCorrecta = new MarkerOptions()
                    .position(coordsCorrectas);
            googleMap.addMarker(markerCorrecta);

            CameraPosition cameraPosition;
            cameraPosition = new CameraPosition.Builder().target(coordsCorrectas).zoom(8).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    };

    public Ciudad obtenerCiudadRandom(ArrayList <Ciudad> ciudades){
        Random rand = new Random();
        return ciudades.get(rand.nextInt(ciudades.size() + 1));
    }

    public void setearCiudadesABotones (Button btn01, Button btn02, Button btn03, Button btn04){
        btn01.setText(ciudadesRandom.get(0).name);
        btn02.setText(ciudadesRandom.get(1).name);
        btn03.setText(ciudadesRandom.get(2).name);
        btn04.setText(ciudadesRandom.get(3).name);
    }

    public Ciudad setearCiudadCorrecta(){
        Random rand = new Random();
        Ciudad ciudadCorrecta;
        int num = rand.nextInt(ciudadesRandom.size());
        ciudadCorrecta = ciudadesRandom.get(num);
        return ciudadCorrecta;
    }

    public class TareaAsincronicaObtenerPais extends AsyncTask<Void, Void, String> {


        public TareaAsincronicaObtenerPais() {
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Estoy en el Main Thread.
        }

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection miConexion = null;
            URL strAPIUrl;
            // Estoy en el Background Thread.

            BufferedReader responseReader;
            String responseLine, strResultado = "";
            StringBuilder sbResponse;
            try {
                String apiURL = "https://api.polshu.com.ar/Data/geonames.json";
                strAPIUrl = new URL(apiURL);
                String stringURL = strAPIUrl.toString();
                Log.d("AccesoAPI", stringURL);
                miConexion = (HttpURLConnection) strAPIUrl.openConnection();
                miConexion.setRequestMethod("GET");
                Log.d("AccesoAPI", "Me conecto");
                if (miConexion.getResponseCode() == 200) {
                    // En un BufferedReader leo todo!
                    Log.d("AccesoAPI", "Conexion OK");
                    responseReader = new BufferedReader(new InputStreamReader(miConexion.getInputStream()));
                    sbResponse = new StringBuilder();
                    while ((responseLine = responseReader.readLine()) != null) {
                        sbResponse.append(responseLine);
                    }
                    responseReader.close();
                    // Hasta aca lei la respuesta en el StringBuilder!
                    // La paso a la variable “strResultado”

                    strResultado = sbResponse.toString();
                    Log.d("Acceso API", "Resultado:" + strResultado);
                } else {
                    // Ocurrió algún error.
                    Log.d("AccesoAPI", "ERROR en la conexion");
                }
            } catch (Exception e) {
                // Ocurrió algún error al conectarme, serán permisos?
                Log.d("AccesoAPI", "ERROR - " + e.getMessage());
            } finally {
                if (miConexion != null) {
                    miConexion.disconnect();
                }
            }
            return strResultado;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            Log.d("AccesoAPI", "OnPostExecute");
            Log.d("AccesoAPI", resultado);

            Gson datosCiudades = new Gson();
            ciudades = datosCiudades.fromJson(resultado, ciudadType);
            Log.d("AccesoAPI", ciudades.toString());
            Juego();
        }


    }
}