package oliveira.br.consultacepmvvm.repository

import android.util.Log
import oliveira.br.consultacepmvvm.models.Endereco
import oliveira.br.consultacepmvvm.services.NetworkAPI
import oliveira.br.consultacepmvvm.interfaces.OnUpdateDataListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnderecoNetworkRepository : EnderecoRepository {

    override fun getEndereco(cep : String, listener: OnUpdateDataListener<Endereco?>) {
        val call = NetworkAPI.getEndereco(cep)
       call.enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                listener.onSucess(response.body())
            }

            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                Log.e("ConsultaCEP", t.message)
                listener.onFailured(t.message)
            }
        })
    }

}