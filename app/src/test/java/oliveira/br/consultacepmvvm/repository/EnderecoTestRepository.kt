package oliveira.br.consultacepmvvm.repository

import oliveira.br.consultacepmvvm.interfaces.OnUpdateDataListener
import oliveira.br.consultacepmvvm.models.Endereco

class EnderecoTestUnitRepository : EnderecoRepository {

    override fun getEndereco(cep: String, listener: OnUpdateDataListener<Endereco?>) {
        listener.onSucess(
            Endereco(
                "01001-000",
                "Praça da Sé",
                "lado ímpar",
                "Sé",
                "São Paulo",
                "SP",
                "",
                "3550308",
                1004
            )
        )
    }
}