package com.example.memorygame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.memorygame.databinding.ActivityEasyBinding
import com.example.memorygame.databinding.ActivityNormalBinding

class normalActivity : AppCompatActivity() {
    val listImageochq = arrayOf(false, false, false, false, false, false)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    var animationDoing  =  false
    lateinit var binding: ActivityNormalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNormalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            nazad.setOnClickListener {
                val intent = Intent(this@normalActivity, MainActivity::class.java)
                startActivity(intent)
            }
            img1.setOnClickListener {
                imageClick(img1, R.drawable.pc, 0)
            }
            img2.setOnClickListener {
                imageClick(img2, R.drawable.monitor, 1)
            }
            img3.setOnClickListener {
                imageClick(img3, R.drawable.mouse, 2)
            }
            img4.setOnClickListener {
                imageClick(img4, R.drawable.monitor, 3)
            }
            img5.setOnClickListener {
                imageClick(img5, R.drawable.mouse, 4)
            }
            img6.setOnClickListener {
                imageClick(img6, R.drawable.pc, 5)
            }
        }
    }

    fun imageClick(imageView: ImageView, rasm: Int, index: Int) {
        if (!animationDoing) {

            if (listImageochq[index] == false) {
                animationOpen(imageView, rasm, index)
            } else {
                animationClose(imageView, rasm, index)
            }
        }
    }

    fun animationOpen(imageView: ImageView, rasm: Int, index: Int) {

        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {

                val animation2 = AnimationUtils.loadAnimation(this@normalActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(rasm)

                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {

                        listImageochq[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++
                        if (ochiqRasm == 2) {
                            if (rasmId[0] == rasmId[1]) {

                                imageAniqla(imageIndex[0]).visibility = View.INVISIBLE
                                ochiqRasm--
                                imageAniqla(imageIndex[1]).visibility = View.INVISIBLE
                                ochiqRasm--
                            } else {
                                animationClose(imageAniqla(imageIndex[0]), -1, imageIndex[0])
                                animationClose(imageAniqla(imageIndex[1]), -1, imageIndex[1])
                            }

                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })


            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })


    }

    fun animationClose(imageView: ImageView, rasm: Int, index: Int?) {

        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {

                val animation2 = AnimationUtils.loadAnimation(this@normalActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.stars)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        listImageochq[index!!] = false
        ochiqRasm--
    }

    fun imageAniqla(index: Int?): ImageView {
        var imageView: ImageView? = null
        when (index) {
            0 -> imageView = binding.img1
            1 -> imageView = binding.img2
            2 -> imageView = binding.img3
            3 -> imageView = binding.img4
            4 -> imageView = binding.img5
            5 -> imageView = binding.img6
        }
        return imageView!!
    }
}