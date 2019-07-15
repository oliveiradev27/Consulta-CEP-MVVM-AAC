package oliveira.br.consultacepmvvm.application

import android.app.Application
import oliveira.br.consultacepmvvm.di.components.ApplicationComponent
import oliveira.br.consultacepmvvm.di.components.DaggerApplicationComponent
import oliveira.br.consultacepmvvm.di.modules.EnderecoViewModelModule
import oliveira.br.consultacepmvvm.repository.EnderecoNetworkRepository

class MyApplication : Application() {

    companion object {
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder()
            .enderecoViewModelModule(EnderecoViewModelModule(EnderecoNetworkRepository()))
            .build()
        graph.inject(this)
    }
}