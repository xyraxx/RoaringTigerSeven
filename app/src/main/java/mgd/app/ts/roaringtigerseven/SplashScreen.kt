package mgd.app.ts.roaringtigerseven;

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import mgd.app.ts.roaringtigerseven.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var tiger : ImageView
    private lateinit var scratch : ImageView

    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(1024,1024)

        tiger = binding.tigerImg
        scratch = binding.scratchImg

        val anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        tiger.startAnimation(anim)

        val anim2 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val anim3 = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        Handler().postDelayed({
            scratch.visibility = View.VISIBLE
            scratch.startAnimation(anim2)
        }, 1000)

        Handler().postDelayed({
            scratch.visibility = View.INVISIBLE
            scratch.startAnimation(anim3)
        }, 2000)



        /*val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        },2000)*/

    }
}