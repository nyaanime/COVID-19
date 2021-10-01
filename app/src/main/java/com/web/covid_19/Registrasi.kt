package com.web.covid_19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.web.covid_19.databinding.ActivityRegistrasiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registrasi : AppCompatActivity() {
    lateinit var binding: ActivityRegistrasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.regis.setOnClickListener {

            val n = binding.nama.text.toString()
            val e = binding.email.text.toString()
            val u = binding.user.text.toString()
            val p =binding.pass.text.toString()
            getRegis(u,p,e,n, null.toString())
        }

    }
    fun getRegis(us : String , ps : String,em:String,nm:String , yu : String){
        ApiService.getApiEndPoint().postRegis(us,ps,em,nm)
            .enqueue(object : Callback<ResponseRegis> {
                override fun onResponse(
                    call: Call<ResponseRegis>,
                    response: Response<ResponseRegis>
                ) {
                    startActivity(Intent(applicationContext,Login::class.java))
                    Toast.makeText(this@Registrasi, response.message(), Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<ResponseRegis>, t: Throwable) {
                    Toast.makeText(this@Registrasi, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }


}