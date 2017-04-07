package com.centroginecologicomujer.app.servicio;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServicioInterface {

    @FormUrlEncoded
    @POST("android/mail.php")
    Call<ResponseBody> reservarCita(@FieldMap HashMap<String,String> parametros);

}
