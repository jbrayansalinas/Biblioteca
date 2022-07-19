package red.lisgar.biblioteca.adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import red.lisgar.biblioteca.R;
import red.lisgar.biblioteca.entidades.Admin;
import red.lisgar.biblioteca.entidades.LibrosDisponibles;

public class ListaLibrosDisponiblesAdapter extends RecyclerView.Adapter<ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder> {

    ArrayList<LibrosDisponibles> listItem;

    public ListaLibrosDisponiblesAdapter(ArrayList<LibrosDisponibles> listItem) {
        this.listItem = listItem;

    }

    @NonNull
    @Override
    public ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libros_disponibles,null, false);
        return new ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder holder, int position) {

        holder.imagenLibrosDisponibles.setImageResource(Integer.parseInt(listItem.get(position).getImgResource()));
        holder.txtItemTituloLibro.setText(listItem.get(position).getTitulo());
        holder.txtItemDescripcion.setText(listItem.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class LibrosDisponiblesViewHolder extends RecyclerView.ViewHolder{

        ImageView imagenLibrosDisponibles;
        TextView txtItemTituloLibro;
        TextView txtItemDescripcion;


        public LibrosDisponiblesViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenLibrosDisponibles=itemView.findViewById(R.id.imagenLibrosDisponibles);
            txtItemTituloLibro=itemView.findViewById(R.id.txtItemTituloLibro);
            txtItemDescripcion=itemView.findViewById(R.id.txtItemDescripcion);
        }
    }
}
