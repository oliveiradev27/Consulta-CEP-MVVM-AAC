package oliveira.br.consultacepmvvm.di.modules

import dagger.Module
import dagger.Provides
import oliveira.br.consultacepmvvm.repository.EnderecoNetworkRepository
import oliveira.br.consultacepmvvm.repository.EnderecoRepository
import javax.inject.Singleton

@Module
class EnderecoModule {

    @Provides
    @Singleton
    fun provideEnderecoRepository() : EnderecoRepository = EnderecoNetworkRepository()
}