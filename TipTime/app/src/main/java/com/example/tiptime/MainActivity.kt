package com.example.tiptime

import android.icu.number.NumberFormatter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayTip(0.0)
        binding.calculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val inputAmount = binding.costOfService.text.toString()
        val cost = inputAmount.toDoubleOrNull()

        if (cost == null) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when(binding.radioGroup.checkedRadioButtonId) {
            R.id.amazing_21 -> 0.21
            R.id.good_16 -> 0.16
            else -> 0.10
        }

        var tip = cost * tipPercentage
        if (binding.roundUpTips.isChecked) {
            tip = ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formatterTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmountNum.text = getString(R.string.tip_amount_num, formatterTip)
    }
}