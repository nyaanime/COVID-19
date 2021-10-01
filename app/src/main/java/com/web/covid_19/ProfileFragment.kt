package com.web.covid_19

import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.edit
import com.web.Constant
import com.web.covid_19.databinding.ActivityRegistrasiBinding
import com.web.covid_19.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val u = binding.user.text.toString()
//        val p =binding.pass.text.toString()
//        getRegis(u,p)

        val userid = context?.getSharedPreferences(Constant.PREFS_NAME,ContextWrapper.MODE_PRIVATE)?.getString(Constant.username,"")
        val email = context?.getSharedPreferences(Constant.PREFS_NAME,ContextWrapper.MODE_PRIVATE)?.getString(Constant.email,"")
        val nama = context?.getSharedPreferences(Constant.PREFS_NAME,ContextWrapper.MODE_PRIVATE)?.getString(Constant.nama,"")
        binding.usernama.text= userid.toString()
        binding.email.text= email.toString()
        binding.nama.text= nama.toString()
        this.binding.facebook.setOnClickListener() {
            this.openBrowser("https://www.facebook.com")
        }
        this.binding.instagram.setOnClickListener() {
            this.openBrowser("https://www.facebook.com")
        }

        this.binding.feedback.setOnClickListener(){
            val user= "admin"
            val komentar = binding.komentar.text.toString()
           feedback(user,komentar)
        }
        binding.logout.setOnClickListener(){
            context?.getSharedPreferences(Constant.PREFS_NAME,ContextWrapper.MODE_PRIVATE)?.edit {
                clear()
            }
            startActivity(Intent(context,Login::class.java))
        }
    }
    fun feedback(user : String,komen:String){
        ApiService.getApiEndPoint().postFeeedback(user,komen)
            .enqueue(object : Callback<ResponseFeedback>{
                override fun onResponse(
                    call: Call<ResponseFeedback>,
                    response: Response<ResponseFeedback>
                ) {
                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<ResponseFeedback>, t: Throwable) {
                    Toast.makeText(activity,t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
    fun openBrowser(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url))

    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }

//    fun getRegis(us : String , ps : String){
//        ApiService.getApiEndPoint().postRegis(us,ps)
//            .enqueue(object : Callback<ResponseRegis> {
//                override fun onResponse(
//                    call: Call<ResponseRegis>,
//                    response: Response<ResponseRegis>
//                ) {
//                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onFailure(call: Call<ResponseRegis>, t: Throwable) {
//                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
}