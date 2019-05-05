package oliveira.br.consultacepmvvm.application

import android.app.Application
import oliveira.br.consultacepmvvm.di.components.ApplicationComponent
import oliveira.br.consultacepmvvm.di.components.DaggerApplicationComponent
import oliveira.br.consultacepmvvm.di.modules.AndroidModule

class MyApplication : Application() {

    companion object {
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        graph.inject(this)
    }
}