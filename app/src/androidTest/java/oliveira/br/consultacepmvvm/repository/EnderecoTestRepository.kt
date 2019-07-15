package oliveira.br.consultacepmvvm.repository

import oliveira.br.consultacepmvvm.interfaces.OnUpdateDataListener
import oliveira.br.consultacepmvvm.models.Endereco
import oliveira.br.consultacepmvvm.utils.JsonUtils

class EnderecoTestRepository : EnderecoRepository {

    override fun getEndereco(cep: String, listener: OnUpdateDataListener<Endereco?>) {
        listener.onSucess(JsonUtils.getAddress())
    }
}