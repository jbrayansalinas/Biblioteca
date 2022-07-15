package red.lisgar.biblioteca.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.util.PatternsCompat;

import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean validarEmail(String correo_electronico){
        DbHelperAdmin dbHelper = new DbHelperAdmin(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    if (PatternsCompat.EMAIL_ADDRESS.matcher(correo_electronico).matches()){
        return true;
        }else{
        return false;
        }
    }
    public boolean validarPass(final String contrasena){
        DbHelperAdmin dbHelper = new DbHelperAdmin(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }
}