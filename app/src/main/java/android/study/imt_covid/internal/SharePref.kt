//package android.study.imt_covid.internal
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.content.res.Configuration
//import java.util.*
//
//const val PREF_NAME = "ChangeLanguage"
//const val PREF_LANG = "Language"
//class SharePref(context: Context){
//    private val preference: SharedPreferences = context.getSharedPreferences(
//        PREF_NAME,
//        Context.MODE_PRIVATE
//    )
//
//    fun getLang(): String{
//        return preference.getString(PREF_LANG, "en")!!
//    }
//    fun setLang(Language: String){
////        val editor = preference.edit()
////        editor.apply{
////            editor.putString(PREF_LANG, Language)
////        }.apply()
//        val session = Sessions(context)
//        //Log.e("Lan",session.getLanguage());
//        val locale: Locale = Locale(langCode)
//        val config = Configuration(context.getResources().getConfiguration())
//        Locale.setDefault(locale)
//        config.setLocale(locale)
//        context.getBaseContext().getResources().updateConfiguration(
//            config,
//            context.getBaseContext().getResources().getDisplayMetrics()
//        )
//    }
//}