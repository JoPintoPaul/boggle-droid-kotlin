package com.droid.boggle.boggledroid

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import kotlinx.android.synthetic.main.grid_square.view.*

class MainActivity : AppCompatActivity() {

    private val dice: List<Die> = listOf(
            Die(listOf(Square("A"), Square("T"), Square("T"), Square("O"), Square("W"), Square("O"))),
            Die(listOf(Square("D"), Square("E"), Square("Y"), Square("L"), Square("V"), Square("R"))),
            Die(listOf(Square("E"), Square("A"), Square("G"), Square("A"), Square("N"), Square("E"))),
            Die(listOf(Square("O"), Square("O"), Square("B"), Square("J"), Square("A"), Square("B"))),
            Die(listOf(Square("L"), Square("R"), Square("H"), Square("Z"), Square("N"), Square("N"))),
            Die(listOf(Square("A"), Square("F"), Square("P"), Square("K"), Square("F"), Square("S"))),
            Die(listOf(Square("W"), Square("V"), Square("E"), Square("H"), Square("T"), Square("R"))),
            Die(listOf(Square("H"), Square("M"), Square("U"), Square("Qu"), Square("I"), Square("N"))),
            Die(listOf(Square("N"), Square("E"), Square("I"), Square("S"), Square("U"), Square("E"))),
            Die(listOf(Square("R"), Square("Y"), Square("T"), Square("L"), Square("E"), Square("T"))),
            Die(listOf(Square("C"), Square("T"), Square("U"), Square("I"), Square("O"), Square("M"))),
            Die(listOf(Square("G"), Square("H"), Square("W"), Square("E"), Square("N"), Square("E"))),
            Die(listOf(Square("E"), Square("D"), Square("L"), Square("X"), Square("I"), Square("R"))),
            Die(listOf(Square("O"), Square("H"), Square("C"), Square("P"), Square("A"), Square("S"))),
            Die(listOf(Square("S"), Square("Y"), Square("I"), Square("D"), Square("T"), Square("T"))),
            Die(listOf(Square("T"), Square("S"), Square("E"), Square("I"), Square("S"), Square("O")))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            displayGrid(dice)
        }

        displayGrid(dice)
    }


    private fun displayGrid(dice: List<Die>) {
        val board = Board(dice).shake()

        val gridview: GridView = findViewById(R.id.gridview)
        gridview.adapter = GridAdapter(this, board)
    }
}

class GridAdapter(context: Context, var squares: List<Square>) : BaseAdapter() {
    private val context: Context? = context

    override fun getCount(): Int {
        return squares.size
    }

    override fun getItem(position: Int): Any {
        return squares[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val square = this.squares[position]

        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val gridView = inflater.inflate(R.layout.grid_square, null)
        gridView.tvName.text = square.letter!!

        return gridView
    }
}

class Square(val letter: String)

class Die(private val letters: List<Square>) {
    fun roll(): Square {
        val shuffled = letters.shuffled()
        return shuffled[0]
    }
}

class Board(private val dice: List<Die>) {
    fun shake(): List<Square> {
        val shuffled = dice.shuffled()
        return shuffled.map { die -> die.roll() }
    }
}
