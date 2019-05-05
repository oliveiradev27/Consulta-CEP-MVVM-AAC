package oliveira.br.consultacepmvvm.ui.activities

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import oliveira.br.consultacepmvvm.viewmodels.EnderecoViewModel
import oliveira.br.consultacepmvvm.R
import oliveira.br.consultacepmvvm.application.MyApplication
import oliveira.br.consultacepmvvm.models.Endereco
import oliveira.br.consultacepmvvm.ui.fragments.EnderecoFragment
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
                hideProgressDialog()
                if (endereco == null)
                    showSearchError()
                configuraExibicaoEndereco(endereco) })

        btn_cep.setOnClickListener{
            if (!et_cep.text.isNullOrEmpty()) {
                viewModel.consultar(et_cep.text.toString())
                showProgressDialog()
            } else {
                et_cep.error = getString(R.string.campo_obrigatorio)
                et_cep.requestFocus()
            }
        }
    }

    private fun showProgressDialog() {
        linear_form.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    private fun hideProgressDialog() {
        linear_form.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }

    private fun configuraExibicaoEndereco(endereco : Endereco?) {
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
            .setAction(getString(R.string.tentar_novamente)) { btn_cep.performClick() }.show()
    }
}
