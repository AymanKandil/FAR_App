package com.example.helloar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType

class AppIntro : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        addSlide(AppIntroFragment.newInstance("Sign In/ Sign up","First step to use the app is creating an account for security purposes, Just type in your email and create a password then hit sign up! If you are returning user just sign in.",R.drawable.startpic,Color.BLACK,Color.WHITE,Color.WHITE))
        addSlide(AppIntroFragment.newInstance("Plane Detection","After Signing in, ARCore need to scan the environment to allow placing of virtual Furniture. Follow the animated hand until you see small dots!",R.drawable.scanpic,Color.BLACK,Color.WHITE,Color.WHITE))
        addSlide(AppIntroFragment.newInstance("Side Menu","Once the small dots are shown you then can pick any of the virtual furniture! Just swipe from the left side and choose from the menu!",R.drawable.menupic, Color.BLACK,Color.WHITE,Color.WHITE))
        addSlide(AppIntroFragment.newInstance("Placing Furniture","Now you are Ready to place the Furniture! Just tap on the screen and the Furniture will be placed! You can move it around by tapping on it and moving it across the screen. Use pinch to change the size of furniture after selecting it. To rotate it, keep one finger on the screen and rotate with the other. Have Fun designing!",R.drawable.deskpic,Color.BLACK,Color.WHITE,Color.WHITE))


        setTransformer(AppIntroPageTransformerType.Fade)
        isIndicatorEnabled = true
        setIndicatorColor(
                selectedIndicatorColor = getColor(R.color.colorAccent),
                unselectedIndicatorColor = getColor(R.color.white)
        )




    }
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        val intent2 = Intent(this, SignupActivity::class.java)
        startActivity(intent2)
        finish()
    }
}