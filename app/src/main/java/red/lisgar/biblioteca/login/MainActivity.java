package red.lisgar.biblioteca.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import red.lisgar.biblioteca.R;
import red.lisgar.biblioteca.admin.AdminLibrosDisponiblesActivity;
import red.lisgar.biblioteca.db.DbAdmin;
import red.lisgar.biblioteca.db.DbUsuarios;
import red.lisgar.biblioteca.entidades.Admin;
import red.lisgar.biblioteca.usuario.UsuMisLibrosActivity;

public class MainActivity extends AppCompatActivity {

    EditText txtCorreo;
    EditText txtpass;
    Button btnEntrar;
    Button btnSignin;
    SharePreference sHarePreference;
    Admin admin= new Admin();
    DbAdmin dbAdmin;
    DbUsuarios dbUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorreo = findViewById(R.id.txtCorreo);
        txtpass = findViewById(R.id.txtpass);
        btnEntrar = findViewById(R.id.btnAgregarLibro);
        sHarePreference = new SharePreference(this);
        dbUsuarios = new DbUsuarios(this);
        dbAdmin = new DbAdmin(this);
        btnSignin = findViewById(R.id.btnSignin);
        String CoAdmin = "johsAdmin".trim().toUpperCase();
        String NomAdmin = "Johs".trim().toUpperCase();
        String PassAdmin = "12345".trim().toUpperCase();

        //CREA EL ADMIN
        boolean checkadmin = dbAdmin.validarAdmin(CoAdmin, PassAdmin);
        if (!checkadmin) {
            admin.setCorreo(CoAdmin);
            admin.setNombre(NomAdmin);
            admin.setPass(PassAdmin);
            dbAdmin.insertarAdmin(admin);
        }
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Correo = txtCorreo.getText().toString().trim().toUpperCase();
                String pass = txtpass.getText().toString().trim().toUpperCase();
                boolean checkCorreopass = dbUsuarios.entrarUsuarioContrasena(Correo, pass);

                //OBLIGATORIEDAD DE CORREO Y CONTRASEÑA
                if (!TextUtils.isEmpty(Correo) && !TextUtils.isEmpty(pass)) {
                        //VALIDA SI ES ADMIN O USUARIO
                        if (CoAdmin.equals(Correo) && PassAdmin.equals(pass)) {
                            //ES ADMIN
                            sHarePreference.setSharedPreferences(NomAdmin);
                            ingresarAdmin();
                            limpiar();
                            }
                        else if (!CoAdmin.equals(Correo) && !PassAdmin.equals(pass)) {
                            //ES USUARIO
                            if (checkCorreopass) {
                                sHarePreference.setSharedPreferences(Correo);
                                ingresarUsuario();
                                limpiar();
                            } else {
                                Toast.makeText(MainActivity.this, "EL USUARIO NO EXISTE", Toast.LENGTH_LONG).show();
                              }
                        }else {
                            Toast.makeText(MainActivity.this, "CONTRASEÑA INCORRECTA", Toast.LENGTH_LONG).show();
                        }
                } else {
                    Toast.makeText(MainActivity.this, "RELLENE TODOS LO CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verSingin();
                limpiar();
            }
        });
    }
    private void ingresarAdmin(){
        Intent intent = new Intent(this, AdminLibrosDisponiblesActivity.class);
        startActivity(intent);
    }
    private void ingresarUsuario(){
        Intent intent2 = new Intent(this, UsuMisLibrosActivity.class);
        startActivity(intent2);
    }
    private void verSingin() {
        Intent intent3 = new Intent(this, SigninActivity.class);
        startActivity(intent3);
    }
    private void limpiar(){
        txtCorreo.setText("");
        txtpass.setText("");
    }
}