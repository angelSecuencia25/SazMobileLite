package com.secuencia.saz.sazmobilelite;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.secuencia.saz.sazmobilelite.Modelo.DatosLupita;
import com.secuencia.saz.sazmobilelite.Modelo.ModeloDatos;
import com.secuencia.saz.sazmobilelite.Modelo.ModeloEmpresa;
import com.secuencia.saz.sazmobilelite.Modelo.ModeloNumeroOrden;
import com.secuencia.saz.sazmobilelite.Modelo.ModeloUsuario;
import com.secuencia.saz.sazmobilelite.Modelo.Similar;
import com.secuencia.saz.sazmobilelite.conexion.ConexionBDCliente;
import com.secuencia.saz.sazmobilelite.conexion.ConexionSQLiteHelper;
import com.secuencia.saz.sazmobilelite.conexion.ConexionSqlServer;
import com.secuencia.saz.sazmobilelite.utilidades.ModeloTienda;
import com.secuencia.saz.sazmobilelite.utilidades.Scann;
import com.secuencia.saz.sazmobilelite.utilidades.Utilidades;
import com.toptoche.searchablespinnerlibrary.SearchableListDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;



public class ConusltaMarca extends Fragment {



    int cantidadRegistros;
    static Timer temporizadorPedido;
    private TimerTask tarea;
    private Handler handler = new Handler();
    static int scu=0;
    Similares simi=new Similares();
    static String colorBar, acabadoBar, marcaBar, corridaBar;
    public static boolean not=false;
    public static boolean vuelta=false;
    GridView gridview;
    String empress;
    public static String NombreTienda;
    String idImagen;
    static Context context;
    String ubica;
    //private ZXingScannerView vistaescaner;

    ArrayList<String> arrayList;
    Button btnDetalle;

    static Double puntoBar, puntoCont;
    public static ModeloEmpresa me=new ModeloEmpresa();
    public static ConexionBDCliente bdc=new ConexionBDCliente();
    ConexionSqlServer conex=new ConexionSqlServer();
    int cantidad=0;
    public static int up=0;
    public static String VarEstilo;
    static String clint, BarCodeFIN="";
    static  String estiloBar="";
    String temporad="";
    TextView edtCodigo;
    String variableS,descuento;

    String fechaUlti, horaUlti;

    double descuentoFinal;
    double auxiliarDes;

    public static ArrayList lista=new ArrayList();
    ArrayList listaAcabado=new ArrayList();
    static String CodigoBar="";
    String[] validador;
    String where=" where ";
    String contVal;
    ArrayList<String> listaMarca=new ArrayList();

    ArrayList listaCorrida=new ArrayList();
    static ArrayList puntos=new ArrayList();
    String numeroUsuario;
    TextView idPedido;
    boolean xcaner=false;
    int  buscador=0;
    ModeloTienda mt=new ModeloTienda();
    String[] cantVal;
    String contCant;


    ArrayList listaTiendas=new ArrayList();
    private String numeroTienda;
    long time =System.currentTimeMillis();
    String barcode;
    String estiloTem,puntoTem,precioTem, cantidadTem;
    String dispositivo;

    static int colorCantidad=-1, acabadoCantidad=0, marcaCantidad=0, corridaCantidad=0,puntosCantidad=0,estiloCantidad=0;

    TextView precioTXT;
    int index=0;
    Button btnBardoce;
    double tot;
    String user, scaneado;
    static String sku;
    String intentResummen;
    public static double pre = 0, contenedor;
    public static int   existencias;
    String point;
    public static Spinner spColor,spAcabado, spMarca, spCorrida, punto;
    String num, str,mar;
    public static TextView existenciasTXT, cantidadTXT, unidadesTXT, importeTXT,descuentoTXT,totalTXT;

    public static Spinner sp2;
    static TextView clienteTXT;
    ArrayList listaEstilo=null;


    double r;
    static String in, finn, inc;
    String estilo, color, acabado, marcas, linea, sublinea, temporada, descripcion, observaciones;
    TextView  pagina, basico, comprador, departamento, tacon, plantilla, forro, clasificacion, corrida, suela, ubicacion;
    EditText TxtScanner;
    Toolbar myToolbar;
    ArrayList<String> listaDatos;
    String fecha;
    TextView encabezado;
    String ide;
    public static String listado;
    ArrayList<String> listaDatos2;
    RecyclerView recycler;
    java.sql.Timestamp timestamp=new java.sql.Timestamp(time);
    String fechaDet;
    static String idFecha;
    String NumeroColor;

    ModeloUsuario mu=new ModeloUsuario();
    ModeloDatos md=new ModeloDatos();
    String NombreUsuario;
    View root;
    Double precioPedido;
    Button btnSimilares;

    public  static  String idMarca=null;
    Button btnOtras;

    static DatosLupita dl=new DatosLupita();

    SearchView search;
    private ArrayList<Object> listaNotas;
    EditText edtBucador;
    AutoCompleteTextView autoC;
    SpinnerDialog spinnerDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        root= inflater.inflate(R.layout.fragment_conuslta_marca, container, false);
        context=root.getContext();

        //  Toast.makeText(getActivity(), "Get"+mt.getNombreTienda(), Toast.LENGTH_SHORT).show();
        String[] ar;


        listado=mt.getNombreTienda();

        //obtenerLineaConexion();
        ToolBarNombre();


        getUser();



        sp2=(Spinner)root.findViewById(R.id.sp2);
        btnDetalle=(Button)root.findViewById(R.id.btnDetalle);
        btnOtras=(Button)root.findViewById(R.id.btnOtras);
        btnSimilares=(Button)root.findViewById(R.id.btnSimilares);
        spColor =(Spinner)root. findViewById(R.id.sp);
        spAcabado=(Spinner)root.findViewById(R.id.sp3);
        spMarca=(Spinner)root.findViewById(R.id.sp4);
        spCorrida=(Spinner)root.findViewById(R.id.sp5);
        punto=(Spinner)root.findViewById(R.id.punto);
        precioTXT=(TextView)root.findViewById(R.id.precioTXT);



