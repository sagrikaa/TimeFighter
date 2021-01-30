package com.example.timefighter.sagrikaaggarwal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreText:TextView
    internal lateinit var  timeLeftText:TextView
    internal var score=0


    internal var gameStarted=false
    internal lateinit var countDownTimer:CountDownTimer
    internal val initialCount:Long=60000
    internal val countDownInterval:Long=1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tapMeButton=findViewById((R.id.tapMeButton))
        gameScoreText=findViewById((R.id.scoreTextView))
        timeLeftText=findViewById((R.id.timeLeftTextView))
        resetGame()
        tapMeButton.setOnClickListener{view->incrementScore()}
    }

    private fun resetGame(){
        score=0
        gameScoreText.text=getString(R.string.yourScore,score)
        val initialTimerCount=initialCount/countDownInterval
        timeLeftText.text=getString(R.string.timeLeft,initialTimerCount)
        countDownTimer=object: CountDownTimer(initialCount,countDownInterval){
            override fun onTick(miliseconds:Long){
                val timeLeft=miliseconds/countDownInterval
                timeLeftText.text=getString(R.string.timeLeft,timeLeft)
            }

            override fun onFinish() {
                endGame()
            }
        }

        gameStarted=false
    }
    private fun incrementScore() {
        if(!gameStarted){
            countDownTimer.start()
            gameStarted=true
        }
        score+=1
        var newScore=getString(R.string.yourScore,score)
        gameScoreText.text=newScore
    }

    private fun endGame(){
        Toast.makeText(this,getString(R.string.toastMessage,score),Toast.LENGTH_LONG)
        gameStarted=false
    }
}