package red.lisgar.biblioteca.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import red.lisgar.biblioteca.R;
import red.lisgar.biblioteca.login.SharePreference;

public class AdminLibrosPrestadosActivity extends AppCompatActivity {

    TextView rolToolbar;
    TextView nombreToolbar;
    ImageView btnMas;
    SharePreference sHarePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_libros_prestados);

        //TOOLBAR
        sHarePreference = new SharePreference(this);
        rolToolbar = findViewById(R.id.rolToolbar);
        btnMas = findViewById(R.id.btnMas);
        nombreToolbar = findViewById(R.id.nombreToolbar);
        rolToolbar.setText("Administrador");
        nombreToolbar.setText(sHarePreference.getSharePreference());

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verLibrosDisponibles();
            }
        });
    }
    private void verLibrosDisponibles() {
        Intent intent3 = new Intent(this, AdminLibrosDisponiblesActivity.class);
        startActivity(intent3);
    }
}