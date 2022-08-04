package com.example.sitios_turisticos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.sitios_turisticos.databinding.ActivityDetalleSitioBinding;
import com.example.sitios_turisticos.databinding.ActivityMainBinding;

public class DetalleSitio extends AppCompatActivity {

    public static  final String SITIO_TURISTICO_KEY = "SitioTuristico";
    public static final String BITMAP_KEY = "bitmap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetalleSitioBinding binding= ActivityDetalleSitioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        SitioTuristico sitio=extras.getParcelable(SITIO_TURISTICO_KEY);
        binding.setSitioTuristico(sitio);

        Bitmap bitmap= extras.getParcelable(BITMAP_KEY);
        if (bitmap!=null){
            binding.imgPerfil.setImageBitmap(bitmap);
        }

        /*binding.txtNonbreSitio.setText(sitio.getNombre());
        binding.txtInformacion.setText(sitio.getInformacion());
        binding.txtUbicacion.setText(sitio.getUbicacion());
        binding.rtbValoracion.setRating(sitio.getValoracion());*/
    }
}