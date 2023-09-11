package br.com.povengenharia.fuelchoice

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


fun String.toDoubleOrNull(): Double? {
    return if (isNotEmpty()) {
        try {
            this.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    } else {
        null
    }
}

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gasolinePrice = findViewById<EditText>(R.id.et_gasolinePrice)
        val ethanolPrice = findViewById<EditText>(R.id.et_ethanolPrice)
        val buttonCalculate = findViewById<Button>(R.id.bt_calculate)
        val result = findViewById<TextView>(R.id.tv_result)

        buttonCalculate.setOnClickListener {
            val inputGasolinePrice = gasolinePrice.text.toString().toDoubleOrNull()
            val inputEthanolPrice = ethanolPrice.text.toString().toDoubleOrNull()

            if (inputEthanolPrice == null || inputGasolinePrice == null || inputEthanolPrice <= 0 || inputGasolinePrice <= 0) {
                Toast.makeText(this, getString(R.string.toast_error_null_or_0), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val resultado = inputEthanolPrice / inputGasolinePrice
            if (resultado > .7) {
                result.text = getString(R.string.tv_tetx_result_gasoline)
            } else {
                result.text = getString(R.string.tv_tetx_result_ethanol)
            }

        }
    }
}



