package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.apptest.AppDatabase
import com.example.apptest.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Register_Fragment :AppCompatActivity() {
    //    private lateinit var viewModel: RegisterViewModel
    private  var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)
        val reg_ac: EditText =findViewById(R.id.Account1_text)
        val reg_pwd: EditText =findViewById(R.id.password1_text)
        val btn_register: Button =findViewById(R.id.over)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btn_register.setOnClickListener {

            if(reg_ac.text.toString().length>1 && reg_pwd.text.toString().length>1 ){

                GlobalScope.launch {
                    user= AppDatabase.getDatabase(applicationContext).userDao().getUserByUsernameAndPassword(reg_ac.text.toString(), reg_pwd.text.toString())
                     if(user==null){
                    AppDatabase.getDatabase(applicationContext).userDao().insertUser(User( AppDatabase.getDatabase(applicationContext).userDao().getUserCount()+1,reg_ac.text.toString(),reg_pwd.text.toString()))
                    homePage()
                  }
                }


            }else{

                Toast.makeText(this,"Please Intput data", LENGTH_LONG).show()}

        }
    }

    fun homePage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}