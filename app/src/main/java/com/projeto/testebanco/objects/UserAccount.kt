package com.projeto.testebanco.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserAccount : Serializable {

    @SerializedName("userId") val userId : String? = null
    @SerializedName("name") val name : String? = null
    @SerializedName("bankAccount") val bankAccount : String? = null
    @SerializedName("agency") val agency : String? = null
    @SerializedName("balance") val balance : Double? = null



}