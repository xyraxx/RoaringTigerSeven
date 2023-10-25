package mgd.app.ts.roaringtigerseven

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class RTSConfig : Application() {

    private var hasUserConsent = false

    companion object {
        private var GAME_URL = ""
        private var APP_CODE = "777T104"
        private var USER_CONSENT = "userConsent"
    }

    override fun onCreate() {
        super.onCreate()
        val prefs: SharedPreferences = getSharedPreferences(APP_CODE, Context.MODE_PRIVATE)
        hasUserConsent = prefs.getBoolean(USER_CONSENT, false)
    }

    fun remoteConfigSetup(context: Context, activity : Activity, hasFirebase : Boolean){

        if(hasFirebase){
            FirebaseApp.initializeApp(context)
            val remoteConfig = FirebaseRemoteConfig.getInstance()
            val rcSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build()
            remoteConfig.setConfigSettingsAsync(rcSettings)
            remoteConfig.fetchAndActivate()
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        GAME_URL = remoteConfig.getString("apiURL") + "?appid=" + APP_CODE
                    }
                }
        }

    }

    fun checkConsent(context: Context, activity: Activity, hasPolicy:Boolean){
        remoteConfigSetup(context, activity, true)

        if(!hasUserConsent && hasPolicy){
            showConsentDialog(context, activity)
        }
    }

    private fun showConsentDialog(context: Context, activity: Activity){
        val builder = AlertDialog.Builder(context)

        val consentDialog : View = LayoutInflater.from(context).inflate(R.layout.consent_dialog, null)

        val consentWV : WebView = consentDialog.findViewById(R.id.consentWV)

        val policyURL = ""


    }

}