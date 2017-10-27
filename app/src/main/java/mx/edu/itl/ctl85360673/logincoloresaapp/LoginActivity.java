package mx.edu.itl.ctl85360673.logincoloresaapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import mx.edu.itl.ctl85360673.logincoloresaapp.clases.Singleton;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USUARIO = "mx.edu.itl.ctrl85360673.logincoloresapp.USUARIO";

    private Button   btnEntrar;
    private EditText edtUsuario, edtContrasena;
    private LinearLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar     = (Button)        findViewById ( R.id.btnEntrar       );
        edtUsuario    = (EditText)      findViewById ( R.id.edtUsuario      );
        edtContrasena = (EditText)      findViewById ( R.id.edtContrasena   );
        lay           = (LinearLayout)  findViewById ( R.id.layoutPrincipal );

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEntrarClick ( v );
            }
        });

        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getCurrentFocus();
                if(view != null){
                    InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    private void btnEntrarClick ( View v ) {
        if ( edtUsuario.getText().toString().equals ( "pma")  &&
             edtContrasena.getText().toString().equals ( "android" )   ) {
             lanzarDiscoActivity ();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder ( this );
            AlertDialog dialogo = builder.setMessage ( "Datos incorrectos. Desea intentar nuevamente ?" ).
              setPositiveButton("Intentar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish ();
                }
            }).create ();
            dialogo.show();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtUsuario.setText("");
        edtContrasena.setText("");
        edtUsuario.requestFocus();
    }

    private void lanzarDiscoActivity () {
        //Guardar el usuario y la contraseña en el Singleton
        Singleton.getInstance().setUsuario(edtUsuario.getText().toString());
        Singleton.getInstance().setContrasena(edtContrasena.getText().toString());
        Singleton.getInstance().setValores("usuario", edtUsuario.getText().toString());
        Singleton.getInstance().setValores("constraseña", edtContrasena.getText().toString());

        Intent intent = new Intent(this, DiscoActivity.class);
        startActivity(intent);
    }
}
