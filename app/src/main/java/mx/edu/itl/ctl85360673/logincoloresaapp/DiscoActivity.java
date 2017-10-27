package mx.edu.itl.ctl85360673.logincoloresaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mx.edu.itl.ctl85360673.logincoloresaapp.clases.Singleton;

public class DiscoActivity extends AppCompatActivity {

    Button button8;
    String usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco);

        button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Recuperar valores desde Singleton
        usuario = Singleton.getInstance().getUsuario();
        contrasena = Singleton.getInstance().getContrasena();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Disco")
                .setMessage("Bienvenido " + usuario )
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        /*Toast.makeText( getApplicationContext(),
                        "Hola" + usuario,
                        Toast.LENGTH_LONG).show();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_disco, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int opcionSeleccionada = item.getItemId();
        if(opcionSeleccionada == R.id.mniConfig){
            Intent prefIntent = new Intent(this, PreferenciasActivity.class);
            startActivity(prefIntent);
            return true;
        }else if(opcionSeleccionada == R.id.mniSalir){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Recuperar el archivo de preferencias de la aplicación
        SharedPreferences pref = getSharedPreferences("mx.edu.itl.ctl85360673.logincoloresaapp_preferences",
                                                      MODE_PRIVATE);
        if( pref != null ){
            //Recuperar las preferencias individuales
            boolean blnMusica       = pref.getBoolean( "musica", false );
            boolean blnWifi         = pref.getBoolean( "wifi", false );
            int     intFragmentos   = Integer.valueOf(pref.getString( "fragmentos", "0" ));
            Toast.makeText(
                    getApplicationContext(),
                    "musica: "        + blnMusica     + "\n" +
                    "wifi: "          + blnWifi       + "\n" +
                    "fragmentos: "    + intFragmentos + "\n",
                    Toast.LENGTH_LONG
            ).show();

        }
    }
}
