package oliveira.br.consultacepmvvm.utils

import oliveira.br.consultacepmvvm.models.Endereco

object JsonUtils {

    fun getAddress(): Endereco {
        return Endereco(
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
    }
}

