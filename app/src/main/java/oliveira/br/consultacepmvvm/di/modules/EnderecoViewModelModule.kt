package oliveira.br.consultacepmvvm.di.modules

import dagger.Module
import dagger.Provides
import oliveira.br.consultacepmvvm.repository.EnderecoRepository
import oliveira.br.consultacepmvvm.viewmodels.EnderecoViewModel

@Module
class EnderecoViewModelModule {

    @Provides
    fun provideViewModel(repository: EnderecoRepository)  : EnderecoViewModel = EnderecoViewModel(repository)
}