package oliveira.br.consultacepmvvm.interfaces

interface OnUpdateDataListener<T> {

    fun onSucess(data : T)
    fun onFailured(error: String?)
}