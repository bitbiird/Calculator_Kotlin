package com.bitbiird.calculator_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operation = null

        buttonZero.setOnClickListener(this)
        buttonOne.setOnClickListener(this)
        buttonTwo.setOnClickListener(this)
        buttonThree.setOnClickListener(this)
        buttonFour.setOnClickListener(this)
        buttonFive.setOnClickListener(this)
        buttonSix.setOnClickListener(this)
        buttonSeven.setOnClickListener(this)
        buttonEight.setOnClickListener(this)
        buttonNine.setOnClickListener(this)
        buttonComa.setOnClickListener(this)
        buttonAdd.setOnClickListener(this)
        buttonSub.setOnClickListener(this)
        buttonMul.setOnClickListener(this)
        buttonDiv.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            buttonZero -> onNumberPressed("0")
            buttonOne -> onNumberPressed("1")
            buttonTwo -> onNumberPressed("2")
            buttonThree -> onNumberPressed("3")
            buttonFour -> onNumberPressed("4")
            buttonFive -> onNumberPressed("5")
            buttonSix -> onNumberPressed("6")
            buttonSeven -> onNumberPressed("7")
            buttonEight -> onNumberPressed("8")
            buttonNine -> onNumberPressed("9")
            buttonComa -> onNumberPressed(",")
            buttonAdd -> onOperationPressed("+")
            buttonSub -> onOperationPressed("-")
            buttonMul -> onOperationPressed("x")
            buttonDiv -> onOperationPressed("/")
            buttonClear -> onClearPressed()
            buttonEqual -> onEqualPressed()
        }
    }

    private fun onNumberPressed(number: String) {
        renderScreen(number)
        checkOperation()
    }

    private fun renderScreen(number: String) {
        val result = if (screen.text == "0" && number != ",") {
            number
        } else {
            "${screen.text}$number"
        }

        screen.text = result
    }

    private fun checkOperation() {
        if (operation == null) {
            firstNumber = screen.text.toString().toDouble()
        } else {
            secondNumber = screen.text.toString().toDouble()
        }
    }

    private fun onOperationPressed(operation: String) {
        this.operation = operation
        firstNumber = screen.text.toString().toDouble()

        screen.text = "0"
    }

    private fun onEqualPressed() {
        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "x" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else -> 0
        }

        operation = null
        firstNumber = result.toDouble()

        try {
            screen.text = if (result.toString().endsWith(".0")) {
                result.toString().replace(".0", "")
            } else {
                "%.2f".format(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun onClearPressed() {
        screen.text = "0"
        firstNumber = 0.0
        secondNumber = 0.0
    }
}