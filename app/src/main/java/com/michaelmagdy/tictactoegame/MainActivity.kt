package com.michaelmagdy.tictactoegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view:View){

        val selectedButton = view as Button
        var cellId = 0
        when(selectedButton.id){
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }
        playGame(cellId, selectedButton)
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(cellId:Int, selectedButton:Button){

        if (activePlayer == 1){
            selectedButton.text = "X"
            selectedButton.setBackgroundResource(R.color.player1BackGround)
            player1.add(cellId)
            activePlayer = 2
            selectedButton.isEnabled = false
            autoPlay()
        } else {
            selectedButton.text = "O"
            selectedButton.setBackgroundResource(R.color.player2BackGround)
            player2.add(cellId)
            activePlayer = 1
            selectedButton.isEnabled = false
        }

        //selectedButton.isEnabled = false
        checkWinner()
    }

    fun checkWinner():Boolean{

        var winner = -1

        //player 1 probabilities
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        } else if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        } else if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        } else if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        } else if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        } else if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        } else if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        } else if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }


        //player 2 probabilities
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        } else if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        } else if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        } else if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        } else if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        } else if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        } else if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        } else if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }

        if (winner == 1){
            Toast.makeText(this, "Player 1 won the game", Toast.LENGTH_LONG).show()
            player1WinCount++
            restartGame()
            return true
        } else if (winner == 2){
            Toast.makeText(this, "Player 2 won the game", Toast.LENGTH_LONG).show()
            player2WinCount++
            restartGame()
            return true
        } else {
            return false
        }

    }

    fun autoPlay(){

        var emptyCells = ArrayList<Int>()
        for (cellId in 1..9){
            if ( !(player1.contains(cellId) || player2.contains(cellId)) ){
                emptyCells.add(cellId)
            }
        }
        if (emptyCells.isEmpty()){
            if (checkWinner()){

            } else {
                restartGame()
            }

        } else {
            val r = Random()
            val randomIndex = r.nextInt(emptyCells.size)
            val cellId = emptyCells[randomIndex]
            var buttonToSelect:Button?
            buttonToSelect = when(cellId){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {button1}
            }
            playGame(cellId, buttonToSelect)
        }

    }

    var player1WinCount = 0
    var player2WinCount = 0

    fun restartGame(){

        activePlayer = 1
        player1.clear()
        player2.clear()
        for (cellId in 1..9){

            var newButton:Button?
            newButton = when(cellId){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {button1}
            }
            newButton.text = ""
            newButton.setBackgroundResource(android.R.color.background_light)
            newButton.isEnabled = true
        }
        Toast.makeText(this, "Score: Player 1: $player1WinCount, Player 2: $player2WinCount",
            Toast.LENGTH_LONG).show()
    }
}
