package com.tekraze.kotlinTest

import android.app.Application
import android.content.Context
import com.greengrowapps.ggarest.GgaRest
import com.greengrowapps.jhiusers.JhiUsers
import com.greengrowapps.jhiusers.JhiUsersImpl
import com.tekraze.kotlinTest.core.CustomSerializer
import com.tekraze.kotlinTest.core.Core
import com.tekraze.kotlinTest.core.config.CoreConfiguration

class MyApplication : Application() {

    private lateinit var jhiUsers: JhiUsers

    private lateinit var core: Core

    private lateinit var config: CoreConfiguration



    override fun onCreate() {
        super.onCreate()

        GgaRest.init(this)
        GgaRest.setSerializer(CustomSerializer())

        if(BuildConfig.DEBUG){
            config = CoreConfiguration("http://216.243.63.213:8082")
        }
        else{
            config = CoreConfiguration("http://216.243.63.213:8082")
        }

        jhiUsers = JhiUsersImpl.with(this,config.serverUrl,true,getSharedPreferences("JhiUsers", Context.MODE_PRIVATE))
        core = Core(jhiUsers,config,getSharedPreferences("Core", Context.MODE_PRIVATE),CustomSerializer())

    }

    fun getJhiUsers() : JhiUsers{
        return jhiUsers
    }

    fun getCore() : Core{
        return core
    }

}
