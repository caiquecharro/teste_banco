package com.projeto.testebanco.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*


class Data : Serializable {


    @SerializedName("title") var title : String? = null
    @SerializedName("desc") var desc : String? = null
    @SerializedName("date") var date : Date? = null
    @SerializedName("value") var value : Double? = null



}