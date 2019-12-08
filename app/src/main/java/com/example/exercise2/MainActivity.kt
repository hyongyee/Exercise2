package com.example.exercise2

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener { calculate(it) }
        buttonReset.setOnClickListener { reset(it) }
    }

    private fun reset(it: View) {
        editTextWeight.text.clear()
        editTextHeight.text.clear()
        imageViewProfile.setImageResource(R.drawable.empty)
        textViewBMI.text=getString(R.string.bmi)
        hideKeyboard(it)
    }

    private fun calculate(it: View) {

        if(editTextWeight.text.length==0||editTextHeight.text.length==0){
            Dialog()
        }else{
            var result=(editTextWeight.text.toString().toDouble())/((editTextHeight.text.toString().toDouble()/100)*(editTextHeight.text.toString().toDouble()/100))
            result=String.format("%.2f",result).toDouble()
            if(result<18.5) {
                textViewBMI.text =getString(R.string.bmi) + " " + result.toString() + ", Underweight";
                imageViewProfile.setImageResource(R.drawable.under)
            }else if(result<=24.9){
                textViewBMI.text =getString(R.string.bmi) + " " + result.toString() + ", Normal";
                imageViewProfile.setImageResource(R.drawable.normal)
            }else{
                textViewBMI.text =getString(R.string.bmi) + " " + result.toString() + ", Overweight";
                imageViewProfile.setImageResource(R.drawable.over)
            }
        }
        hideKeyboard(it)
    }

    private fun Dialog(){
        val builder = AlertDialog.Builder(this);
        builder.setTitle("Information")
            .setMessage("All Information is required")
            .setPositiveButton("OK",{ dialogInterface: DialogInterface, i: Int -> })

        builder.show()
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
