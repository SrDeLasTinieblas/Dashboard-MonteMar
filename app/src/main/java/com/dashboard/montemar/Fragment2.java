package com.dashboard.montemar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.juang.jplot.PlotPastelito;
import com.juang.jplot.PlotPlanitoXY;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment2 extends Fragment {

    View view;
    RequestQueue requestQueue;
    //RequestQueue queue = Volley.newRequestQueue(this);  // this = context
    /*private PlotPlanitoXY plot;
    float [] X,Y;*/
    private PlotPastelito pastel;
    private LinearLayout pantalla;
    Fragment2 context;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_2, container, false);
        context=this;
        pantalla = view.findViewById(R.id.pantalla);
        pastel=new PlotPastelito(context.getActivity(),"Ganancias Diarias");//puedes usar simplemente "this" en lugar de context
        float[] datapoints = {2.5f,5,8,11,23,7,16};
        String[] etiquetas={"lunes", "martes", "miercoles","jueves","viernes","sabado","domingo"};
        pastel.SetDatos(datapoints,etiquetas);

   /*antes de mostrar el grafico en pantalla(LinearLayout) deben de ir todos los ajustes "Set" del grafico.
       Todos los metodos publicos que ayudan a personalizar el grafico se describen cada uno en la siguiente sección */

        pastel.SetHD(true); //ajustamos la calidad hd que suaviza bordes del grafico. por default esta desactivado
        pantalla.addView(pastel);

        context = this;

        //btnFragment1 = view.findViewById(R.id.buttonFragment1);
        /*pantalla = view.findViewById(R.id.pantalla);
        X=new float[4]; Y=new float[4];// si se desean graficar datos tipo double debe convertirse de "double a float"
        X[0]=3.4f;Y[0]=2.5f;
        X[1]=11.3f;Y[1]=6.6f;
        X[2]=12.4f;Y[2]=7.6f;
        X[3]=20.9f;Y[3]=10.4f;
        plot = new PlotPlanitoXY(context.getActivity(),"Titulo principal del grafico","titulo eje x","titulo eje y");
        plot.SetSerie1(X,Y,"graph 1",5,true);// el 5 es el tamaño de punto "true" es para unir los puntos
        //con una linea
       /*antes de mostrar el grafico en pantalla(LinearLayout) deben de ir todos los ajustes "Set" del grafico.
       Todos los metodos publicos que ayudan a personalizar el grafico se describen cada uno en la siguiente sección */

       /*
       //agregando imagem.png al fondo de la cuadricula que esta en la carpeta "drawable" del proyecto.
       Drawable myDrawable = getResources().getDrawable(R.drawable.fneon);//debe cambiarse "fneon" por tu imagen
       Bitmap myFondo = ((BitmapDrawable) myDrawable).getBitmap();
       plot.SetImagFondo1(myFondo);
       */

        /*plot.SetHD(true); //ajustamos la calidad hd que suaviza bordes del grafico. por default esta desactivado
        plot.SetTouch(true);// activa el touch sobre el grafico no es necesario colocarlo ya que por default esta activado
        pantalla.addView(plot);*/

        //getRequest();
        postRequest();
        return view;
    }
    public void getRequest(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://192.168.1.21:8000/productos",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Sleep(5000);
                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();



                        } catch (Exception e) {
                            Log.d("JSONException", e.getMessage());
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.networkResponse + " error");
                    }
                }
        );
        // Aqui enviamos la solicitud de la peticion
        requestQueue = Volley.newRequestQueue(context.getActivity());
        requestQueue.add(request);
    }

    public void postRequest(){
        //http://192.168.1.21:8000/
        /**
         * new StringRequest(
         *                 Request.Method.GET,
         *                 APIs.UrlProductos,
         */

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://192.168.1.21:8000/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Usuario", "fqafa");
                /*params.put("Apellidos", "bbbb");
                params.put("Email", "qweee");
                params.put("Direccion", "vsss");
                params.put("NombreProducto", "Atun");*/
                //params.put("Precio", "5");
                System.out.println("==> " +params);

                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(context.getActivity());
        requestQueue.add(postRequest);
    }

    //RequestQueue requestQueue = new StringRequest(Request.Method.POST, "")
        /*String URL_REQUEST="http://192.168.1.21:8000/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REQUEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                System.out.println("POST DATA: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(("POST DATA : RESPONSE Failed" + error));
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("Nombres", "value 1 Data");
                params.put("Apellidos", "value 2 Data");
                params.put("Direccion", "value 4 Data");
                params.put("NombreProducto", "value 4 Data");
                params.put("Precio", "5");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlenconded");
                return params;
            }
        };
        requestQueue.add(stringRequest);*/
    }
