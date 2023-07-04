package com.example.diceroller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roller)
        rollButton.setOnClickListener {
            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
            rollDice()
        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val num = dice.roll()
        val resultTextView: TextView = findViewById(R.id.number)
        resultTextView.text = num.toString()

//        val images = arrayOf(
//            R.drawable.dice_1,
//            R.drawable.dice_2,
//            R.drawable.dice_3,
//            R.drawable.dice_4,
//            R.drawable.dice_5,
//            R.drawable.dice_6
//        )

        val numHint: TextView = findViewById(R.id.number_hint)
        val hintText = when (num) {
            1 -> "Wow!"
            2 -> "Amazing!"
            3 -> "Bravo!"
            4 -> "Yep!"
            5 -> "Almost one step away!"
            else -> getString(R.string.lucky_number)
        }
        numHint.text = hintText

        val diceImage: ImageView = findViewById(R.id.dice_image)
        val imageName = "dice_$num"
        diceImage.setImageResource(getDrawableResource(this, imageName))
    }

    private fun getDrawableResource(context: Context, name: String): Int {
        val packageName = context.packageName
        return context.resources.getIdentifier(name, "drawable", packageName)
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        if (numSides >= 1) {
            return (1..numSides).random()
        }

        return 0
    }
}
