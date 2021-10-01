package com.web.covid_19

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.web.Constant
import com.web.covid_19.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {



    lateinit var binding: ActivityLoginBinding
    lateinit var pref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(getSharedPreferences(Constant.PREFS_NAME,ContextWrapper.MODE_PRIVATE).contains(Constant.username)){
            startActivity(Intent(this, MainActivity::class.java))
        }



        pref = getSharedPreferences(Constant.PREFS_NAME,Context.MODE_PRIVATE)
        binding.regis.setOnClickListener {

            if(binding.user.text.toString().isEmpty()){
                binding.user.setError("isi username dulu")
            }else if(binding.pass.text.toString().isEmpty()){
                binding.pass.setError("isi password dulu")
            }else{
                val u = binding.user.text.toString()
                val p =binding.pass.text.toString()
                login(u,p)
            }

        }
        binding.sigUp.setOnClickListener {
            startActivity(Intent(this,Registrasi::class.java))
        }
    }
    fun login(us : String , ps : String){
        ApiService.getApiEndPoint().postLogin(us,ps)
            .enqueue(object : Callback<ResponseLogin2> {
                override fun onResponse(
                    call: Call<ResponseLogin2>,
                    response: Response<ResponseLogin2>
                ) {
                    if(response.isSuccessful){
                        val datalogin = response.body()?.data
                        pref.edit(true){
                            putString(Constant.username,
                                datalogin?.username)
                            putString(Constant.email,datalogin?.email)
                            putString(Constant.nama,datalogin?.namaLengkap)
                        }
                        Log.i("datapref", pref.toString())
                        print(pref)
                        try {
                            Thread.sleep(100)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                    }else{
                        Toast.makeText(this@Login, response.message(), Toast.LENGTH_SHORT).show()

                    }


                }

                override fun onFailure(call: Call<ResponseLogin2>, t: Throwable) {
                    Toast.makeText(this@Login, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }


}