package red.lisgar.biblioteca.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import red.lisgar.biblioteca.R;
import red.lisgar.biblioteca.admin.AdminActualizarLibroActivity;
import red.lisgar.biblioteca.entidades.LibrosDisponibles;

public class ListaLibrosDisponiblesAdapter extends RecyclerView.Adapter<ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder> {

    ArrayList<LibrosDisponibles> listItem;
    Context context;

    public ListaLibrosDisponiblesAdapter(ArrayList<LibrosDisponibles> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libros_disponibles, parent, false);
        return new ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaLibrosDisponiblesAdapter.LibrosDisponiblesViewHolder holder, int position) {
        Glide.with(context)
                .load(listItem.get(position).getImgResource())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imagenLibrosDisponibles);
        holder.txtItemTituloLibro.setText(listItem.get(position).getTitulo());
        holder.txtItemDescripcion.setText(listItem.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class LibrosDisponiblesViewHolder extends RecyclerView.ViewHolder {

        ImageView imagenLibrosDisponibles;
        TextView txtItemTituloLibro;
        TextView txtItemDescripcion;


        public LibrosDisponiblesViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenLibrosDisponibles = itemView.findViewById(R.id.imagenLibrosDisponibles);
            txtItemTituloLibro = itemView.findViewById(R.id.txtItemTituloLibro);
            txtItemDescripcion = itemView.findViewById(R.id.txtItemDescripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AdminActualizarLibroActivity.class);
                    intent.putExtra("ID", listItem.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