        descuentoTXT=(TextView)root.findViewById(R.id.descuentoTXT);
        totalTXT=(TextView)root.findViewById(R.id.totalTXT);
        btnBardoce=(Button)root.findViewById(R.id.btnBarCode);
        existenciasTXT=(TextView)root.findViewById(R.id.existenciasTXT);






        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try{

                    sp2.setAdapter(null);
                    spColor.setAdapter(null);
                    spAcabado.setAdapter(null);
                    spCorrida.setAdapter(null);
                    punto.setAdapter(null);



                    contarEstilos();
                    if(estiloCantidad!=0) {

                        sp2.setAdapter(null);
                        llenarEstilos();

                    }





                }catch (IndexOutOfBoundsException e){
                    e.getMessage();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });




        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                buscador=0;
                colorCantidad=0;
                acabadoCantidad=0;
                colorCantidad=0;
                puntosCantidad=0;

                listaCorrida.clear();


                listaAcabado.clear();
                lista.clear();


                limpiarCajas();
                buscador();


                Toast.makeText(getActivity(), "Cargando...", Toast.LENGTH_LONG).show();

                puntos.clear();


                punto.setAdapter(null);
                idMarca=getMarca(spMarca.getSelectedItem().toString());
                contarSp();
                if(colorCantidad>0) {
                    if (Principal.scannPass == true && buscador == 0) {
                        llenarSp();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), R.layout.spinner_item, lista);
                        spColor.setAdapter(adapter);
                        spColor.setSelection(obtenerPosicionItem(spColor, colorBar));
                    } else if (Principal.scannPass == false && colorCantidad == 1 && buscador == 0) {
                        llenarSp();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, lista);
                        spColor.setAdapter(adapter);
                        spColor.setSelection(1);

                    } else if (Principal.scannPass == false && colorCantidad > 1 && buscador == 0) {
                        llenarSp();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, lista);
                        spColor.setAdapter(adapter);

                    } else if (buscador == 1 && Principal.scannPass==false) {
                        //buscarMarcas();
                        llenarCorrida();
                        obtenerCorrida();

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);
                            spCorrida.setSelection(1);





                    } else if (Principal.scannPass == false && buscador == 0) {
                        llenarSp();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, lista);
                        spColor.setAdapter(adapter);

                    }else if(buscador == 1 && Principal.scannPass==true){
                        buscarMarcas();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaMarca);
                        spMarca.setAdapter(adapter);
                        spMarca.setSelection(obtenerPosicionItem(spMarca, marcaBar));


                    }
                }else{
                    Toast.makeText(getActivity(),"Estilo no valido",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(Principal.similarPass==true){

            cargarDatosBarcodeSimilar(dl.getBarcode());
            Principal.similarPass=false;
        }


        FormatoFecha();
        retomarPedidos();
        ConsultarNuevosRegistros();


        inicializarMarca();


        btnSimilares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(punto.getSelectedItem()=="") {
                    Toast.makeText(getActivity(),"Debes seleccionar estilo y punto ",Toast.LENGTH_LONG).show();
                }else{
                    try {


                        Principal.punto=punto.getSelectedItem().toString();
                        dl.setBarcode(barcode);
                        dl.setPunto(punto.getSelectedItem().toString());

                        Intent similar=new Intent(getActivity(),Similares.class);
                        startActivity(similar);
                    }catch (Exception ex){
                        Toast.makeText(getActivity(),"Debes Ingresar un  estilo ",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btnOtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(punto.getSelectedItem()=="") {
                    Toast.makeText(getActivity(),"Debes seleccionar estilo y punto ",Toast.LENGTH_LONG).show();
                }else{
                    try {

                        dl.setBarcode(barcode);
                        dl.setPunto(punto.getSelectedItem().toString());

                        Intent tienda = new Intent(getActivity(), Tiendas.class);
                        startActivity(tienda);
                    }catch (Exception ex){
                        Toast.makeText(getActivity(),"Debes Ingresar un  estilo ",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        btnBardoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xcaner=true;
                startActivity(new Intent(getActivity(), Scann.class));


            }
        });



        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    if(sp2.getSelectedItem().toString().equals("")) {
                        Toast.makeText(getActivity(),"Debes seleccionar estilo y punto ",Toast.LENGTH_LONG).show();
                    }else{
                        try {

                            Principal.estilo=sp2.getSelectedItem().toString();
                            Principal.bar=barcode;
                            Intent detalle = new Intent(getActivity(), Detalle.class);

                            detalle.putExtra("valores", sp2.getSelectedItemId() + "-" + Principal.idColor + "-" + Principal.idAcabado + "-" + idMarca + "-" + empress);

                            startActivity(detalle);
                        }catch (Exception ex){
                            Toast.makeText(getActivity(),"Debes Ingresar un  estilo ",Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (Exception e){

                }
            }
        });






        punto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(root.getContext(), "Cargando...", Toast.LENGTH_SHORT).show();


                if(punto.getSelectedItem().equals("")) {


                }else{
                    consultarCantidadReal();
                    traerPrecio();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


/*

        sp2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                lista.clear();
                spColor.setAdapter(null);
                listaAcabado.clear();
                spAcabado.setAdapter(null);
                listaCorrida.clear();
                spCorrida.setAdapter(null);
                limpiarCajas();

                puntos.clear();
                punto.setAdapter(null);


                precioTXT.setText(null);

                descuentoFinal=0.0;
                auxiliarDes=0.0;


                in=null;
                finn=null;
                inc=null;





            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
*/


/*

        root.findViewById(R.id.prueba).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();

            }
        });



        try{
            spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                @Override
                public void onClick(String item, int position) {
                    Toast.makeText(getActivity(), item + "  " + position + "", Toast.LENGTH_SHORT).show();
                    selectedItems.setText(item + " Position: " + position);

                }
            });

        }catch (Exception e){

        }
*/






        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                try {


                    Toast.makeText(getActivity(), "Cargando...", Toast.LENGTH_LONG).show();




                    listaAcabado.clear();

                    listaCorrida.clear();
                    puntos.clear();
                    spAcabado.setAdapter(null);

                    spCorrida.setAdapter(null);
                    punto.setAdapter(null);
                    precioTXT.setText(null);
                    limpiarCajas();
                    ReinicarContadores();
                    spAcabado.setAdapter(null);
                    listaAcabado.clear();
                    com.toptoche.searchablespinnerlibrary.SearchableSpinner spin = new SearchableSpinner(getActivity());

                    SearchableListDialog sld = new SearchableListDialog();
                    sld.setTitle("selecciona marca");
                    contarSp3();

                    String validador = null;
                    validador = spColor.getSelectedItem().toString();
                    if (!validador.isEmpty()) {


                        if (Principal.scannPass == true) {
                            llenarSp3();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaAcabado);
                            spAcabado.setAdapter(adapter);
                            spAcabado.setSelection(obtenerPosicionItem(spAcabado, acabadoBar));
                            sld.setTitle("selecciona marca");
                            spin.setTitle("Selecciona una marca");
                        } else if (Principal.scannPass == false && acabadoCantidad == 1) {
                            llenarSp3();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaAcabado);
                            spAcabado.setAdapter(adapter);
                            spAcabado.setSelection(1);
                            sld.setTitle("selecciona marca");
                            spin.setTitle("Selecciona una marca");


                        } else if (Principal.scannPass == false && acabadoCantidad > 1) {
                            llenarSp3();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaAcabado);
                            spAcabado.setAdapter(adapter);

                            sld.setTitle("selecciona marca");
                            spin.setTitle("Selecciona una marca");

                        }
                    }

                }catch (IllegalStateException e){
                    e.getMessage();

                }
                }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });



        spAcabado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {

                    String validador = "";
                    validador = spMarca.getSelectedItem().toString();
                    if (validador.equals(" ")) {

                        puntos.clear();
                        punto.setAdapter(null);
                        listaCorrida.clear();
                        spCorrida.setAdapter(null);

                    } else {


                        spCorrida.setAdapter(null);
                        punto.setAdapter(null);
                        listaCorrida.clear();
                        puntos.clear();
                        limpiarCajas();
                        ReinicarContadores();
                        spCorrida.setAdapter(null);
                        listaCorrida.clear();


                        contarSp5();
                        if (Principal.scannPass == true && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);
                            spCorrida.setSelection(obtenerPosicionItem(spCorrida, corridaBar));
                        } else if (Principal.scannPass == false && corridaCantidad == 1 && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);
                            spCorrida.setSelection(1);

                        } else if (Principal.scannPass == false && corridaCantidad > 1 && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);

                        } else if (buscador == 1 && Principal.scannPass == false) {
                            llenarSp5();
                            obtenerCorrida();
                            if (corridaCantidad == 1) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                                spCorrida.setAdapter(adapter);
                                spCorrida.setSelection(1);
                            } else if (corridaCantidad > 1) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                                spCorrida.setAdapter(adapter);
                            }

                        } else if (Principal.scannPass == false && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);

                        } else if (buscador == 1 && Principal.scannPass == true) {
                            llenarSp5();
                            obtenerCorrida();

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);
                            spCorrida.setSelection(obtenerPosicionItem(spCorrida, corridaBar));


                        }
                    }
                }catch(Exception e){

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        spCorrida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String validador = "";
                validador = spCorrida.getSelectedItem().toString();
                if (validador.equals(" ")) {
                    puntos.clear();
                    punto.setAdapter(null);

                }
                else{
                    punto.setAdapter(null);
                    puntos.clear();
                    limpiarCajas();
                    ReinicarContadores();
                    puntos.clear();
                    punto.setAdapter(null);

                    //   llenarExistencias();
                    llenarTabla();
                    contarPuntos();
                    traerDatosProducto();
                    if (Principal.scannPass == true && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);
                        punto.setSelection(obtenerPosicionItem(punto, String.valueOf(puntoBar)));
                        Principal.scannPass = false;
                    } else if (Principal.scannPass == false && puntosCantidad == 1 && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);


                    } else if (Principal.scannPass == false && puntosCantidad > 1 && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);

                    } else if (buscador == 1 && Principal.scannPass == false) {
                        traerDatosProducto();
                        llenarPuntos();
                        if (puntosCantidad == 1) {
                            llenarTabla();


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                            punto.setAdapter(adapter);



                        } else if (puntosCantidad > 1) {
                            llenarTabla();
                            llenarPuntos();

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                            punto.setAdapter(adapter);

                        }


                    } else if (Principal.scannPass == false && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);



                    } else if (buscador == 1 && Principal.scannPass == true) {
                        Similar simi = new Similar();
                        traerDatosProducto();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);
                        punto.setSelection(obtenerPosicionItem(punto, String.valueOf(puntoBar)));
                        Principal.scannPass = false;


                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;


    }


    private void generarCantidad() {
        up += cantidad;
        cantidad = 0;

        contVal+="-"+sp2.getSelectedItem().toString();
        contCant+="-"+up;
        unidadesTXT.setText(String.valueOf(up));

    }

    private void generarImporte() {
        double Descuento;

        Descuento=Double.parseDouble(descuento);
        contenedor = Double.parseDouble(variableS);
        precioPedido=(contenedor-Descuento) * cantidad;
        pre += (contenedor-Descuento) * cantidad;

        importeTXT.setText("$"+String.valueOf(pre));

    }


    private void enviarADetalle() {
        double tem;
        puntoTem = punto.getSelectedItem().toString();
        tem = ((Double.parseDouble(variableS)-Double.parseDouble(descuento)) * cantidad);
        precioTem = String.valueOf(tem);
        estiloTem = sp2.getSelectedItem().toString();
        cantidadTem = String.valueOf(cantidad);
        Resumen(estiloTem, puntoTem, cantidadTem, precioTem);
        estiloTem = null;
        puntoTem = null;
        cantidadTem = null;
        precioTem = null;
    }




    public void retomarPedidos(){
        int id=0;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT id,SUM(cantidad),SUM(total),cliente FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cursor.moveToNext()) {
            id=cursor.getInt(0);
            if(id!=0){
                unidadesTXT.setText(cursor.getString(1));
                importeTXT.setText("$"+cursor.getString(2));
                clienteTXT.setText(cursor.getString(3));
            }



        }

    }




    public void deleteContenedor(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);


        SQLiteDatabase db = conn.getReadableDatabase();





        db.execSQL("DELETE FROM  " + Utilidades.TABLA_CONTENEDOR);


        db.close();
    }

    public String sacarPares(){
        String pares="";
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT SUM(cantidad) FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cursor.moveToNext()) {
            pares=(cursor.getString(0));



        }
        return pares;
    }

    public String sacarTotal(){
        String total="";
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT SUM(total)FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cursor.moveToNext()) {
            total=(cursor.getString(0));



        }
        return total;
    }





    public int getOrden(){
        int idOrden = 0;

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT idOrden FROM " + Utilidades.TABLA_ORDEN , null);
        while (cursor.moveToNext()) {

            idOrden=cursor.getInt(0);
        }
        return idOrden;
    }





    public void Resumen(String estilo,String punto, String cantidad, String precio){
        clint=clienteTXT.getText().toString();

        intentResummen+="-"+clint+","+estilo+","+punto+","+cantidad+","+precio+","+spColor.getSelectedItem()+","+idMarca+","+variableS+","+barcode+","+Principal.idAcabado+","+idImagen;
    }


    public static void cargarDatos(String var){
        Principal.scannPass=true;
        scu=0;
        BarCodeFIN="";
        estiloBar="";
        VarEstilo="";


        sku=null;
        VarEstilo=var;
        buscarEnSku();
        validarSku();
        if(scu==0) {
            getBarcode();
        }
        getEstilo();
        if(estiloBar.equals("")){
            Toast.makeText(context, "Codigo de barras no existente en el sistema ", Toast.LENGTH_LONG).show();
            Principal.scannPass=false;
        }
       // sp2.setText(estiloBar);
        cargarDatosBarcode();

        llenarSp();


    }
    public static String getEstiloSimilar(String barcode){
        scu=0;
        String estilo="";
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs = st.executeQuery("select a.ESTILO from colores c inner join articulo a on c.numero = a.color where a.BARCODE='"+barcode+"'");

            while (rs.next()) {
                estilo=rs.getString(1);

            }


        } catch (SQLException e) {
            e.getMessage();
        }
        return estilo;
    }

    public static void cargarDatosBarcodeSimilar(String barcode){
        Principal.scannPass=true;
        Similar simi=new Similar();
        puntoBar=Double.parseDouble(simi.getPunto());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select ac.acabado, co.color, ma.MARCA ,cor.Nombre from articulo a inner join acabados ac  on a.ACABADO=ac.numero inner join colores co on a.COLOR=co.numero inner join marcas ma on a.MARCA=ma.NUMERO inner join corridas cor on a.corrida=cor.id where a.barcode='"+barcode+"';";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                acabadoBar=rs.getString(1);
                colorBar=rs.getString(2);
                marcaBar=rs.getString(3);
                corridaBar=rs.getString(4);
            }
            String estilo=getEstiloSimilar(barcode);
            //sp2.setText(estilo);
            llenarSp();
            //
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static void cargarDatosBarcode(){

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select ac.acabado, co.color, ma.MARCA ,cor.Nombre from articulo a inner join acabados ac  on a.ACABADO=ac.numero inner join colores co on a.COLOR=co.numero inner join marcas ma on a.MARCA=ma.NUMERO inner join corridas cor on a.corrida=cor.id where a.barcode='"+BarCodeFIN+"';";
            ResultSet rs = st.executeQuery(sql);



            while (rs.next()) {
                acabadoBar=rs.getString(1);
                colorBar=rs.getString(2);
                marcaBar=rs.getString(3);
                corridaBar=rs.getString(4);
            }

            //
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void buscarEnMitienda(int real){
        try {

            List<Map<String, String>> data = null;
            data = new ArrayList<Map<String, String>>();
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String puntoSp=punto.getSelectedItem().toString();
            String sql="lupitaApartados'"+barcode+"',"+puntoSp+","+listado+",''";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd=rs.getMetaData();
            while(rs.next()) {


                // int apartado=consultarAPartados(barcode,puntoSp);
                existencias=(rs.getInt(2));
                existenciasTXT.setText(String.valueOf(existencias));

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            Toast.makeText(getActivity(), "No hay existencias ", Toast.LENGTH_LONG).show();


            int validador = validarTemporada(temporad);
            if (validador>0) {
                Negar();
            }
            existenciasTXT.setText("0");

        }


    }

    public int validarTemporada(String temporada)
    {

        int tem=0;
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select * from artnegTemp where temporada="+temporada;
            ResultSet rs=st.executeQuery(sql);

            while (rs.next())
            {

                tem=rs.getInt(1);

            }





        } catch (SQLException e){
            e.getMessage();
        }

        return  tem;
    }


    public void consultarCantidadReal(){
        int real=0;
        try {

            List<Map<String, String>> data = null;
            data = new ArrayList<Map<String, String>>();
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT (isnull(cantreal,0))   FROM existen WHERE barcode='"+barcode+ "' and talla="+punto.getSelectedItem()+" and tienda="+listado;
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd=rs.getMetaData();
            while(rs.next()) {
                real =rs.getInt(1);
                Toast.makeText(root.getContext(), "Cargando...", Toast.LENGTH_SHORT).show();

            }


        } catch (SQLException e1) {
            Toast.makeText(getActivity(),"Error cantidad Real",Toast.LENGTH_LONG).show();
        }
        buscarEnMitienda(real);

    }




    public void llenarPuntos(){

        try {
            puntos.clear();
            puntos.add("");

            double inicio, fin, medio;
            inicio = Double.valueOf(in);
            fin = Double.valueOf(finn);
            medio = Double.valueOf(inc);

            for (double i = inicio; i < fin + medio; i = i + medio) {

                puntosCantidad++;
                puntos.add(i);
            }

        }catch (Exception e){

        }
    }


    public void contarPuntos(){

        try {
            puntos.clear();
            puntos.add("");

            double inicio, fin, medio;
            inicio = Double.valueOf(in);
            fin = Double.valueOf(finn);
            medio = Double.valueOf(inc);

            for (double i = inicio; i < fin + medio; i = i + medio) {

                puntosCantidad++;

            }

        }catch (Exception e){

        }
    }


    public static int obtenerPosicionItem(Spinner spinner, String fruta) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(fruta)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }


    public  static void llenarSp() {
        lista.add(null);
        lista.clear();
        String add="";
        lista.add("");





        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT c.color, numero from colores c inner join articulo a on c.numero = a.color where a.estilo = '"+sp2.getSelectedItem()+"' and a.marca="+idMarca;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                add=rs.getString(1);
                colorCantidad ++;
                lista.add(add);

            }

            //
        } catch (Exception e) {

        }

    }




    public void contarSp() {
        lista.add(null);
        lista.clear();
        String add="";
        lista.add("");
        idMarca=getMarca(spMarca.getSelectedItem().toString());



        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT c.color, c.numero from colores c inner join articulo a on c.numero = a.color inner join marcas m on m.numero=a.marca where a.estilo = '"+sp2.getSelectedItem()+"' and a.marca="+idMarca;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                add=rs.getString(1);
                colorCantidad ++;

            }

            //
        } catch (Exception e) {

        }

    }


    public  void buscarMarcas() {
        listaMarca.add(null);
        listaMarca.clear();
        String add="";

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  marcas.MARCA,articulo.MARCA   from articulo inner join marcas on articulo.MARCA=marcas.NUMERO where estilo='"+sp2.getSelectedItem()+"' ";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {


                add=rs.getString(1);
                marcaCantidad ++;
                listaMarca.add(add);

            }

            //
        } catch (Exception e) {

        }



    }
    public static String getColor(String color){

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT numero FROM colores WHERE color='"+color+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                Principal.idColor=rs.getString(1);



            }


        } catch (Exception e) {

        }

        return Principal.idColor;
    }


    public void llenarSp3() {
        listaAcabado.clear();
        listaAcabado.add("");
        Principal.idColor=getColor(spColor.getSelectedItem().toString());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT ac.Acabado, numero from acabados ac inner join articulo a on ac.numero = a.acabado where a.estilo = '"+sp2.getSelectedItem()+"' and a.Color = "+Principal.idColor+"";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                acabadoCantidad++;

                listaAcabado.add(rs.getString(1));




            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp3", Toast.LENGTH_SHORT).show();
        }

    }

    public void contarSp3() {
        listaAcabado.clear();
        listaAcabado.add("");
        Principal.idColor=getColor(spColor.getSelectedItem().toString());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT ac.Acabado, numero from acabados ac inner join articulo a on ac.numero = a.acabado where a.estilo = '"+sp2.getSelectedItem()+"' and a.Color = "+Principal.idColor+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                acabadoCantidad++;

                String add=(rs.getString(1));




            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp3", Toast.LENGTH_SHORT).show();
        }

    }

    public String getAcabado(String acabado){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT numero FROM acabados WHERE acabado='"+acabado+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                Principal.idAcabado=rs.getString(1);



            }


        } catch (Exception e) {

        }
        return Principal.idAcabado;
    }

    public void llenarSp4() {

        listaMarca.clear();
        listaMarca.add(" ");

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  marca, numero from marcas ";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                marcaCantidad++;
                listaMarca.add(rs.getString(1));
                spMarca.setId(rs.getInt(2));




            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Error en sp4", Toast.LENGTH_SHORT).show();
        }

    }


    public void contarSp4() {

        listaMarca.clear();
        listaMarca.add(" ");

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  marca, numero from marcas";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                marcaCantidad++;
                String add=(rs.getString(1));
                String s=(rs.getString(2));




            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Error en sp4", Toast.LENGTH_SHORT).show();
        }

    }

    public String getMarca(String marca){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT numero FROM marcas WHERE marca='"+marca+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                idMarca=rs.getString(1);

            }

        } catch (Exception e) {

        }
        return idMarca;
    }


    public void llenarSp5() {
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        Principal.idAcabado=getAcabado(spAcabado.getSelectedItem().toString());
        listaCorrida.clear();
        listaCorrida.add(" ");


        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select  co.Nombre as Corrida,  co.id from corridas co inner join articulo a on co.id = a.corrida where a.estilo = '"+sp2.getSelectedItem()+"' and a.Color = "+Principal.idColor+" and a.acabado = "+Principal.idAcabado+" and a.marca =" +idMarca+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                corridaCantidad++;
                listaCorrida.add(rs.getString(1));
                spCorrida.setId(rs.getInt(2));



            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp5", Toast.LENGTH_SHORT).show();
        }

    }

    public void llenarCorrida() {
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        //Principal.idAcabado=getAcabado(spAcabado.getSelectedItem().toString());
        listaCorrida.clear();
        listaCorrida.add(" ");


        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select  co.Nombre as Corrida,  co.id from corridas co inner join articulo a on co.id = a.corrida where a.estilo = '"+sp2.getSelectedItem()+"'  and a.marca =" +idMarca+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                corridaCantidad++;
                listaCorrida.add(rs.getString(1));
                spCorrida.setId(rs.getInt(2));



            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp5", Toast.LENGTH_SHORT).show();
        }

    }

    public void contarSp5() {
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        Principal.idAcabado=getAcabado(spAcabado.getSelectedItem().toString());
        listaCorrida.clear();



        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select  co.Nombre as Corrida,  co.id from corridas co inner join articulo a on co.id = a.corrida where a.estilo = '"+sp2.getSelectedItem()+"' and a.Color = "+Principal.idColor+" and a.acabado = "+Principal.idAcabado+" and a.marca =" +idMarca+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                corridaCantidad++;
                String d=(rs.getString(1));
                String s=(rs.getString(2));



            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp5", Toast.LENGTH_SHORT).show();
        }

    }


    public void obtenerCorrida() {
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        listaCorrida.clear();
        listaCorrida.add(" ");

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  co.Nombre as Corrida,  co.id from corridas co inner join articulo a on co.id = a.corrida where a.estilo = '"+sp2.getSelectedItem()+"' and  a.marca =" +idMarca+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                corridaCantidad++;
                listaCorrida.add(rs.getString(1));
                spCorrida.setId(rs.getInt(2));

            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp5", Toast.LENGTH_SHORT).show();
        }

    }

    public String getCorrida(String corrida){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT id FROM corridas WHERE nombre='"+corrida+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                Principal.idCorrida=rs.getString(1);



            }


        } catch (Exception e) {

        }
        return Principal.idCorrida;
    }

    public void traerPrecio() {
        double Total;
        DecimalFormat formato = new DecimalFormat("#.##");
        Principal.scannPass=false;
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
           // String sql="select precio,  DESCTO from precios where BARCODE="+"'"+barcode+"' and talla="+punto.getSelectedItem();
            String sql="select precio,  isnull(DESCTO,0) from precios where BARCODE="+"'"+barcode+"' and talla="+punto.getSelectedItem();
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                Toast.makeText(root.getContext(), "Cargando...", Toast.LENGTH_SHORT).show();
                variableS=rs.getString(1);
                descuento=rs.getString(2);
                auxiliarDes=Double.parseDouble(descuento)/100;
                descuentoFinal = Double.parseDouble(variableS)*auxiliarDes;
                Total=Double.parseDouble(variableS)- descuentoFinal;
                precioTXT.setText("$"+variableS);
                descuentoTXT.setText("%"+descuento);
                totalTXT.setText(String.valueOf(formato.format(Total)));




            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Error en traer precio", Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarCajas(){

        existenciasTXT.setText(null);
        totalTXT.setText(null);
        descuentoTXT.setText(null);
        precioTXT.setText(null);

    }
    public void llenarTabla(){
        Principal.idCorrida=getIdCorrida();
        where=" where ";
        if(buscador==0){
            where+="a.estilo = '"+sp2.getSelectedItem()+"' and a.Color ="+Principal.idColor+" and a.acabado ="+Principal.idAcabado+" and a.marca ="+idMarca+" and a.corrida="+Principal.idCorrida;
        }else if(buscador==1){
            where+="a.estilo = '"+sp2.getSelectedItem()+"' and a.marca ="+idMarca+" and a.corrida="+Principal.idCorrida;
        }


        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();

            String sql="select a.BARCODE,co.inicial,co.final,co.incremento,c.color,ma.marca,ac.acabado,im.id,a.UBICA,t.NUMERO,clas.NUMERO,sl.NUMERO,su.numero,ta.NUMERO,co.id\n" +
                    " from articulo a inner join lineas l on a.LINEA=l.NUMERO inner join sublinea sl on a.SUBLINEA=sl.NUMERO inner join temporad t on a.TEMPORAD=t.NUMERO\n" +
                    "  inner join proveed p on a.PROVEED=p.numero\n" +
                    "  left join empleado e on a.comprador=e.numero inner join departamentos d on a.DEPARTAMENTO=d.NUMERO\n" +
                    "  inner join tacones ta on a.TACON=ta.NUMERO inner join plantillas pl on a.PLANTILLA=pl.NUMERO inner join forros f on a.FORRO=f.NUMERO \n" +
                    "  inner join corridas co on a.corrida=co.id inner join suelas su on a.SUELA=su.numero inner join colores c on a.color = c.numero\n" +
                    "  inner join acabados ac on a.ACABADO=ac.NUMERO inner join marcas ma on a.MARCA=ma.NUMERO left join imagenes im on a.id=im.id inner join clasific clas on a.CLASIFIC=clas.numero "+ where ;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Similar simi=new Similar();
                barcode=rs.getString(1);
                in=rs.getString(2);
                finn=rs.getString(3);
                inc=rs.getString(4);
                color=rs.getString(5);
                marcas=rs.getString(6);
                acabado=rs.getString(7);
                idImagen=rs.getString(8);
                ubica=rs.getString(9);
                simi.setMarca(marcas);
                simi.setColor(color);
                simi.setAcabado(acabado);
                simi.setTemporada(rs.getString(10));
                simi.setClasificacion(rs.getString(11));
                simi.setSubLinea(rs.getString(12));
                simi.setSuela(rs.getString(13));
                simi.setTacon(rs.getString(14));
                simi.setCorrida(rs.getString(15));
            }


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en llenar Tabla", Toast.LENGTH_SHORT).show();
        }

    }


    public void traerDatosProducto(){
        Principal.idCorrida=getIdCorrida();

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();

            String sql="select a.BARCODE,co.inicial,co.final,co.incremento,c.color,ma.marca,ac.acabado,im.id,a.UBICA,a.temporad\n" +
                    " from articulo a inner join lineas l on a.LINEA=l.NUMERO inner join sublinea sl on a.SUBLINEA=sl.NUMERO inner join temporad t on a.TEMPORAD=t.NUMERO\n" +
                    "  inner join proveed p on a.PROVEED=p.numero\n" +
                    "  left join empleado e on a.comprador=e.numero inner join departamentos d on a.DEPARTAMENTO=d.NUMERO\n" +
                    "  inner join tacones ta on a.TACON=ta.NUMERO inner join plantillas pl on a.PLANTILLA=pl.NUMERO inner join forros f on a.FORRO=f.NUMERO \n" +
                    "  inner join corridas co on a.corrida=co.id inner join suelas su on a.SUELA=su.numero inner join colores c on a.color = c.numero\n" +
                    "  inner join acabados ac on a.ACABADO=ac.NUMERO inner join marcas ma on a.MARCA=ma.NUMERO left join imagenes im on a.id=im.id where a.estilo = '"+sp2.getSelectedItem()+"' and a.marca = "+idMarca+" and a.corrida="+Principal.idCorrida+" and a.color="+Principal.idColor+" and a.acabado="+Principal.idAcabado+"";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {


                barcode=rs.getString(1);
                in=rs.getString(2);
                finn=rs.getString(3);
                inc=rs.getString(4);
                color=rs.getString(5);
                marcas=rs.getString(6);
                acabado=rs.getString(7);
                idImagen=rs.getString(8);
                ubica=rs.getString(9);
                temporad=rs.getString(10);








            }


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en llenar Tabla", Toast.LENGTH_SHORT).show();
        }

    }



    public void ToolBarNombre(){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select nombre from tiendas where numero="+listado;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                NombreTienda=rs.getString(1);


            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en llenar encabezado", Toast.LENGTH_SHORT).show();
        }

    }





    public void FormatoFecha(){
        String au=null;
        int cont=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());

        Date date=new Date();
        String aux = dateFormat.format(date);
        String[] fecha=aux.split("");
        for(int i=1;i<fecha.length;i++){
            int auxiliar=Integer.parseInt(fecha[i]);
            int  x=0;
            for( x=0;x<10;x++){

                if(x==auxiliar){

                    au+="-"+x;




                }

            }
        }
        String[] a=au.split("-");
        idFecha=a[1]+a[2]+a[3]+a[4]+a[5]+a[6]+a[7]+a[8]+a[9]+a[10]+a[11]+a[12]+a[13]+a[14];


    }


    public void getUser(){
        try {


            ModeloNumeroOrden mno=new ModeloNumeroOrden();
            mno.setUsuario(mu.getNombre());
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs=st.executeQuery("select numero, nombre from empleado where [user]='"+ mu.getNombre()+"'");

            while(rs.next()){
                numeroUsuario=rs.getString(1);
                NombreUsuario=(rs.getString( 2));
            }


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error al optener usuario", Toast.LENGTH_SHORT).show();
        }
    }




    public static void buscarEnSku(){

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT saz FROM sku WHERE sku ='"+VarEstilo+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                sku=(rs.getString(1));



            }

        } catch (SQLException xe) {
            xe.getMessage();

        }
    }

    public static void validarSku(){
        String predeterminada = "SALIDA";

        //declaramos una palabra de entrada
        String   entrada = sku;

        //variable usada para verificar si las palabras son iguales
        String aux="";

        //se verifica que ambas palabras tengan la misma longitud
        //si no es asi no se pueden comparar
        if(sku!=null) {
            configurarBarcodeSku();
            consultarPuntoBar();
            scu=1;

        }else{

            configurarBarcode();

        }





    }
    public static  void tallasBarcode(Double talla){

        try {

            double inicio, fin, medio;
            inicio = Double.valueOf(in);
            fin = Double.valueOf(finn);
            medio = Double.valueOf(inc);
            puntos.add(talla);

            for (double i = inicio; i < fin + medio; i = i + medio) {


                if(i!=talla){
                    puntos.add(i);
                }

            }
        }catch (Exception e){

        }
    }
    public static void configurarBarcodeSku(){
        String aux=sku;
        String[] barcode=aux.split("");
        int tamaño=barcode.length;
        for(int i=0;i<tamaño-3;i++){
            BarCodeFIN+=barcode[i];
        }







    }


    public static void configurarBarcode(){
        String aux=VarEstilo;
        String[] barcode=aux.split("");
        String res=""+barcode[9]+barcode[10]+barcode[11];
        Double resint=Double.parseDouble(res);
        puntoBar=resint/10;
        CodigoBar=barcode[0]+barcode[1]+barcode[2]+barcode[3]+barcode[4]+barcode[5]+barcode[6]+barcode[7]+barcode[8];
    }


    public static void consultarPuntoBar(){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select  LEFT(saz,LEN(saz)-3) as barcode, cast(cast(RIGHT(saz,3)as decimal(4,1))/10 as decimal(4,1))  as talla from sku where sku = '"+VarEstilo+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                puntoBar=rs.getDouble(2);



            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (SQLException xe) {
            xe.getMessage();
            // Toast.makeText(getApplicationContext(), "Error obtener barcode", Toast.LENGTH_SHORT).show();
        }
    }
    public static void getBarcode(){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs = st.executeQuery("select p.barcode from precios p inner join articulo a on a.barcode = p.barcode where p.talla = "+puntoBar+" and a.id = "+CodigoBar);


            while (rs.next()) {
                BarCodeFIN=rs.getString(1);



            }

            // Toast.makeText(Principal.this,"Inicio de sesion Exitosa...!!!: " + empresa, Toast.LENGTH_LONG).show();
        } catch (SQLException xe) {
            xe.getMessage();
            // Toast.makeText(getApplicationContext(), "Error obtener barcode", Toast.LENGTH_SHORT).show();
        }

    }

    public static void getEstilo(){
        scu=0;
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs = st.executeQuery("select a.ESTILO from colores c inner join articulo a on c.numero = a.color where a.BARCODE='"+BarCodeFIN+"'");

            while (rs.next()) {
                estiloBar=rs.getString(1);



            }


        } catch (Exception e) {

        }

    }
    public void getIdPedido(){

        idPedido.setText("No. de pedido: " + idFecha);
    }



    public void notification(String title, String message, Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = createID();
        String channelId = "channel-id";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_MAX;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.logotipo)//R.mipmap.ic_launcher
                .setContentTitle(title)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentText(message)
                .setVibrate(new long[]{100, 250})
                .setLights(Color.BLACK, 500, 5000)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary));

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        Principal.location=2;
        stackBuilder.addNextIntent(new Intent(context, menu.class));

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

    public int createID() {
        Date now = new Date();
        int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss", Locale.FRENCH).format(now));
        return id;
    }

    private void IniciarTemporizador(){
        try {
            temporizadorPedido = new Timer();
            tarea = new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {


                            vuelta = true;
                            not = true;
                            ConsultarNuevosRegistros();
                            if (cantidadRegistros == Principal.hiloCantidad) {


                                consultaUltimoRegistro();


                            }

                            if (cantidadRegistros > Principal.hiloCantidad) {
                                mandarNotificacion();
                                Principal.hiloCantidad = cantidadRegistros;
                            } else {
                                Principal.hiloCantidad = cantidadRegistros;
                            }

                        }
                    });
                }
            };
            temporizadorPedido.schedule(tarea, 0, 10000);
        }catch (RuntimeException r){

        }catch (Exception e){

        }
    }

    public void mandarNotificacion(){
        ModeloNumeroOrden mno=new ModeloNumeroOrden();
        int AreaActual=getAreaActual();
        try {
            String tienda=ultimaVez();
            ModeloUsuario mu=new ModeloUsuario();
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select top 1 comandero.cliente ,estilo, comanderoDet.numero from comanderoDet inner join comanderoLog on comanderoLog.numero=comanderoDet.numero inner join comandero on comandero.numero=comanderoDet.numero where comanderodet.status=1 and comandero.tienda="+tienda+" and comanderoLog.hora >'"+horaUlti+"' and comanderoLog.fecha >= '"+fechaUlti+"' and empleado='"+mu.getNombre()+"' order by hora desc ";
            ResultSet rs = st.executeQuery(sql);


            if(cantidadRegistros>Principal.hiloCantidad)
            {

                while (rs.next()) {
                    String cliente = rs.getString(1);
                    String estilo = rs.getString(2);
                    String numero = rs.getString(3);
                    if (cantidadRegistros>Principal.hiloCantidad || AreaActual== -1 ) {
                        notification("PEDIDO SURTIDO DEL CLIENTE: " + cliente, "Estilo: " + estilo, context);
                        Principal.hiloCantidad=cantidadRegistros;
                    }

                }

                Principal.hiloCantidad=cantidadRegistros;

            }


        } catch (RuntimeException r){

        }catch (Exception e) {

        }

    }


    public void contarEstilos() {



        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select estilo from articulo where marca="+spMarca.getId();
            ResultSet rs = st.executeQuery(sql);




            while (rs.next()) {

                estiloCantidad++;
                String add=(rs.getString(1));


            }
            st.close();


        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Error en sp4", Toast.LENGTH_SHORT).show();
        }

    }


    public String ultimaVez(){
        String numeroT=null;
        try {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();

            String sql="SELECT nombreT FROM tienda where id=1";
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                numeroT = (cursor.getString(0));
            }
        }catch (RuntimeException r){

        }catch (Exception e){

        }

        return numeroT;
    }

    public void llenarEstilos() {



        listaEstilo=new ArrayList();
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select estilo from articulo where marca="+idMarca;
            ResultSet rs = st.executeQuery(sql);




            while (rs.next()) {

                String estilo=(rs.getString(1));


                if(!estilo.isEmpty()){
                    listaEstilo.add(estilo);
                }


            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaEstilo);
            sp2.setAdapter(adapter);
            st.close();


        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Error en sp4", Toast.LENGTH_SHORT).show();
        }

    }

    public void ConsultarNuevosRegistros(){
        ModeloNumeroOrden mno=new ModeloNumeroOrden();
        try {

            String tienda=ultimaVez();
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT COUNT(numero) FROM comanderoDet where tienda="+tienda+" and [status]=1";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                cantidadRegistros=rs.getInt(1);

            }


        } catch (RuntimeException r){

        }catch (Exception e) {
            Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
        }
    }

    public void consultaUltimoRegistro(){
        try{
            String tienda =ultimaVez();
            Statement st=bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select top 1 fecha, hora from comanderoDet inner join comanderoLog on comanderoLog.numero=comanderoDet.numero where status=1 and tienda="+tienda+"  order by fecha, hora desc";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                fechaUlti=rs.getString(1);
                horaUlti=rs.getString(2);
            }

        }catch (SQLException e){

        }catch (RuntimeException r){

        }

    }
    public String getIdCorrida(){
        String idCorrida=null;
        try {

            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql=" select DISTINCT corridas.id from corridas  inner join articulo on  corridas.id=articulo.corrida  where estilo='"+sp2.getSelectedItem()+"' and marca="+idMarca +"and nombre='"+spCorrida.getSelectedItem()+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                idCorrida=rs.getString(1);

            }


        }catch (RuntimeException r){

        } catch (Exception e) {
            Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
        }
        return  idCorrida;
    }
    public String getIdUsuario(){
        String idUsuario=null;
        ModeloUsuario mu=new ModeloUsuario();
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "Select numero from empleado where nombre='"+mu.getNombre()+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                idUsuario=(rs.getString(1));

            }

        } catch (RuntimeException r){

        }catch (Exception e) {
        }
        return idUsuario;
    }
    public int getAreaActual(){
        int area=0;
        String idEmpleado=getIdUsuario();

        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select top 1 idArea from AreasAsignadas where idEmpleado="+idEmpleado+" and  fecha=CONVERT(nCHAR(8), getDate() , 112) order by hora desc ";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                area=(rs.getInt(1));

            }

        } catch (RuntimeException r){

        } catch (Exception e) {
        }
        return area;
    }

    public void Negar(){
        String aux=traerFecha();
        String[] date=aux.split(" ");
        String puntoSp=punto.getSelectedItem().toString();
        String fecha=date[0];
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "insert into artneg (SOCIO,PUNTO, CANTIDAD, BARCODE,llave,fecha,tienda) VALUES (-1,"+puntoSp+",1,'"+barcode+"',NEWID(),'"+fecha+"',"+ultimaVez()+");";
            st.executeUpdate(sql);



        } catch (SQLException e){
            e.getMessage();
        }

    }
    public void ReinicarContadores(){
        colorCantidad=0;
        acabadoCantidad=0;
        corridaCantidad=0;

}

    public void limpiarListas(){
        listaCorrida.clear();
        listaAcabado.clear();
        lista.clear();
    }


    public void mismoDispositivo() {
        String modelo = Build.MODEL;
        String serie = Build.MANUFACTURER;
        String marca = Build.ID;
        try {
            Statement st = conex.conexionBD().createStatement();

            String sql = " SELECT idDisp FROM smAppAccesos where mail='" + mu.getCorreo() + "' and idempresa=" + me.getIdEmpresa() +"and app=1";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dispositivo = rs.getString(1);
            }

        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "No sé puede actualizar el dispositivo", Toast.LENGTH_SHORT).show();
        }

        if (!dispositivo.isEmpty()) {

            String predeterminada = dispositivo;

            //declaramos una palabra de entrada
            String entrada = marca+"-"+serie+"-"+modelo;

            //variable usada para verificar si las palabras son iguales
            String aux = "";

            //se verifica que ambas palabras tengan la misma longitud
            //si no es asi no se pueden comparar
            if (entrada != null) {
                if (predeterminada.length() == entrada.length()) {

                    for (int i = 0; i < predeterminada.length(); i++) {

                        //verificamos si el primer caracter de predeterminada
                        //es igual al primero de entrada
                        if (predeterminada.charAt(i) == entrada.charAt(i)) {
                            //si es asi guardamos ese concatenamos el caracter a la variable aux
                            aux += predeterminada.charAt(i);
                        }
                    }

                    //al finalizar el bucle verificamos si la variable aux es
                    //igual a la predeterminada
                    if (aux.equals(predeterminada)) {

                        //no hay pedo

                    } else {

                        android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(getActivity());
                        alerta.setMessage("Se ha cerrado la sesión actual ya que otro usuario ha accedido con tus datos en otro dispositivo.")
                                .setCancelable(false).setIcon(R.drawable.aviso)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getActivity(), Principal.class);
                                        startActivity(intent);


                                    }
                                });

                        android.app.AlertDialog titulo = alerta.create();
                        titulo.setTitle("Aviso");
                        titulo.show();

                    }


                } else {

                    android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(getActivity());
                    alerta.setMessage("Se ha cerrado la sesión actual ya que otro usuario ha accedido con tus datos en otro dispositivo.")
                            .setCancelable(false).setIcon(R.drawable.aviso)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getActivity(), Principal.class);
                                    startActivity(intent);


                                }
                            });

                    android.app.AlertDialog titulo = alerta.create();
                    //              titulo.setTitle("Aviso");
