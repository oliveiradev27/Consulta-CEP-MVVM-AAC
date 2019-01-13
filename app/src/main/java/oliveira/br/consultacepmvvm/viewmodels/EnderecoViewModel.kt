package oliveira.br.consultacepmvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import oliveira.br.consultacepmvvm.interfaces.OnUpdateDataListener
import oliveira.br.consultacepmvvm.models.Endereco
import oliveira.br.consultacepmvvm.repository.EnderecoRepository

class EnderecoViewModel(private val repository: EnderecoRepository) : ViewModel() {

    private var enderecoLiveData  = MutableLiveData<Endereco>()

    fun getEndereco() : MutableLiveData<Endereco> = enderecoLiveData

    fun consultar(cep: String)  {
        enderecoLiveData.value.let { endereco -> if (cep == endereco?.cep) return }

        repository.getEndereco(cep, object : OnUpdateDataListener<Endereco?> {
           override fun onSucess(data : Endereco?)  {
               if (data?.logradouro != null)
                    enderecoLiveData.value = data
               else
                   enderecoLiveData.value = null
           }

            override fun onFailured(error: String?) {
                enderecoLiveData.value = null
            }
        })
    }
}