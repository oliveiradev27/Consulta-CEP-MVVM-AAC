package oliveira.br.consultacepmvvm.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Endereco(
    var cep: String,
    var logradouro: String,
    var complemento : String,
    var bairro : String,
    var localidade: String,
    var uf: String,
    var unidade : String,
    var ibge : String,
    var gia : Int) : Parcelable