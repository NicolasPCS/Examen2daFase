package com.example.examensegundafase.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examensegundafase.R;
import com.example.examensegundafase.adapters.prod_adapters;
import com.example.examensegundafase.databinding.FragmentHomeBinding;
import com.example.examensegundafase.entities.productos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    // Firebase
    private DocumentReference docRef;
    private FirebaseAuth mAuth;

    // Adapter para inflar el CardView
    private prod_adapters adapterProd;
    private RecyclerView recyclerViewProds;
    private ArrayList<productos> prodArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewProds = root.findViewById(R.id.recyclerViewProds);
        prodArrayList = new ArrayList<>();

        // Firebase
        mAuth = FirebaseAuth.getInstance();

        cargarListaProds(new myCallBack() {
            @Override
            public void onCallback(ArrayList<productos> courseList) {
                mostrarData();
            }
        });



        return root;
    }

    private void mostrarData() {
        recyclerViewProds.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterProd = new prod_adapters(getContext(), prodArrayList);
        recyclerViewProds.setAdapter(adapterProd);

        Log.d("Size", String.valueOf(prodArrayList.size()));

        for (int i = 0; i < prodArrayList.size(); i++) {
            Log.d("Course", prodArrayList.get(i).getNombreProducto());
            Log.d("Course", prodArrayList.get(i).getPrecioProducto());
            Log.d("Course", prodArrayList.get(i).getImagenUrl());
        }
    }

    public interface myCallBack {
        void onCallback(ArrayList<productos> courseList);
    }

    private void cargarListaProds(myCallBack mCallBack) {
        prodArrayList.add(new productos("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.fullabarrotes.com%2Fproducto-tag%2Fcosteno%2F&psig=AOvVaw20_mLwIA9Sbjb5t-Xcvew2&ust=1654273182135000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCOi1hoGWj_gCFQAAAAAdAAAAABAD",
                "Aceite Costeño", "10.00", true));
        prodArrayList.add(new productos("https://www.gloria.com.pe/uploads/products/lacteos/gloria-ninios-400g.jpg",
                "Leche gloria", "5.00", true));
        prodArrayList.add(new productos("https://s.cornershopapp.com/product-images/Filete-de-Atun-Gloria-en-Aceite-Vegetal-Lata-170-g-364118.jpg?versionId=KqccOTlksKpvGuYAuDIyc9eNhVP.c28T",
                "Atún gloria", "10.00", true));
        prodArrayList.add(new productos("https://plazavea.vteximg.com.br/arquivos/ids/561249-450-450/20140724.jpg",
                "Frejoles", "10.00", true));
        prodArrayList.add(new productos("https://vegaperu.vtexassets.com/arquivos/ids/159503/7750670014168.jpg?v=637835695667200000",
                "Gaseosa oro", "10.00", true));

        mCallBack.onCallback(prodArrayList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}