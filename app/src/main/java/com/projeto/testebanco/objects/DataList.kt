package com.projeto.testebanco.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataList : Serializable {


    @SerializedName("statementList") var statementList = ArrayList<Data>()


}