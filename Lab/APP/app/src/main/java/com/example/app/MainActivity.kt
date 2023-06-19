package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.apptest.AppDatabase
import com.example.apptest.User
import com.example.finaltest.LoginViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private  var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_login:Button=findViewById(R.id.btn_login)
        val btn_reg:Button=findViewById(R.id.btn_reg)
        val edit_ac:EditText=findViewById(R.id.edit_ac)
        val edit_pwd:EditText=findViewById(R.id.edit_pwd)


        btn_login.setOnClickListener {
            runBlocking {
                user= AppDatabase.getDatabase(this@MainActivity).userDao().getUserByUsernameAndPassword(edit_ac.text.toString(), edit_pwd.text.toString())
            }

            if(user!=null) {
             Toast.makeText(this,"Welcome "+ user!!.name+" user",   Toast.LENGTH_SHORT).show()
                pagechange(Intent(this, LoginedFragment::class.java))
            }else{
                Toast.makeText(this,"Worng user,Please Input correct user",Toast.LENGTH_LONG).show()
            }
        }
        btn_reg.setOnClickListener {
            pagechange( Intent(this, Register_Fragment::class.java))
        }


    }
    fun pagechange(intent: Intent){
        startActivity(intent)
    }

}