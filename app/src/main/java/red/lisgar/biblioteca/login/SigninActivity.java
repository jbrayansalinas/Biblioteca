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
import red.lisgar.biblioteca.db.DbAdmin;
import red.lisgar.biblioteca.db.DbUsuarios;
import red.lisgar.biblioteca.entidades.Usuarios;
import red.lisgar.biblioteca.usuario.UsuMisLibrosActivity;

public class SigninActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtCorreosignin;
    EditText txtTelefono;
    EditText txtDireccion;
    EditText txtContrasena;
    Button btnRegistrarse;
    Button btnIniciarSesion;
    Usuarios usuarios;
    SharePreference sHarePreference;
    DbAdmin dbAdmin;
    DbUsuarios dbUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreosignin = findViewById(R.id.txtCorreosignin);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtContrasena = findViewById(R.id.txtContrasena);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        sHarePreference = new SharePreference(this);
        usuarios = new Usuarios();
        dbAdmin = new DbAdmin(this);
        dbUsuarios = new DbUsuarios(this);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = txtNombre.getText().toString().trim().toUpperCase();
                String Correo = txtCorreosignin.getText().toString().trim().toUpperCase();
                String Telefono = txtTelefono.getText().toString().trim().toUpperCase();
                String Direccion = txtDireccion.getText().toString().trim().toUpperCase();
                String Contrasena = txtContrasena.getText().toString().trim().toUpperCase();

                //OBLIGATORIEDAD DE TODOS LOS CAMPOS
                if (TextUtils.isEmpty(Nombre) || TextUtils.isEmpty(Correo) || TextUtils.isEmpty(Telefono) || TextUtils.isEmpty(Direccion) || TextUtils.isEmpty(Contrasena)) {
                    Toast.makeText(SigninActivity.this, "RELLENE TODOS LO CAMPOS", Toast.LENGTH_LONG).show();
                } else {
                    //QUE NO ESTÉ CREADO
                    boolean checkadmin = dbAdmin.validarAdminSignin(Correo);
                    boolean checkCorreopass = dbUsuarios.entrarUsuarioContrasenaSignin(Correo);
                    if (checkadmin || checkCorreopass){
                        Toast.makeText(SigninActivity.this, "CORREO NO DISPONIBLE", Toast.LENGTH_LONG).show();
                    } else {
                        if (Telefono.length()==10) {
                            Toast.makeText(SigninActivity.this, "EL TELEFONO DEBE TENER 10 CARACTERES", Toast.LENGTH_LONG).show();
                        } else {
                            //SE REGISTRA
                            DbUsuarios dbUsuarios = new DbUsuarios(SigninActivity.this);
                            usuarios.setNombre(Nombre);
                            usuarios.setCorreo(Correo);
                            usuarios.setTelefono(Telefono);
                            usuarios.setDireccion(Direccion);
                            usuarios.setContrasena(Contrasena);
                            long id = dbUsuarios.insertarUsuario(usuarios);
                            if (id > 0) {
                                Toast.makeText(SigninActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                                verMain();
                            } else {
                                Toast.makeText(SigninActivity.this, "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verMain();
            }
        });
    }

    private void verMain() {
        Intent intent3 = new Intent(this, MainActivity.class);
        startActivity(intent3);
    }
}