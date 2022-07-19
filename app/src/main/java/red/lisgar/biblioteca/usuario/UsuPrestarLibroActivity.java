package red.lisgar.biblioteca.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import red.lisgar.biblioteca.R;
import red.lisgar.biblioteca.login.SharePreference;

public class UsuPrestarLibroActivity extends AppCompatActivity {

    TextView rolToolbar;
    TextView nombreToolbar;
    ImageView btnMas;
    ImageView imgBarra;
    SharePreference sHarePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_prestar_libro);
        //TOOLBAR
        sHarePreference = new SharePreference(this);
        rolToolbar = findViewById(R.id.rolToolbar);
        btnMas = findViewById(R.id.btnMas);
        imgBarra = findViewById(R.id.imgBarra);
        nombreToolbar = findViewById(R.id.nombreToolbar);
        imgBarra.setImageResource(R.drawable.user);
        rolToolbar.setText("Usuario");
        nombreToolbar.setText(sHarePreference.getSharePreference());

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verLibrosDisponibles();
            }
        });
    }
    private void verLibrosDisponibles() {
        Intent intent3 = new Intent(this, UsuMisLibrosActivity.class);
        startActivity(intent3);
    }
}