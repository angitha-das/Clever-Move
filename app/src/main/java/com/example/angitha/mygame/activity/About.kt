package com.example.angitha.mygame.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.angitha.mygame.R
import com.example.angitha.mygame.levels.GameLevels

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_about)
        GameLevels.getInstance().fromMenu = false
    }

    override fun onBackPressed() {
        val i = Intent(this, GameMenuActivity::class.java)
        startActivity(i)
        finish()
    }
}
