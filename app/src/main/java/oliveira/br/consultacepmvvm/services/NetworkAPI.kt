package oliveira.br.consultacepmvvm.services

import oliveira.br.consultacepmvvm.constants.APIConstants
import oliveira.br.consultacepmvvm.models.Endereco
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkAPI {

    var retrofit = Retrofit.Builder()
        .baseUrl(APIConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getEndereco(cep : String) : Call<Endereco> {
        val service = retrofit.create(EnderecoService::class.java)
        return service.getEndereco(cep)
    }

}