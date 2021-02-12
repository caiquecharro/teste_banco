package com.projeto.testebanco.objects

import com.google.gson.annotations.SerializedName;
import java.io.Serializable



class User : Serializable {

    @SerializedName("userAccount") val userAccount : UserAccount? = null
}