package com.mecorp.medatcorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.mecorp.medatcorp.entidad.ad_tmusua
import com.mecorp.medatcorp.services.LoginService
import com.mecorp.medatcorp.utils.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var txtUsuario : TextInputEditText
    private lateinit var txtClave : TextInputEditText
    private lateinit var btnLogin : Button
    private lateinit var LoginService: LoginService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        txtUsuario=findViewById(R.id.txtUsuario)
        txtClave=findViewById(R.id.txtClave)
        btnLogin=findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener { Login() }
    }

    fun Login(){
        var usu=txtUsuario.text.toString()
        var con=txtClave.text.toString()
        var msg="";

        if(txtUsuario.text.toString()==""){
            msg +="*Ingrese el Usuario\n";
        }
        if(txtClave.text.toString()==""){
            msg +="*Ingrese la Contraseña\n";
        }
        if(msg!=""){
            showAlert(msg);
        }
        else{
            // 👇 Obtenemos el servicio ya configurado
            val loginService = ApiUtils.getApiLogin()

            loginService.login(usu, con).enqueue(object : Callback<ad_tmusua> {
                override fun onResponse(call: Call<ad_tmusua?>, response: Response<ad_tmusua?>) {
                    if (response.isSuccessful) {
                        val usuario = response.body()
                        if (usuario != null) {
                            showAlert("Login correcto.\nToken: ${usuario.token}")
                        } else {
                            showAlert("No se recibieron datos del servidor.")
                        }
                    } else {
                        showAlert("Error en la autenticación (${response.code()})")
                    }
                }

                override fun onFailure(call: Call<ad_tmusua>, t: Throwable) {
                    showAlert("Error de conexión: ${t.message}")
                }
            })
        }
    }
    fun showAlert(men:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Mensaje - MECORP")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }
}
