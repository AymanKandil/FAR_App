package com.example.helloar

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType

class AppIntro : AppIntro() {


    lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)


        preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        val FirstTime = preferences.getString("FirstTimeInstall", "")
        if (FirstTime == "Yes")
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            val editor = preferences.edit()
            editor.putString("FirstTimeInstall", "Yes")
            editor.apply()
        }




        addSlide(AppIntroFragment.newInstance("Plane Detection","To begin with, ARCore need to scan the environment to allow placing of virtual Furniture. Follow the animated hand until you see small dots!",R.drawable.scanpic,getColor(R.color.secondcolor)))
        addSlide(AppIntroFragment.newInstance("Furniture Menu","Once the small dots are shown you then can pick any of the virtual furniture! Just tap on the button on the lower right corner of the screen and choose from the menu!",R.drawable.menupic,getColor(R.color.thirdcolor)))
        addSlide(AppIntroFragment.newInstance("Placing Furniture","Once you select something from the menu, just tap on the screen and the Furniture will be placed! You can move it around by tapping on it and moving it across the screen. Use pinch to change the size of furniture after selecting it. To rotate it, keep one finger on the screen and rotate with the other. Have Fun designing!",R.drawable.deskpic,getColor(R.color.fourthcolor)))


        setTransformer(AppIntroPageTransformerType.Fade)
        isIndicatorEnabled = true
        isColorTransitionsEnabled = true
        setIndicatorColor(
                selectedIndicatorColor = getColor(R.color.colorAccent),
                unselectedIndicatorColor = getColor(R.color.Black)
        )




    }
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        val intent2 = Intent(this, MainActivity::class.java)
        startActivity(intent2)
        finish()
    }
}