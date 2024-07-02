package com.example.tugas_tambahan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class Data : AppCompatActivity() {

    private lateinit var recyclerViewMhs: RecyclerView
    private lateinit var mhsAdapter: Adapters
    private val firestore = FirebaseFirestore.getInstance()
    private val mhsList = mutableListOf<Mhs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        recyclerViewMhs = findViewById(R.id.recyclerViewMhs)
        recyclerViewMhs.layoutManager = LinearLayoutManager(this)
        mhsAdapter = Adapters(mhsList)
        recyclerViewMhs.adapter = mhsAdapter

        fetchMhsData()

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Optional: close the current activity
        }
    }

    private fun fetchMhsData() {
        firestore.collection("mhs")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val mhs = document.toObject(Mhs::class.java)
                    mhsList.add(mhs)
                }
                mhsAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
}
