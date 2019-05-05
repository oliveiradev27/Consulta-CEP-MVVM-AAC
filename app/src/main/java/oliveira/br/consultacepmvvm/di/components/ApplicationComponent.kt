package oliveira.br.consultacepmvvm.di.components

import dagger.Component
import oliveira.br.consultacepmvvm.application.MyApplication
import oliveira.br.consultacepmvvm.di.modules.AndroidModule
import oliveira.br.consultacepmvvm.di.modules.EnderecoModule
import oliveira.br.consultacepmvvm.di.modules.EnderecoViewModelModule
import oliveira.br.consultacepmvvm.ui.activities.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, EnderecoViewModelModule::class, EnderecoModule::class))
interface ApplicationComponent {

    fun inject(application: MyApplication)
    fun inject(mainActivity: MainActivity)

}