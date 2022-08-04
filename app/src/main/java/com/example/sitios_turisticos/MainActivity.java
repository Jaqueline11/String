package com.example.sitios_turisticos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.sitios_turisticos.databinding.ActivityDetalleSitioBinding;
import com.example.sitios_turisticos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.btnGuardar.setOnClickListener(view -> {
        String nombre = binding.txtNombre.getText().toString();
        String ubicacion = binding.txtUbicacion.getText().toString();
        String informacion = binding.txtInformacion.getText().toString();
        float valoracion = binding.rtbValoracion.getRating();
            abrirActivityDetalle(nombre,ubicacion,informacion,valoracion);
        });

        MetodoLauncher();
        binding.imgSitio.setOnClickListener(view -> {
            abrirCamara();
        });

    }

    private void abrirCamara() {
        Intent camaraintent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //--startActivityForResult(camaraintent,1000);
        activityResultLauncher.launch(camaraintent);
    }

    public void MetodoLauncher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==RESULT_OK ){
                    if (result.getData() !=null){
                        bitmap= result.getData().getExtras().getParcelable("data");
                        binding.imgSitio.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }

    private void abrirActivityDetalle(String nom, String ubi, String info, float val){
        Intent intent = new Intent(this,DetalleSitio.class);
        SitioTuristico sitio = new SitioTuristico(nom,ubi,info,val);
        intent.putExtra(DetalleSitio.SITIO_TURISTICO_KEY,sitio);
        intent.putExtra(DetalleSitio.BITMAP_KEY, bitmap);
        startActivity(intent);
    }

}