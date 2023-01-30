package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var editTextWeight: EditText
    lateinit var editTextHeight: EditText
    lateinit var calculateBtn:Button
    lateinit var resultText: TextView
    lateinit var statementResult: TextView
    lateinit var informationRes: TextView
    lateinit var cardView: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextWeight = findViewById<EditText>(R.id.etWeight)
        editTextHeight = findViewById<EditText>(R.id.etHeight)
        calculateBtn  = findViewById<Button>(R.id.Calculate)
        resultText = findViewById<TextView>(R.id.Result)
        statementResult = findViewById<TextView>(R.id.tvResult)
        informationRes = findViewById<TextView>(R.id.tvInfo)

        calculateBtn.setOnClickListener{
            calculate()
        }
    }

    fun calculate(){
        var weight = editTextWeight.text.toString()
        var height = editTextHeight.text.toString()
        if(functionValidator(weight, height)){
            editTextHeight.text.clear()
            editTextWeight.text.clear()
            resultText.setText("")
            informationRes.setText("")
            statementResult.setText("")

            var weight1 = weight.toFloat()
            var height1 = height.toFloat()
            height1 = height1/100
            var BMI: Float = weight1/(height1 * height1)
            var BMIf = String.format("%.2f",BMI).toFloat()
            resultShow(BMIf)
        }

    }
    private fun functionValidator(weight: String?, height: String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun resultShow(BMI:Float){
        resultText.text = BMI.toString()
        informationRes.text = "(Normal Range is 18.8-24.9)"
        var result = ""
        var color = 0
        when{
            BMI<18.5 ->{
                result = "Under Weight"
                color = R.color.under_weight
            }
            BMI in 18.5 ..24.99 ->{
                result = "Healthy"
                color = R.color.normal
            }
            BMI in 24.99 ..29.99 ->{
                result = "Overweight"
                color = R.color.over_weight
            }
            BMI>=30.0->{
                result = "Obese"
                color = R.color.obese
            }
        }
        statementResult.setTextColor(ContextCompat.getColor(this, color))
        statementResult.text = result
    }
}