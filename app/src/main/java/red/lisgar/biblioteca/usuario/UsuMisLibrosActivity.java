package red.lisgar.biblioteca.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    PopupMenu popupMenu;

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

        //MENU POPUP
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu = new PopupMenu(UsuMisLibrosActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_usuario, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.salirMenu:
                                salir();
                                break;
                            default:
                                return UsuMisLibrosActivity.super.onOptionsItemSelected(menuItem);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
    private void verLibrosDisponibles() {
        Intent intent2 = new Intent(this, AdminLibrosDisponiblesActivity.class);
        startActivity(intent2);
    }
    private void salir() {
        Intent intent3 = new Intent(this, MainActivity.class);
        startActivity(intent3);
    }
}