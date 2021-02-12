package com.projeto.testebanco.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projeto.testebanco.R
import com.projeto.testebanco.objects.Data
import com.projeto.testebanco.objects.DataList
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.format.FormatStyle
import java.util.*


class DataAdapter (private val context: Context, private val dataList: DataList?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var ph: RecyclerView.ViewHolder? = null


        val v = LayoutInflater.from(context).inflate(R.layout.card, parent, false)
        ph = DataHolder(v)

        return ph

    }

    override fun getItemCount(): Int {

       var size = dataList!!.statementList.size

        return size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = dataList!!.statementList!![position]

        (holder as? DataHolder)?.bindView(item)


    }


    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var lblDesc: TextView
        var lblValue: TextView
        var lblDate: TextView

        var data: Data? = null


        init {

            lblDesc= itemView.findViewById(R.id.lblDesc)
            lblValue = itemView.findViewById(R.id.lblValue)
            lblDate = itemView.findViewById(R.id.lblDate)

        }


        fun bindView(data: Data) {

            this.data = data

            lblDesc.text = data.desc

            val dateFormat = SimpleDateFormat("dd/MM/yyyy")

            lblDate.text = dateFormat.format(data.date)

            val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            lblValue.text = format.format(data.value)


        }
    }


}