package com.career.careerpath.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.career.careerpath.R
import com.career.careerpath.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        val animation = AnimationUtils.loadAnimation(this, R.anim.animacion)
        binding.imgSplash.startAnimation(animation)

        val intent = Intent(this, MainActivity::class.java)
        animation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
    }
}