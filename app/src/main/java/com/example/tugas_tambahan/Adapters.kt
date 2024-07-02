package com.example.tugas_tambahan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Mhs(
    val nama: String = "",
    val nim: String = "",
    val jkel: String = "",
    val univ: String = "",
    val hobi: String = ""
)

class Adapters (private val mhsList: List<Mhs>) : RecyclerView.Adapter<Adapters.MhsViewHolder>() {

    class MhsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewNim: TextView = itemView.findViewById(R.id.textViewNim)
        val textViewGender: TextView = itemView.findViewById(R.id.textViewGender)
        val textViewUniversity: TextView = itemView.findViewById(R.id.textViewUniversity)
        val textViewHobby: TextView = itemView.findViewById(R.id.textViewHobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MhsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_mhs, parent, false)
        return MhsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MhsViewHolder, position: Int) {
        val currentMhs = mhsList[position]
        holder.textViewName.text = currentMhs.nama
        holder.textViewNim.text = currentMhs.nim
        holder.textViewGender.text = currentMhs.jkel
        holder.textViewUniversity.text = currentMhs.univ
        holder.textViewHobby.text = currentMhs.hobi
    }

    override fun getItemCount() = mhsList.size
}
