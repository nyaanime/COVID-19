package com.web.covid_19

import android.content.ContextWrapper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.web.Constant
import com.web.covid_19.Ambulance.AmbulanceFragment
import com.web.covid_19.Home.HomeFragment
import com.web.covid_19.Oxygen.OxygenFragment
import com.web.covid_19.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttomMenu.selectedItemId = R.id.home

        changeFragments(HomeFragment())
        binding.buttomMenu.setOnNavigationItemSelectedListener {
                item->
            when(item.itemId){
                R.id.Ambulance->{
                    changeFragments(AmbulanceFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.home-> {
                    changeFragments(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.Oxygen->{
                    changeFragments(OxygenFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile->{
                    changeFragments(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.announcement->{
                    changeFragments(announcementFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        val a =  getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)?.getString(
            Constant.username,
            "none")

        Toast.makeText(this, "$a",Toast.LENGTH_SHORT).show()

    }


    private fun changeFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()

    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Tutup Aplikasi")
        builder
            .setMessage("yakin tutp aplikasi?")
        builder.setPositiveButton("ya"){dialog,which->
            finishAffinity()
        }
        builder.setNegativeButton("tidak"){dialog,which->
            Toast.makeText(this,"kembalik ke halaman",Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}