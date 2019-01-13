package oliveira.br.consultacepmvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import oliveira.br.consultacepmvvm.repository.EnderecoRepository

class EnderecoViewModelFactory(private val repository: EnderecoRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = EnderecoViewModel(repository) as T
}