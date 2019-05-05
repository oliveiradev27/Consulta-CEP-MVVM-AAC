package oliveira.br.consultacepmvvm.services

import oliveira.br.consultacepmvvm.models.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("{cep}/json/")
    fun getEndereco(@Path("cep") cep : String): Call<Endereco>
}