package com.route.todoc36.ui.home.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.route.todoc36.R
import com.route.todoc36.database.MyDataBase
import com.route.todoc36.database.Task
import com.route.todoc36.databinding.ActivityUpdateTodoBinding
import java.text.SimpleDateFormat
import java.util.*

class UpdateTodo : AppCompatActivity() {

    lateinit var  activityUpdateTodoBinding : ActivityUpdateTodoBinding
    lateinit var task: Task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityUpdateTodoBinding= ActivityUpdateTodoBinding.inflate(layoutInflater)
        setContentView(activityUpdateTodoBinding.root)

        task = ((intent.getSerializableExtra("todo") as? Task)!!)

        //show data
        showData(task)

        activityUpdateTodoBinding.submit.setOnClickListener {
      updateTodo()
        }
    }

    private fun updateTodo(){

        if(isValidForm()==true){
            task!!.title=activityUpdateTodoBinding.titleContainer.editText!!.text.toString()
            task!!.desc = activityUpdateTodoBinding.descContainer.editText!!.text.toString()
            task!!.date = activityUpdateTodoBinding.dateContainer.editText!!.text.toString().toLong()

            MyDataBase.getInstance(this@UpdateTodo)
                .getTasksDao()
                .updateTask(task)


        }


    }

    fun isValidForm():Boolean{
        var isValid = true;
        if(activityUpdateTodoBinding.titleContainer.editText?.text.toString().isNullOrBlank()){
           activityUpdateTodoBinding.titleContainer.error = getString(R.string.please_enter_title);
            isValid = false;
        }else {
           activityUpdateTodoBinding.titleContainer.error=null
        }

        if(activityUpdateTodoBinding.descContainer.editText?.text.toString().isNullOrBlank()){
           activityUpdateTodoBinding.descContainer.error = getString(R.string.please_enter_desc);
            isValid = false;
        }else {
            activityUpdateTodoBinding.descContainer.error=null
        }

        if(activityUpdateTodoBinding.date.text.isNullOrBlank()){
           activityUpdateTodoBinding.dateContainer.error = getString(R.string.please_select_date);
            isValid = false;
        }else {
            activityUpdateTodoBinding.dateContainer.error=null
        }

        return isValid;
    }
    private fun showData(task: Task) {
        activityUpdateTodoBinding.titleContainer.editText!!.setText(task.title)
        Log.e("date" , task.date.toString())
         val date = convertLongToTime(task.date!!)
        Log.e("date" , date.toString())
        activityUpdateTodoBinding.date.setText(date)
        activityUpdateTodoBinding.descContainer.editText!!.setText(task.desc)
        }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }
}
