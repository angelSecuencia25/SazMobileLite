package com.secuencia.saz.sazmobilelite;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.secuencia.saz.sazmobilelite.Modelo.ModeloEmpresa;
import com.secuencia.saz.sazmobilelite.Modelo.ModeloUsuario;
import com.secuencia.saz.sazmobilelite.conexion.ConexionBDCliente;
import com.secuencia.saz.sazmobilelite.conexion.ConexionSQLiteHelper;
import com.secuencia.saz.sazmobilelite.conexion.ConexionSqlServer;
import com.secuencia.saz.sazmobilelite.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.CAMERA;

public class Principal extends AppCompatActivity {

    Button button;
    EditText edtContraseña, edtCorreo;
    public String res;
    public static Boolean similarPass=false;

    public static  String bar="";

    public static  boolean scannPass=false;
    public static boolean passConsulta=false;
    public static boolean busqueda2=false;
    public static String punto="";
    public static String estilo="";
    public static int location = 0;
    CheckBox check;
    public RequestQueue queue;
    public String usuario = "";
    Boolean mensaje=false;
    String asistencia;
    int checkDisp=0;
    public static int hiloCantidad = 0;
    String fechaContrato;
    String pass = " ";
    int verificador;
    public String empresa = null;
    String Password;
    String idUsuario;
    String fechaHoy;
    int filas=0;
    Boolean membrecia=false;

    public static String idCorrida=null, idAcabado=null,idColor=null;

    String nombreUsuario;
    ModeloEmpresa me;
    ModeloUsuario mu;

    ConexionSqlServer conex = new ConexionSqlServer();
    ConexionBDCliente bdc = new ConexionBDCliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        check = (CheckBox) findViewById(R.id.check);

