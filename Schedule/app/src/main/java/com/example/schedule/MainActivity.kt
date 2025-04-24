package com.example.schedule

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.schedule.viewmodel.ScheduleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.model.entities.Schedule
import java.io.FileInputStream
import java.io.IOException
import java.io.ObjectInputStream


class MainActivity : AppCompatActivity() {

    private val scheduleViewModel by lazy {
        ViewModelProvider(this).get(ScheduleViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAdicionar = findViewById<FloatingActionButton>(R.id.btnAdicionarHorario)
        val listView: ListView = findViewById(R.id.listHorarios)

        loadSchedulesFromFile()


        btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        scheduleViewModel.scheduleList.observe(this) { schedules ->
            val scheduleList = schedules.map {
                "Cliente: ${it.Cliente}, Data: ${it.DataMarcada}, Horário: ${it.Horario}, Valor: R$ ${it.Valor}"
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, scheduleList)
            listView.adapter = adapter
        }
    }

    private fun loadSchedulesFromFile() {
        try {
            val fileInputStream: FileInputStream = openFileInput("agendamentos.txt")
            val objectInputStream = ObjectInputStream(fileInputStream)
            var schedule: Schedule?
            while (true) {
                schedule = objectInputStream.readObject() as? Schedule
                schedule?.let {
                    scheduleViewModel.addSchedule(it)
                }
            }
        } catch (e: IOException) {
            // O arquivo pode não existir ou ser vazio, o que é esperado na primeira vez
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

}
