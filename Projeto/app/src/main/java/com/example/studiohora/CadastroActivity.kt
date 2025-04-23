package com.example.studiohora

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.studiohora.Model.Schedule
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CadastroActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cadastro)

        val txtNome = findViewById<EditText>(R.id.txtNome)
        val txtData = findViewById<EditText>(R.id.txtData)
        val txtHora = findViewById<EditText>(R.id.txtHora)
        val txtValor = findViewById<EditText>(R.id.txtValor)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        txtData.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    val formattedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                    txtData.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        txtHora.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    val formattedTime = String.format("%02d:%02d", hourOfDay, minute)
                    txtHora.setText(formattedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }

        btnCadastrar.setOnClickListener {
            val nome = txtNome.text.toString()
            val data = txtData.text.toString()
            val hora = txtHora.text.toString()

            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dataConvertida: Date = formatter.parse(txtData.text.toString()) ?: Date()

            val schedule = Schedule(
                cliente = txtNome.text.toString(),
                hora = txtHora.text.toString(),
                data = dataConvertida,
                valor = txtValor.text.toString().toDoubleOrNull() ?: 0.0
            )

            AlertDialog.Builder(this) // `this` é a Context, geralmente uma Activity
                .setTitle("Aviso")
                .setMessage(
                    "Cliente: ${schedule.cliente}\n" +
                            "Data: ${formatter.format(schedule.data)}\n" +
                            "Hora: ${schedule.hora}\n" +
                            "Valor: R$ ${"%.2f".format(schedule.valor)}"
                )

                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }


    }
}