package com.tekraze.kotlinTest

import android.os.Bundle
import android.widget.Toast

import com.greengrowapps.jhiusers.listeners.OnLoginListener


class SplashActivity : BaseActivity(), OnLoginListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val users = getJhiUsers()

        if(users.isLoginSaved){
            users.autoLogin(this)
        }

        else{
            toLoginActivity()
        }
    }

    override fun onLoginSuccess() {
        toMainActivity()
    }

    private fun toMainActivity() {
        startActivity(MainActivity.clearTopIntent(this))
    }

    override fun onLoginError(error: String?) {
        Toast.makeText(this,getString(R.string.loginErrorMsg), Toast.LENGTH_SHORT).show()
        toLoginActivity()
    }

    private fun toLoginActivity() {
        startActivity(LoginActivity.clearTopIntent(this))
    }
}