        edtContraseña = (EditText) findViewById(R.id.edtContraseña);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);



        button = (Button) findViewById(R.id.button);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());

       //this.deleteDatabase("db tienda");

        //consultarBuscador();

        Date date=new Date();
       fechaHoy=dateFormat.format(date);
        PermisosCamara();
        mostrarDatos();
        queue = Volley.newRequestQueue(this);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast toast = new Toast(Principal.this);
                pl.droidsonroids.gif.GifImageView view = new pl.droidsonroids.gif.GifImageView(Principal.this);
                view.setImageResource(R.drawable.loading);
                toast.setView(view);
                toast.show();

                inicializarDatos();


                    consulta();


            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

        }

        return true;

    }

    public void consulta()
    {
        try {
            final String url = "http://secuenciaonline.com/svcCadEncrypt/cadEncrypt.svc/encjson/" + pass;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Password = response.get("EncriptaJSONResult").toString();

                        Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_LONG).show();
                        Statement st = conex.conexionBD().createStatement();
                        String query = "select  Empresa from Rlogin where Usuario= '" + usuario + "' and Clave= '" + Password + "' and Activo=1 and suspendido=0";
                        ResultSet rs = st.executeQuery(query);


                        while (rs.next()) {
                            empresa = (rs.getString(1));
                            obtenerLineaConexion();
                            getIdUsuario();
                            asistencia();


                            if (!empresa.isEmpty()) {

                                String predeterminada = "SALIDA";


                                String entrada = asistencia;

                                String aux = "";


                                if (asistencia != null) {
                                    if (predeterminada.length() == entrada.length()) {

                                        for (int i = 0; i < predeterminada.length(); i++) {


                                            if (predeterminada.charAt(i) == entrada.charAt(i)) {

                                                aux += predeterminada.charAt(i);
                                            }
                                        }


                                        if (aux.equals(predeterminada)) {
                                            verificar();
                                            verificarRenta();

                                            if (fechaContrato != null) {
                                                compararFechaReta();
                                            }



                                            if (verificador == 1 && membrecia == true) {
                                                mismoDispositivo();

                                                if (check.isChecked() == true) {
                                                    recordarDatos(usuario, pass);
                                                } else if (check.isChecked() == false) {
                                                    noRecordar();
                                                }

                                                setDateDivice();
                                                checkDispositivo();
                                                if (checkDisp > 0) {
                                                    updateDispositivo();

                                                } else if (checkDisp == 0) {
                                                    DispositivoActual();
                                                }

                                                compararFechaReta();
                                                if (mensaje == true) {
                                                    android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(Principal.this);
                                                    alerta.setMessage("Este usuario estaba siendo usado en otro dispositivo por lo que se cerrará la sesión anterior.")
                                                            .setCancelable(false).setIcon(R.drawable.aviso)
                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    Toast.makeText(Principal.this, "Bienvenido", Toast.LENGTH_LONG).show();
                                                                    // Toast.makeText(Principal.this,"Bienvenido", Toast.LENGTH_LONG).show();
                                                                    Intent intent = new Intent(getApplicationContext(), ActPrincipal.class);
                                                                    intent.putExtra("Empresa", empresa);
                                                                    intent.putExtra("Usuario", usuario);
                                                                    startActivity(intent);

                                                                }
                                                            });

                                                    android.app.AlertDialog titulo = alerta.create();
                                                  //  titulo.setTitle("Aviso");
                                                  //   titulo.show();


                                                } else {
                                                    Intent intent = new Intent(getApplicationContext(), ActPrincipal.class);
                                                    intent.putExtra("Empresa", empresa);
                                                    intent.putExtra("Usuario", usuario);
                                                    startActivity(intent);
                                                }


                                            } else if (filas == 0) {
                                                iniciarPrueba();
                                                consulta();

                                            }  else if(verificador==0 && membrecia==true) {

                                                AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                                dialogo.setTitle("Aviso ").setIcon(R.drawable.aviso);
                                                dialogo.setMessage("Este usuario se encuentra inactivo favor de contactarte con ayuda");

                                                dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {


                                                    }
                                                });

                                                dialogo.show();
                                            }else {
                                                AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                                dialogo.setTitle("Aviso");
                                                dialogo.setMessage("Tu periodo de prueba ha expirado o ha sido suspendido por falta de pago , Comunicate con saz para ReConectar");

                                                dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {


                                                    }
                                                });

                                                dialogo.show();
                                            }
                                        } else {
                                            verificar();
                                            verificarRenta();
                                            if (fechaContrato != null) {
                                                compararFechaReta();
                                            }

                                            if (verificador == 1 && membrecia == true) {
                                                mismoDispositivo();
                                                // Toast.makeText(Principal.this,"Bienvenido", Toast.LENGTH_LONG).show();

                                                if (check.getLinksClickable() == true) {
                                                    recordarDatos(usuario, pass);
                                                } else if (check.getLinksClickable() == false) {
                                                    noRecordar();
                                                }
                                                setDateDivice();
                                                checkDispositivo();
                                                if (checkDisp > 0) {
                                                    updateDispositivo();

                                                } else if (checkDisp == 0) {
                                                    DispositivoActual();
                                                }
                                                compararFechaReta();
                                                if (mensaje == true) {
                                                    android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(Principal.this);
                                                    alerta.setMessage("Este usuario estaba siendo usado en otro dispositivo por lo que se cerrará la sesión anterior.")
                                                            .setCancelable(false).setIcon(R.drawable.aviso)
                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    Toast.makeText(Principal.this, "Bienvenido", Toast.LENGTH_LONG).show();
                                                                    // Toast.makeText(Principal.this,"Bienvenido", Toast.LENGTH_LONG).show();
                                                                    Intent intent = new Intent(getApplicationContext(), ActPrincipal.class);
                                                                    intent.putExtra("Empresa", empresa);
                                                                    intent.putExtra("Usuario", usuario);
                                                                    startActivity(intent);

                                                                }
                                                            });

                                                    android.app.AlertDialog titulo = alerta.create();
                                                    titulo.setTitle("Aviso");
                                                    titulo.show();


                                                } else {
                                                    Intent intent = new Intent(getApplicationContext(), menu.class);
                                                    intent.putExtra("Empresa", empresa);
                                                    intent.putExtra("Usuario", usuario);
                                                    startActivity(intent);
                                                }


                                            } else if (filas == 0) {
                                                iniciarPrueba();
                                                consulta();

                                            }  else if(verificador==0 && membrecia==true) {

                                                AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                                dialogo.setTitle("Aviso ").setIcon(R.drawable.aviso);
                                                dialogo.setMessage("Este usuario se encuentra inactivo favor de contactarte con ayuda");

                                                dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {


                                                    }
                                                });

                                                dialogo.show();
                                            }else {
                                                AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                                dialogo.setTitle("Upss :(");
                                                dialogo.setMessage("Tu periodo de prueba ha expirado o ha sido suspendido por falta de pago , Comunicate con saz para ReConectar");

                                                dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {


                                                    }
                                                });

                                                dialogo.show();
                                            }

                                        }


                                    } else {
                                        verificar();
                                        verificarRenta();
                                        if (fechaContrato != null) {
                                            compararFechaReta();
                                        }
                                        if (verificador == 1 && membrecia == true) {
                                            mismoDispositivo();

                                            if (check.isChecked() == true) {
                                                recordarDatos(usuario, pass);
                                            } else if (check.isChecked() == false) {
                                                noRecordar();
                                            }
                                            setDateDivice();
                                            checkDispositivo();
                                            if (checkDisp > 0) {
                                                updateDispositivo();
                                            } else if (checkDisp == 0) {

                                                DispositivoActual();
                                            }
                                            compararFechaReta();
                                            if (mensaje == true) {
                                                android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(Principal.this);
                                                alerta.setMessage("Este usuario estaba siendo usado en otro dispositivo por lo que se cerrará la sesión anterior.")
                                                        .setCancelable(false).setIcon(R.drawable.aviso)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Toast.makeText(Principal.this, "Bienvenido", Toast.LENGTH_LONG).show();
                                                                // Toast.makeText(Principal.this,"Bienvenido", Toast.LENGTH_LONG).show();
                                                                Intent intent = new Intent(getApplicationContext(), ActPrincipal.class);
                                                                intent.putExtra("Empresa", empresa);
                                                                intent.putExtra("Usuario", usuario);
                                                                startActivity(intent);

                                                            }
                                                        });

                                                android.app.AlertDialog titulo = alerta.create();
                                                titulo.setTitle("Aviso");
                                                titulo.show();


                                            } else {
                                                Intent intent = new Intent(getApplicationContext(), menu.class);
                                                intent.putExtra("Empresa", empresa);
                                                intent.putExtra("Usuario", usuario);
                                                startActivity(intent);
                                            }

                                        } else if (filas == 0) {
                                            iniciarPrueba();
                                            consulta();

                                        }  else if(verificador==0 && membrecia==true) {

                                            AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                            dialogo.setTitle("Aviso ").setIcon(R.drawable.aviso);
                                            dialogo.setMessage("Este usuario se encuentra inactivo favor de contactarte con ayuda");

                                            dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {


                                                }
                                            });

                                            dialogo.show();
                                        }else {
                                            AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                            dialogo.setTitle("Upss :(").setIcon(R.drawable.aviso);
                                            dialogo.setMessage("Tu periodo de prueba ha expirado o ha sido suspendido por falta de pago , Comunicate con saz para ReConectar");

                                            dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {


                                                }
                                            });

                                            dialogo.show();
                                        }
                                    }
                                } else {
                                    verificar();
                                    verificarRenta();
                                    if (fechaContrato != null) {
                                        compararFechaReta();
                                    }
                                    if (verificador == 1 && membrecia == true) {
                                        mismoDispositivo();
                                        Toast.makeText(Principal.this, "Bienvenido", Toast.LENGTH_LONG).show();
                                        // Toast.makeText(Principal.this,"Bienvenido", Toast.LENGTH_LONG).show();

                                        if (check.isChecked() == true) {
                                            recordarDatos(usuario, pass);
                                        } else if (check.isChecked() == false) {
                                            noRecordar();

                                        }
                                        setDateDivice();
                                        checkDispositivo();
                                        if (checkDisp > 0) {
                                            updateDispositivo();
                                        } else if (checkDisp == 0) {

                                            DispositivoActual();
                                        }
                                        compararFechaReta();
                                        if (mensaje == true) {
                                            android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(Principal.this);
                                            alerta.setMessage("Este usuario estaba siendo usado en otro dispositivo por lo que se cerrará la sesión anterior.")
                                                    .setCancelable(false).setIcon(R.drawable.aviso)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Toast.makeText(Principal.this, "Bienvenido", Toast.LENGTH_LONG).show();
                                                            // Toast.makeText(Principal.this,"Bienvenido", Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(getApplicationContext(), ActPrincipal.class);
                                                            intent.putExtra("Empresa", empresa);
                                                            intent.putExtra("Usuario", usuario);
                                                            startActivity(intent);

                                                        }
                                                    });

                                            android.app.AlertDialog titulo = alerta.create();
                                            titulo.setTitle("Aviso");
                                            titulo.show();


                                        } else {
                                            Intent intent = new Intent(getApplicationContext(), ActPrincipal.class);
                                            intent.putExtra("Empresa", empresa);
                                            intent.putExtra("Usuario", usuario);
                                            startActivity(intent);
                                        }

                                    } else if (filas == 0) {
                                        iniciarPrueba();
                                        consulta();

                                    } else if(verificador==0 && membrecia==true) {

                                        AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                        dialogo.setTitle("Aviso ").setIcon(R.drawable.aviso);
                                        dialogo.setMessage("Este usuario se encuentra inactivo favor de contactarte con ayuda");

                                        dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {


                                            }
                                        });

                                        dialogo.show();
                                    }else {


                                        AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
                                        dialogo.setTitle("Aviso ").setIcon(R.drawable.aviso);
                                        dialogo.setMessage("Tu periodo de prueba ha expirado o ha sido suspendido por falta de pago , Comunicate con saz para ReConectar");

                                        dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {


                                            }
                                        });

                                        dialogo.show();
                                    }
                                }

                            }

                        }

                        if (empresa == null) {
                            Toast.makeText(Principal.this, "Error verifica tus datos....!!!", Toast.LENGTH_LONG).show();

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Principal.this, "Error", Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(Principal.this, " Error...!!! Verifica tus Datos ", Toast.LENGTH_LONG).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override//Obtiene el error al no encontrar el resultado que se pide
                public void onErrorResponse(VolleyError error) {

                }
            }
            );
            queue.add(request);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Hay un problema con la conexion",Toast.LENGTH_LONG).show();
        }
    }

    public void noRecordar() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db tienda", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();


        db.execSQL("DELETE FROM LOGIN");


    }

    public void inicializarDatos() {
        pass = String.valueOf(edtContraseña.getText());
        usuario = edtCorreo.getText().toString();
    }

    public void obtenerLineaConexion() {


        me = new ModeloEmpresa();


        {
            try {
                Statement st = conex.conexionBD().createStatement();
                String query = "select  Server, Usuariosvr,PassSvr, basededatos, empresa,idEmpresa from logins where idEmpresa= " + empresa + " and status=1 and isnull(borrado,0)=0  ";
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {


                    String server = verificarServer(rs.getString(1));
                    String usuario = rs.getString(2);
                    String pass = rs.getString(3);
                    String base = rs.getString(4);
                    String empresa = rs.getString(5);
                    String idEmepresa=rs.getString(6);

                    me.setServer(server);
                    me.setUsuario(usuario);
                    me.setPass(pass);
                    me.setBase(base);
                    me.setEmpresa(empresa);
                    me.setIdEmpresa(idEmepresa);


                }


            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error en la linea de conexion", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public String verificarServer(String server) {
        String contenedor = "";
        String[] contServer = server.split("");
        try {
            for (int x = 0; x < contServer.length; x++) {
                if (!contServer[x].isEmpty()) {

                    String predeterminada = ",";


                    String entrada = contServer[x];


                    String aux = "";


                    if (predeterminada.length() == entrada.length()) {

                        for (int i = 0; i < predeterminada.length(); i++) {


                            if (predeterminada.charAt(i) == entrada.charAt(i)) {

                                aux += predeterminada.charAt(i);
                            }
                        }


                        if (aux.equals(predeterminada)) {
                            contServer[x] = ":";
                        } else {


                        }


                    } else {

                    }


                }

            }

            for (int i = 0; i < contServer.length; i++) {

                contenedor += contServer[i];

            }
        } catch (Exception e) {
            e.getMessage();
        }
        String verificado = "";
        verificado = contenedor;
        return verificado;
    }

    public void recordarDatos(String usuario, String pass) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db tienda", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();


        db.execSQL("INSERT INTO  login (usuario,contraseña) VALUES('" + usuario + "', '" + pass + "')");


    }

    public void getIdUsuario() {

        try {
            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String query = "select numero, nombre from empleado where [user]='" + edtCorreo.getText() + "'";
            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {


                idUsuario = rs.getString(1);
                nombreUsuario = rs.getString(2);


                mu.setCorreo(edtCorreo.getText().toString());
                mu.setNombre(nombreUsuario);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error al traer id usuario", Toast.LENGTH_SHORT).show();
        }


    }

    public void mostrarDatos() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        String sql = "SELECT usuario, contraseña FROM login  ORDER BY id ASC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            String usu = cursor.getString(0);
            String contra = cursor.getString(1);

            if (!usu.isEmpty()) {
                edtCorreo.setText(usu);
                edtContraseña.setText(contra);
                check.setChecked(true);
            }


        }
    }

    public void asistencia() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Date date = new Date();

        String fecha = dateFormat.format(date);


        try {
            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select top 1 tipo from logdia where idEmpleado=" + idUsuario + " and CONVERT(nCHAR(8), fecha , 112)='" + fecha + "' order by fecha desc";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {


                asistencia = rs.getString(1);

            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error al verificar asistencia", Toast.LENGTH_SHORT).show();
        }

    }


    public void PermisosCamara() {
        if ((checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED)) {

        }

        if (shouldShowRequestPermissionRationale(CAMERA)) {
            cargarDialogoRecomendacion();
        } else {
            requestPermissions(new String[]{CAMERA}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    private void cargarDialogoRecomendacion() {

        AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
        dialogo.setTitle("Permisos desactivados ");
        dialogo.setMessage("Debe aceptar los permisos para el corecto funcionamiento de SazMobile app");

        dialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{CAMERA}, 100);

            }
        });

        dialogo.show();
    }


    public void verificarRenta() {


        try {
            Statement st =  conex.conexionBD().createStatement();
            String sql = " SELECT isnull(smapp,0), fechaSmApp  FROM rlogin WHERE usuario='" + usuario + "' ";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                verificador = rs.getInt(1);
                fechaContrato=rs.getString(2);

            }


        } catch (SQLException e) {
            e.getMessage();
            Toast.makeText(getApplicationContext(), "No sé puede verificar la renta", Toast.LENGTH_SHORT).show();
        }

    }


    public void iniciarPrueba() {
        try {
            Statement st =  conex.conexionBD().createStatement();
            String sql = "update Rlogin set smApp=1 , fechaSmApp=DATEADD(day,30,GETDATE()) where Empresa="+empresa+"";
            st.executeUpdate(sql);



        } catch (SQLException e) {
            e.getMessage();
            Toast.makeText(getApplicationContext(), "No sé puede iniciar la prueba", Toast.LENGTH_SHORT).show();
            }
    }

    public void setDateDivice(){

        String modelo = Build.MODEL;
        String serie = Build.MANUFACTURER;
        String marca = Build.ID;

        try{
            Statement st= conex.conexionBD().createStatement();
            String sql="insert into smAppDispositivos (mail,idEmpresa,fecha,idDisp,nombreDisp,app,llave)values('"+usuario+"',"+empresa+",GETDATE(),'"+marca+"','"+serie+" "+ modelo+"',1,NEWID());";
            st.executeUpdate(sql);
        }catch (Exception e ){
            e.getMessage();
            Toast.makeText(getApplicationContext(), "No sé puede obtener datos del dispositivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void compararFechaReta() throws ParseException {


        String[] fechaCAux=fechaContrato.split("");
        String[] fechaHAux=fechaHoy.split("");



      int añoC=Integer.parseInt(fechaCAux[1]+fechaCAux[2]+fechaCAux[3]+fechaCAux[4]);
      int mesC=Integer.parseInt(fechaCAux[6]+fechaCAux[7]);
      int diaC=Integer.parseInt(fechaCAux[9]+fechaCAux[10]);



      String hoy=fechaHAux[1]+fechaHAux[2]+fechaHAux[3]+fechaHAux[4]+"-"+fechaHAux[5]+fechaHAux[6]+"-"+fechaHAux[7]+fechaHAux[8];



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateContrato = sdf.parse(fechaContrato);
        Date dateActual = sdf.parse(hoy);

              if(dateContrato.compareTo(dateActual)>0){

                  membrecia=true;
              }else{
                  membrecia=false;
              }





    }

    public void verificar(){
        try{
            Statement st= conex.conexionBD().createStatement();
            String sql="select top 1 1 from rlogin where Empresa="+empresa+" and smApp=1;";
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                filas=rs.getInt(1);
            }

        }catch (Exception e ){
            e.getMessage();
            Toast.makeText(getApplicationContext(), "No sé puede obtener datos del dispositivo", Toast.LENGTH_SHORT).show();
        }
    }


    public void DispositivoActual(){

        String modelo = Build.MODEL;
        String serie = Build.MANUFACTURER;
        String marca = Build.ID;
        try{
            Statement st= conex.conexionBD().createStatement();
            String sql="insert into smAppAccesos (mail,idEmpresa,ultimoAcceso,idDisp,nombreDisp,activo,app,llave)values('"+usuario+"',"+empresa+",GETDATE(),'"+marca+"','"+serie+" "+ modelo+"',1,1,NEWID());";
            st.executeUpdate(sql);
        }catch (Exception e ){
            e.getMessage();
            Toast.makeText(getApplicationContext(), "No sé puede obtener datos del dispositivo", Toast.LENGTH_SHORT).show();
        }
    }

public void checkDispositivo(){
    try{
        Statement st= conex.conexionBD().createStatement();
        String sql="SELECT isnull(id,0) FROM smAppAccesos where mail='"+usuario+"' and app=1";
        ResultSet rs=st.executeQuery(sql);

        while(rs.next()){
         checkDisp=rs.getInt(1);
        }
    }catch (Exception e ){
        e.getMessage();
        Toast.makeText(getApplicationContext(), "No sé puede obtener datos del dispositivo", Toast.LENGTH_SHORT).show();
    }
}

public void updateDispositivo(){
    String modelo = Build.MODEL;
    String serie = Build.MANUFACTURER;
    String marca = Build.ID;
    try{
        Statement st= conex.conexionBD().createStatement();
        String sql="  update smAppAccesos set ultimoAcceso=GETDATE(),idDisp='"+marca+"-"+serie+"-"+modelo+"',nombreDisp='"+serie+" "+modelo+"', activo=1, app=1 where mail='"+usuario+"' and app=1";
        st.executeUpdate(sql);



    }catch (Exception e ){
        e.getMessage();
        Toast.makeText(getApplicationContext(), "No sé puede actualizar el dispositivo", Toast.LENGTH_SHORT).show();
    }
}


    public void mismoDispositivo(){
        String dispositivo="";
        int app=0;
        String modelo = Build.MODEL;
        String serie = Build.MANUFACTURER;
        String marca = Build.ID;
        try{
            Statement st= conex.conexionBD().createStatement();
            String sql=" SELECT idDisp,app FROM smAppAccesos where mail='"+usuario+"' and app=1  ";
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                dispositivo=rs.getString(1);
                app=rs.getInt(2);

            }

        }catch (Exception e ){
            e.getMessage();
            Toast.makeText(getApplicationContext(), "Error 323 al obtener datos del dispositivo ", Toast.LENGTH_SHORT).show();
        }

        if (!dispositivo.isEmpty()) {

            String predeterminada = dispositivo;

            String entrada = marca+"-"+serie+"-"+modelo;

            String aux = "";


            if (entrada!= null) {
                if (predeterminada.length() == entrada.length()) {

                    for (int i = 0; i < predeterminada.length(); i++) {


                        if (predeterminada.charAt(i) == entrada.charAt(i)) {

                            aux += predeterminada.charAt(i);
                        }
                    }


                    if (aux.equals(predeterminada)) {

                            mensaje=false;


                    } else {

                        if(app==1){
                            mensaje=true;
                        }else{
                            mensaje=false;
                        }

                    }

                } else {

                    if(app==1){
                        mensaje=true;
                    }else{
                        mensaje=false;
                    }

                }

            }

        }

    }


    public void consultarBuscador(){
        int buscador=0;
        try {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();

            String sql="SELECT "+ Utilidades.CAMPO_BUSCADOR2+" FROM "+Utilidades.TABLA_CHECKB;
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                buscador= Integer.parseInt(cursor.getString(0));
            }

            if(buscador==1){
                busqueda2=true;
            }
        }catch (Exception e){

            Toast toast = Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG);
            TextView x = (TextView) toast.getView().findViewById(android.R.id.message);
            x.setTextColor(Color.WHITE); toast.show();

        } finally {

        }

    }
}


