package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class SignUpScreen : AppCompatActivity() {

    lateinit var name:EditText
    lateinit var fam:EditText
    lateinit var mail:EditText
    lateinit var pass:EditText
    lateinit var secpass:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        name= findViewById(R.id.edit_name)
        fam=findViewById(R.id.edit_fam)
        mail=findViewById(R.id.edit_mail)
        pass=findViewById(R.id.edit_pass)
        secpass=findViewById(R.id.edit_secpass)
    }

    fun newacc_click(view: View) {
        if(name.length()>1&&fam.length()>1&&mail.length()>1&&pass.length()>1&&secpass.length()>1 && pass.length() == secpass.length())
        {
            val intent = Intent(this,SignInScreen::class.java)
            startActivity(intent)
            val toast = Toast.makeText(applicationContext,"Вы успешно зарегистрированы!",Toast.LENGTH_SHORT)
            toast.show()
        }

    }
    fun ihaveacc_click(view: View) {
        val intent = Intent(this,SignInScreen::class.java)
        startActivity(intent)
    }
}