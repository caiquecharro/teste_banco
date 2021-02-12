package com.projeto.testebanco

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.view.View
import androidx.core.view.isVisible
import com.android.volley.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.projeto.testebanco.adapter.DataAdapter
import com.projeto.testebanco.objects.User
import com.projeto.testebanco.objects.UserLogin
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

     var userLogin = UserLogin()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        bntLogin.setOnClickListener(login)


    }


    val login = View.OnClickListener {

        userLogin.user = txtUser.text.toString()
        userLogin.password = txtPassword.text.toString()

        if(userLogin.user != "" || userLogin.password != ""){

            bntLogin.isEnabled = false

            loginRest(this, userLogin)


        }else{

        }


    }






    fun loginRest(
        context: Context,
        userLoginRest: UserLogin


    ) {
        val queue = Volley.newRequestQueue(context)
        val sr = object : StringRequest(
            Request.Method.POST,
            "https://bank-app-test.herokuapp.com/api/login",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {


                    println(response)

                    var gson = Gson()

                    var json = response

                    var user : User = gson.fromJson(json, User::class.java)



                    bntLogin.isEnabled = true

                    val intent = Intent(this@MainActivity,DataActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)



                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {

                    txtUser.isVisible = false


                }
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["user"] = userLoginRest.user!!
                params["password"] = userLoginRest.password!!


                return params
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/x-www-form-urlencoded"
                return params
            }
        }
        queue.add(sr)
    }
}
