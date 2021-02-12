package com.projeto.testebanco

import android.content.Context
import android.content.Intent
import android.graphics.MaskFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.projeto.testebanco.adapter.DataAdapter
import com.projeto.testebanco.objects.Data
import com.projeto.testebanco.objects.DataList
import com.projeto.testebanco.objects.User
import com.projeto.testebanco.objects.UserLogin
import kotlinx.android.synthetic.main.activity_data.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap

class DataActivity : AppCompatActivity() {


    var userActive = User()

    var mLayoutManager: RecyclerView.LayoutManager? = null
    var mAdapter: DataAdapter? = null

    var list : DataList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        userActive = intent.getSerializableExtra("user") as User



        var texto = userActive.userAccount!!.agency



        lblName.text = userActive.userAccount!!.name
        lblConta.text = userActive.userAccount!!.bankAccount + " / " + userActive.userAccount!!.agency

        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        lblSaldo.text = format.format(userActive.userAccount!!.balance)

//        lblSaldo.text = userActive.userAccount!!.balance.toString()

        list = DataList()

        loginRest(this, userActive)


    }




    fun loginRest(
        context: Context,
        user: User


    ) {
        val queue = Volley.newRequestQueue(context)
        val sr = object : StringRequest(
            Request.Method.GET,
            "https://bank-app-test.herokuapp.com/api/statements/" + userActive.userAccount!!.userId.toString(),
            object : Response.Listener<String> {
                override fun onResponse(response: String) {


                    println(response)

                    var gson = Gson()

                    var json = response

                    list  = gson.fromJson(json, DataList::class.java)


                    mLayoutManager = LinearLayoutManager(this@DataActivity)
                    mRecyclerView.layoutManager = mLayoutManager

                    mAdapter = DataAdapter(this@DataActivity, list)
                    mRecyclerView.adapter = mAdapter



                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {



                }
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
//                params["user"] = userLoginRest.user!!
//                params["password"] = userLoginRest.password!!


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
