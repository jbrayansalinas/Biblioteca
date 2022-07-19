package red.lisgar.biblioteca.admin;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import red.lisgar.biblioteca.R;
import red.lisgar.biblioteca.adaptadores.ListaLibrosDisponiblesAdapter;
import red.lisgar.biblioteca.db.DbAdmin;
import red.lisgar.biblioteca.db.DbLibros;
import red.lisgar.biblioteca.entidades.Admin;
import red.lisgar.biblioteca.entidades.LibrosDisponibles;
import red.lisgar.biblioteca.login.MainActivity;
import red.lisgar.biblioteca.login.SharePreference;

public class AdminLibrosDisponiblesActivity extends AppCompatActivity {

    //LAYOUT
    RecyclerView rvLista;
    ListaLibrosDisponiblesAdapter adapter;
    ArrayList<LibrosDisponibles> listItem;
    TextView rolToolbar;
    TextView nombreToolbar;
    ImageView btnMas;

    //CLASES
    DbLibros dbLibros;
    PopupMenu popupMenu;
    SharePreference sHarePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_libros_disponibles);

        //TOOLBAR
        sHarePreference = new SharePreference(this);
        btnMas = findViewById(R.id.btnMas);
        rolToolbar = findViewById(R.id.rolToolbar);
        nombreToolbar = findViewById(R.id.nombreToolbar);
        btnMas.setImageResource(R.drawable.ic_mas);
        rolToolbar.setText("Administrador");
        nombreToolbar.setText(sHarePreference.getSharePreference());

        //RECYCLEVIEW
        rvLista = findViewById(R.id.recyclerId);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvLista.setLayoutManager(manager);
        dbLibros = new DbLibros(AdminLibrosDisponiblesActivity.this);
        listItem = new ArrayList<>();
        adapter = new ListaLibrosDisponiblesAdapter(dbLibros.mostrarLibros(), AdminLibrosDisponiblesActivity.this);
        rvLista.setAdapter(adapter);

        //MENU POPUP
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu = new PopupMenu(AdminLibrosDisponiblesActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_admin_disponibles, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.prestados:
                                verAdminPrestados();
                                break;
                            case R.id.agregar:
                                agregar();
                                break;
                            case R.id.salir:
                                salir();
                                break;
                            default:
                                return AdminLibrosDisponiblesActivity.super.onOptionsItemSelected(menuItem);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void verAdminPrestados(){
        Intent intent = new Intent(this, AdminLibrosPrestadosActivity.class);
        startActivity(intent);
    }
    private void agregar(){
        Intent intent2 = new Intent(this, AdminAgregarLibroActivity.class);
        startActivity(intent2);
    }
    private void salir(){
        Intent intent3 = new Intent(this, MainActivity.class);
        startActivity(intent3);
    }
}