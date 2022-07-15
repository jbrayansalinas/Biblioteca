package red.lisgar.biblioteca.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.security.PublicKey;

import red.lisgar.biblioteca.entidades.Usuarios;
import red.lisgar.biblioteca.login.SharePreference;

public class DbUsuarios extends DbHelperAdmin{
    Context context;
    SharePreference sharePreference;

    public DbUsuarios(@Nullable Context context){
        super(context);
        this.context = context;
        sharePreference = new SharePreference(context);
    }

    public long insertarUsuario(Usuarios usuarios) {

        long id= 0;

        try {
            DbHelperAdmin dbHelper = new DbHelperAdmin(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(COLUMN_USUARIO_NOMBRE, usuarios.getNombre());
            values.put(COLUMN_USUARIO_TELEFONO, usuarios.getTelefono());
            values.put(COLUMN_USUARIO_CORREO, usuarios.getCorreo());
            values.put(COLUMN_USUARIO_DIRECCION, usuarios.getDireccion());
            values.put(COLUMN_USUARIO_CONTRASENA, usuarios.getContrasena());


            id = db.insert(TABLE_USUARIO, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        return id;

    }
    public boolean entrarUsuarioContrasena(String correo_electronico, String contrasena) {
        DbHelperAdmin dbHelper = new DbHelperAdmin(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_USUARIO + " WHERE " + COLUMN_USUARIO_CORREO + " =? AND " + COLUMN_USUARIO_CONTRASENA + " =?",new String[] {correo_electronico,contrasena});
        if (cursorUsuario.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean entrarUsuarioContrasenaSignin(String correo_electronico) {
        DbHelperAdmin dbHelper = new DbHelperAdmin(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_USUARIO + " WHERE " + COLUMN_USUARIO_CORREO + " =? ",new String[] {correo_electronico});
        if (cursorUsuario.getCount()>0)
            return true;
        else
            return false;
    }
}