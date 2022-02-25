package com.kennymgn.workshiftscalc

import androidx.lifecycle.ViewModel

class CalcViewModel: ViewModel() {
    private data class Shift(val hours:Int, val minutes:Int) {
        val totalMinutes by lazy { hours*60 + minutes }
    }
    private val shifts = mutableListOf<Shift>()
    val numberOfShifts:Int
        get() = shifts.size
    val resultOutputText:String
        get() = formatResult(calcSum())
    fun addShift(hours:Int,minutes:Int) {shifts.add(Shift(hours, minutes))}
    private fun calcSum() = shifts.sumBy{it.totalMinutes}.toLong()
    private fun formatResult(sum:Long):String {
        val hours = sum/60
        val minutes = sum%60
        return if(minutes>9) "$hours:$minutes" else "$hours:0$minutes"
    }
    fun removeAllShifts() {shifts.clear()}
    fun removeLastShift() {if(shifts.isNotEmpty()) shifts.removeLast()}
}