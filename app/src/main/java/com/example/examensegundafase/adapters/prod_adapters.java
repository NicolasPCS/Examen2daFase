package com.example.examensegundafase.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examensegundafase.R;
import com.example.examensegundafase.entities.productos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class prod_adapters extends RecyclerView.Adapter<prod_adapters.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<productos> model;

    // Listener
    private View.OnClickListener listener;
    private Object Context;

    public prod_adapters(Context context, ArrayList<productos> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.productos_item, parent, false);
        //view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull prod_adapters.ViewHolder holder, int position) {
        String imgProd = model.get(position).getImagenUrl();
        String nombreProd = model.get(position).getNombreProducto();
        String precioProd = model.get(position).getPrecioProducto();

        //holder.imgProd.Picc;
        holder.nombreProd.setText(nombreProd);
        holder.precioProd.setText(precioProd);

        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;

        ImageView imgProd;
        TextView nombreProd, precioProd;
        CheckBox estaAgregado;
        Button btnPagar;

        // Expandable view
        public LinearLayout expandableView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            imgProd = itemView.findViewById(R.id.imageView);

            nombreProd = itemView.findViewById(R.id.nombreProd);
            precioProd = itemView.findViewById(R.id.precioProd);
            estaAgregado = itemView.findViewById(R.id.cbAgregar);

            // Botones Asistencia
            btnPagar = itemView.findViewById(R.id.btnIrAPagar);

            // Expandable CardView
            //cardView = (CardView) itemView.findViewById(R.id.);
        }

        void setOnClickListeners() {
            //btnPagar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnIrAPagar:
                    Toast.makeText(context, "Agregando productos a tu carrito", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
