package com.route.todoc36

import android.content.Context
import android.content.SharedPreferences

class LanguagesSettingsHelper {

    companion object{
        var sharedPreferences:SharedPreferences?=null


       private fun getSharedPreferencesInstance(context: Context):SharedPreferences{
            if(sharedPreferences==null){
                sharedPreferences =  context.getSharedPreferences("lang", Context.MODE_PRIVATE)
            }
            return sharedPreferences!!
        }

        public fun putData(lang:String,context: Context){

            var editor = getSharedPreferencesInstance(context).edit()
            editor.putString("lang",lang)

            editor.commit()
        }

        public fun retreiveDataFromSharedPreferences(key: String, context: Context): String{
            var data = getSharedPreferencesInstance(context).getString(key,"en")
            return data!!
        }

    }



}