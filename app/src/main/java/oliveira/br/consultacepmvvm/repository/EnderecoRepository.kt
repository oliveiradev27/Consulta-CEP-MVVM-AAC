package oliveira.br.consultacepmvvm.repository

import oliveira.br.consultacepmvvm.interfaces.OnUpdateDataListener
import oliveira.br.consultacepmvvm.models.Endereco

interface EnderecoRepository {
    fun getEndereco(cep : String, listener : OnUpdateDataListener<Endereco?>)
}