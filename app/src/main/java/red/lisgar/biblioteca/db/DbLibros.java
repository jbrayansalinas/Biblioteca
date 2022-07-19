package red.lisgar.biblioteca.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import red.lisgar.biblioteca.entidades.LibrosDisponibles;
import red.lisgar.biblioteca.entidades.Usuarios;
import red.lisgar.biblioteca.login.SharePreference;

public class DbLibros extends DbHelperAdmin{
    Context context;
    SharePreference sHarePreference;
    DbHelperAdmin dbHelper;

    public DbLibros(@Nullable Context context) {
        super(context);
        this.context = context;
        sHarePreference = new SharePreference(context);
    }
    public long insertaLibro(LibrosDisponibles librosDisponibles) {

        long id= 0;

        try {
            dbHelper = new DbHelperAdmin(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(COLUMN_LIBRO_NOMBRE, librosDisponibles.getTitulo());
            values.put(COLUMN_LIBRO_AUTOR, librosDisponibles.getAutor());
            values.put(COLUMN_LIBRO_CANTIDAD, librosDisponibles.getCantidad());
            values.put(COLUMN_LIBRO_URL, librosDisponibles.getUrl());
            values.put(COLUMN_LIBRO_IMAGEN, librosDisponibles.getImgResource());
            values.put(COLUMN_LIBRO_DESCRIPCION, librosDisponibles.getDescripcion());
            values.put(COLUMN_LIBRO_SP, sHarePreference.getSharePreference());


            id = db.insert(TABLE_LIBRO, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<LibrosDisponibles> mostrarLibros(){
        dbHelper = new DbHelperAdmin((context));
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<LibrosDisponibles> listaLibros = new ArrayList<>();
        LibrosDisponibles librosDisponibles;
        Cursor cursorLibro;

        cursorLibro = db.rawQuery("SELECT * FROM " + TABLE_LIBRO + " WHERE " + COLUMN_LIBRO_SP + " = '" + sHarePreference.getSharePreference()+"'", null);

        if (cursorLibro.moveToFirst()){
            do {
                librosDisponibles = new LibrosDisponibles();
                librosDisponibles.setId(cursorLibro.getInt(0));
                librosDisponibles.setTitulo(cursorLibro.getString(1));
                librosDisponibles.setAutor(cursorLibro.getString(2));
                librosDisponibles.setCantidad(cursorLibro.getString(3));
                librosDisponibles.setUrl(cursorLibro.getString(4));
                librosDisponibles.setImgResource(cursorLibro.getString(5));
                librosDisponibles.setDescripcion(cursorLibro.getString(6));
                listaLibros.add(librosDisponibles);
            } while (cursorLibro.moveToNext());
        }
        cursorLibro.close();

        return listaLibros;
    }
}
