package com.route.todoc36.ui.home.settings

import android.content.Context
import android.content.SharedPreferences

class SettingsHelper {

    companion object{
       var  sharedPreferences: SharedPreferences?=null
     //  lateinit var editor : SharedPreferences.Editor


        fun getSharedpreferenceInstance(context: Context): SharedPreferences{
            if(sharedPreferences==null){
           sharedPreferences = context.
            getSharedPreferences("", Context.MODE_PRIVATE)




            }


            return sharedPreferences!!
        }

        public  fun putData(context: Context , data:String){
            var editor = getSharedpreferenceInstance(context).edit()
            editor.putString("lang", data)
            editor.apply()
            editor.commit()

        }
    }


}