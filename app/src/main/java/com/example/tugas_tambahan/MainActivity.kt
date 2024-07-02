package com.example.tugas_tambahan

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextNim = findViewById<EditText>(R.id.editTextNim)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val spinnerUniversity = findViewById<Spinner>(R.id.spinnerUniversity)
        val checkBoxReading = findViewById<CheckBox>(R.id.checkBoxReading)
        val checkBoxSports = findViewById<CheckBox>(R.id.checkBoxSports)
        val checkBoxMusic = findViewById<CheckBox>(R.id.checkBoxMusic)
        val checkBoxTravel = findViewById<CheckBox>(R.id.checkBoxTravel)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val dataButton = findViewById<Button>(R.id.dataButton)

        submitButton.setOnClickListener {
            val name = editTextName.text.toString()
            val nim = editTextNim.text.toString()

            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(selectedGenderId).text.toString()

            val selectedUniversity = spinnerUniversity.selectedItem.toString()

            val hobbies = StringBuilder()
            if (checkBoxReading.isChecked) hobbies.append("Membaca, ")
            if (checkBoxSports.isChecked) hobbies.append("Olahraga, ")
            if (checkBoxMusic.isChecked) hobbies.append("Musik, ")
            if (checkBoxTravel.isChecked) hobbies.append("Traveling, ")

            val hobbiesText = if (hobbies.isNotEmpty()) hobbies.removeSuffix(", ").toString() else "Tidak ada"

            val mhsData = hashMapOf(
                "nama" to name,
                "nim" to nim,
                "jkel" to selectedGender,
                "univ" to selectedUniversity,
                "hobi" to hobbiesText
            )

            firestore.collection("mhs")
                .add(mhsData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        dataButton.setOnClickListener {
            startActivity(Intent(this,Data::class.java))
            Toast.makeText(this,"Tampilkan data",Toast.LENGTH_LONG).show()
        }
    }
}
