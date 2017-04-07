package com.centroginecologicomujer.app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.centroginecologicomujer.app.servicio.ServicioInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReserveCitaActivity extends AppCompatActivity {

    EditText etNombresApellidos, etEdad, etEmail, etTelefono, etConsulta;
    Spinner spEspecialidad;
    Button btnEnviar;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_cita);

        configurarDialog();

        spEspecialidad = (Spinner) findViewById(R.id.spEspecialidad);
        List<String> lista = new ArrayList<>();
        lista.add("Consulta");
        lista.add("Ecografía");
        lista.add("Planificación Familiar");
        lista.add("Chequeo Ginecológico");
        lista.add("Chequeo de Infertilidad");
        lista.add("Otros");
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(ReserveCitaActivity.this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEspecialidad.setAdapter(adaptador);


        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatos();
            }
        });

    }

    private void configurarDialog() {
        mProgressDialog = new ProgressDialog(ReserveCitaActivity.this);
        mProgressDialog.setMessage("Enviando reserva ..");
        mProgressDialog.setCancelable(false);
    }

    private void showDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    private void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    void enviarDatos() {
        etNombresApellidos = (EditText) findViewById(R.id.etNombresApellidos);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etConsulta = (EditText) findViewById(R.id.etConsulta);

        if (!etNombresApellidos.getText().toString().trim().isEmpty() &&
                !etEmail.getText().toString().trim().isEmpty() &&
                !etConsulta.getText().toString().trim().isEmpty()) {

            HashMap<String, String> parametros = new HashMap<>();
            parametros.put("nombres_apellidos", etNombresApellidos.getText().toString());
            parametros.put("edad", etEdad.getText().toString());
            parametros.put("email", etEmail.getText().toString());
            parametros.put("telefono", etTelefono.getText().toString());
            parametros.put("consulta", etConsulta.getText().toString());
            parametros.put("especialidad", spEspecialidad.getSelectedItem().toString());


            showDialog();

            Retrofit consultaRetrofit = new Retrofit.Builder().baseUrl("http://centroginecologicomujer.com/").build();
            ServicioInterface miInterface = consultaRetrofit.create(ServicioInterface.class);
            miInterface.reservarCita(parametros).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    hideDialog();
                    Toast.makeText(ReserveCitaActivity.this, "Tu mensaje se ha enviado correctamente.", Toast.LENGTH_LONG).show();
                    etNombresApellidos.setText("");
                    etEdad.setText("");
                    etEmail.setText("");
                    etTelefono.setText("");
                    etConsulta.setText("");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    hideDialog();
                    Toast.makeText(ReserveCitaActivity.this, "Se produjo un error en tu conexión. Inténtalo nuevamente.", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            Toast.makeText(ReserveCitaActivity.this, "Debe completar todos los campos para continuar", Toast.LENGTH_LONG).show();
        }


    }


}
