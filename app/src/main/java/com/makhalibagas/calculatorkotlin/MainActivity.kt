package com.makhalibagas.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.makhalibagas.calculatorkotlin.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            //Numbers
            num0.setOnClickListener { appendVal("0", false) }
            num1.setOnClickListener { appendVal("1", false) }
            num2.setOnClickListener { appendVal("2", false) }
            num3.setOnClickListener { appendVal("3", false) }
            num4.setOnClickListener { appendVal("4", false) }
            num5.setOnClickListener { appendVal("5", false) }
            num6.setOnClickListener { appendVal("6", false) }
            num7.setOnClickListener { appendVal("7", false) }
            num8.setOnClickListener { appendVal("8", false) }
            num9.setOnClickListener { appendVal("9", false) }
            numDot.setOnClickListener { appendVal(".", false) }
            //Operators
            clear.setOnClickListener { appendVal("", true) }
            startBracket.setOnClickListener { appendVal(" ( ", false) }
            closeBracket.setOnClickListener { appendVal(" ) ", false) }
            actionDivide.setOnClickListener { appendVal(" / ", false) }
            actionMultiply.setOnClickListener { appendVal(" * ", false) }
            actionMinus.setOnClickListener { appendVal(" - ", false) }
            actionAdd.setOnClickListener { appendVal(" + ", false) }

            actionBack.setOnClickListener {
                val expression = placeholder.text.toString()
                if (expression.isNotEmpty()) {
                    placeholder.text = expression.substring(0, expression.length - 1)
                }


            }

            actionEquals.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(placeholder.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if (result == longResult.toDouble()) {
                        Toast.makeText(this@MainActivity, "Double", Toast.LENGTH_SHORT).show()
                        answer.text = longResult.toString()
                    } else
                        answer.text = result.toString()

                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show();

                    Log.d("EXCEPTION", "Message: ${e.message}")
                }
            }
        }
    }

    fun appendVal(string: String, isClear: Boolean) {
        binding.apply {
            if (isClear) {
                placeholder.text = ""
                answer.text = ""
            } else {
                if (placeholder.text == "*"){

                }
                placeholder.append(string)
            }
        }
    }
}