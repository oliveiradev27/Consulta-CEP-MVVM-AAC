package oliveira.br.consultacepmvvm.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import oliveira.br.consultacepmvvm.R
import oliveira.br.consultacepmvvm.application.MyApplication
import oliveira.br.consultacepmvvm.models.Endereco
import oliveira.br.consultacepmvvm.ui.fragments.EnderecoFragment
import oliveira.br.consultacepmvvm.viewmodels.EnderecoViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager
    private var fragment : EnderecoFragment? = null

    @Inject
    lateinit var viewModel : EnderecoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApplication.graph.inject(this)

        viewModel
            .getEndereco()
            .observe(this, Observer<Endereco>{ endereco ->
                if (endereco == null)
                    showSearchError()
                configuraExibicaoEndereco(endereco)
            })

        btn_cep.setOnClickListener{
            fragment.let { it?.configureLoadingState() }
            consultarCep()
        }
    }

    private fun consultarCep() {
        if (!et_cep.text.isNullOrEmpty()) {
            viewModel.consultar(et_cep.text.toString())
            showProgressDialog()
        } else {
            et_cep.error = getString(R.string.campo_obrigatorio)
            et_cep.requestFocus()
        }
    }

    private fun showProgressDialog() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgressDialog() {
        progress.visibility = View.GONE
    }

    private fun configuraExibicaoEndereco(endereco : Endereco?) {
        hideProgressDialog()
        if (fragment == null) {
            val transaction = manager.beginTransaction()
            fragment = EnderecoFragment.newInstance(endereco)
            transaction.replace(R.id.fragment_endereco, fragment!!)
            transaction.commit()
        } else {
            fragment!!.bindData(endereco)
        }
    }

    private fun showSearchError() {
        Snackbar.make(main_content, getString(R.string.dados_nao_encontrados), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.tentar_novamente)) { consultarCep() }.show()
    }
}
