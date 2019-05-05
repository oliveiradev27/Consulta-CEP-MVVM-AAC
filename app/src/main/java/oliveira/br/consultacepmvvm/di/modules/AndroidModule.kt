package oliveira.br.consultacepmvvm.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import oliveira.br.consultacepmvvm.di.annotations.ForApplication
import javax.inject.Singleton

@Module
class AndroidModule (private val application: Application) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext() : Context = application
}