package com.example.schedule

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.model.entities.Schedule
import com.example.schedule.viewmodel.ScheduleViewModel
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.util.Calendar

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etData = findViewById<EditText>(R.id.etData)
        val etNome = findViewById<EditText>(R.id.etNome)
        val etHorario = findViewById<EditText>(R.id.etHorario)
        val etValor = findViewById<EditText>(R.id.etValor)

        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        etData.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { view, selectedYear, selectedMonth, selectedDay ->
                    // Formata a data selecionada para "dd/MM/yyyy"
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    etData.setText(formattedDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        btnSalvar.setOnClickListener {
            val cliente = etNome.text.toString()
            val dataMarcada = etData.text.toString()
            val horario = etHorario.text.toString()
            val valor = etValor.text.toString().toDoubleOrNull()

            if (cliente.isNotEmpty() && dataMarcada.isNotEmpty() && horario.isNotEmpty() && valor != null) {
                val schedule = Schedule(cliente, dataMarcada, horario, valor, null)

                // Salvar os dados no arquivo de texto
                saveScheduleToFile(schedule)

                // Enviar os dados de volta para MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("SCHEDULE", schedule)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveScheduleToFile(schedule: Schedule) {
        try {
            // Acessando o arquivo no armazenamento interno
            val fileOutputStream: FileOutputStream = openFileOutput("agendamentos.txt", MODE_APPEND)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            // Escrevendo o objeto no arquivo
            objectOutputStream.writeObject(schedule)
            objectOutputStream.close()

            Toast.makeText(this, "Agendamento salvo com sucesso!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Erro ao salvar o agendamento.", Toast.LENGTH_SHORT).show()
        }
    }
}