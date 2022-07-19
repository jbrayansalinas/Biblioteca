package red.lisgar.biblioteca.usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import red.lisgar.biblioteca.R;
import red.lisgar.biblioteca.admin.AdminLibrosDisponiblesActivity;
import red.lisgar.biblioteca.login.MainActivity;
import red.lisgar.biblioteca.login.SharePreference;

public class UsuMisLibrosActivity extends AppCompatActivity {

    TextView rolToolbar;
    TextView nombreToolbar;
    ImageView btnMas;
    ImageView imgBarra;
    SharePreference sHarePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_mis_libros);
        //TOOLBAR
        sHarePreference = new SharePreference(this);
        btnMas = findViewById(R.id.btnMas);
        rolToolbar = findViewById(R.id.rolToolbar);
        nombreToolbar = findViewById(R.id.nombreToolbar);
        imgBarra = findViewById(R.id.imgBarra);
        btnMas.setImageResource(R.drawable.ic_mas);
        imgBarra.setImageResource(R.drawable.user);
        rolToolbar.setText("Usuario");
        nombreToolbar.setText(sHarePreference.getSharePreference());

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UsuMisLibrosActivity.this, "Despues lo termino", Toast.LENGTH_LONG).show();

            }
        });
    }
    private void verLibrosDisponibles() {
        Intent intent3 = new Intent(this, AdminLibrosDisponiblesActivity.class);
        startActivity(intent3);
    }
}