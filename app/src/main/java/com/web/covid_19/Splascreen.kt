package com.web.covid_19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.web.covid_19.databinding.ActivitySplascreenBinding

class Splascreen : AppCompatActivity() {
    lateinit var binding :ActivitySplascreenBinding
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplascreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        },3000)


    }

}