//                titulo.show();


                }
            }

        }
    }

    public String getIdEmpresa(){
        String id="";
        try{
            Statement st= conex.conexionBD().createStatement();

            String sql="select idEmpresa from logins where idEmpresa='"+me.getIdEmpresa()+"'";
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                id=rs.getString(1);
            }

        }catch (Exception e ){
            e.getMessage();
            Toast.makeText(getActivity(), "No sé puede consultar el id de la empresa", Toast.LENGTH_SHORT).show();
        }
        return id;
    }


    public void buscador(){

        try {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();

            String sql="SELECT "+Utilidades.CAMPO_BUSCADOR+" FROM "+Utilidades.TABLA_CHECKB;
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                buscador= Integer.parseInt(cursor.getString(0));
            }
        }catch (Exception e){


        } finally {

        }



    }



    public String traerFecha(){

        String date="";

        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "Select getDate()";
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                date=rs.getString(1);
            }




        } catch (SQLException e){

        }

        return date;

    }



    public void buscarMarca(){
        listaMarca.add(null);
        listaMarca.clear();
        String sql = null;

        try {
            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            if(buscador==0){
                sql = "select DISTINCT  m.marca, numero from marcas m inner join articulo a on m.numero = a.marca  where m.marca like '%"+edtBucador.getText()+"%' and a.estilo = '"+sp2.getSelectedItem()+"' and  a.acabado = "+Principal.idAcabado+" and a.color="+Principal.idColor+"";
            }else if (buscador==1){
                sql = "select DISTINCT  m.marca, numero from marcas m inner join articulo a on m.numero = a.marca  where m.marca like '%"+edtBucador.getText()+"%' and a.estilo = '"+sp2.getSelectedItem()+"'";
            }

            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                String add=rs.getString(1);
                listaMarca.add(add);

            }

        } catch (Exception e){

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaMarca);
        spMarca.setAdapter(adapter);
        spMarca.setSelection(0);
    }


    public void inicializarMarca() {
        try {
            listaMarca.clear();
            listaCorrida.clear();
            puntos.clear();


            spCorrida.setAdapter(null);
            punto.setAdapter(null);
            limpiarCajas();


            ReinicarContadores();


            contarSp4();
            if (Principal.scannPass == true) {
                llenarSp4();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaMarca);
                spMarca.setAdapter(adapter);
                spMarca.setSelection(obtenerPosicionItem(spMarca, marcaBar));
            } else if (Principal.scannPass == false && marcaCantidad == 1) {
                llenarSp4();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaMarca);
                spMarca.setAdapter(adapter);
                spMarca.setSelection(1);


            } else if (Principal.scannPass == false && marcaCantidad > 1) {
                llenarSp4();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaMarca);
                spMarca.setAdapter(adapter);

            }

        } catch (Exception e) {
            e.getMessage();
        }
    }


}
