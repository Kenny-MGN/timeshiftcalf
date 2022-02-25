package com.kennymgn.workshiftscalc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),View.OnClickListener,View.OnLongClickListener {
    private lateinit var timePicker:TimePicker
    private lateinit var resultOutputTV: TextView
    private lateinit var numberOfShiftsTV: TextView
    private lateinit var addButton: Button
    private lateinit var removeButton: Button
    private val model:CalcViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultOutputTV = findViewById(R.id.result_output_tw)
        numberOfShiftsTV = findViewById(R.id.number_of_shifts_tw)
        addButton = findViewById(R.id.add_button)
        removeButton = findViewById(R.id.remove_button)
        addButton.setOnClickListener(this)
        addButton.setOnLongClickListener(this)
        removeButton.setOnClickListener(this)
        removeButton.setOnLongClickListener {
            model.removeAllShifts()
            refresh()
            return@setOnLongClickListener true
        }
        timePicker = findViewById(R.id.time_picker)
        timePicker.apply {
            setIs24HourView(true)
            hour = 8
            minute = 0
        }
        refresh()
    }
    override fun onClick(view: View?) {
        when(view?.id) {
            addButton.id -> {
                model.addShift(timePicker.hour, timePicker.minute)
                refresh()
            }
            removeButton.id -> {
                model.removeLastShift()
                refresh()
            }
        }
    }
    override fun onLongClick(v: View?) {
        when(v?.id) {
            addButton.id -> {
            }
        }
    }
    private fun refresh() {
        resultOutputTV.text = model.resultOutputText
        numberOfShiftsTV.text = getString(R.string.number_of_shifts_text)
    }
}