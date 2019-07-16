package oliveira.br.consultacepmvvm

import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_endereco.view.*
import oliveira.br.consultacepmvvm.application.MyApplication
import oliveira.br.consultacepmvvm.di.components.DaggerApplicationComponent
import oliveira.br.consultacepmvvm.di.modules.EnderecoViewModelModule
import oliveira.br.consultacepmvvm.repository.EnderecoTestUnitRepository
import oliveira.br.consultacepmvvm.ui.activities.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityRoboletricTest {

    @Test
    fun clickingButtonSearchZip_shouldLoadAddressData() {
        mockDependecies()

        val activity = Robolectric.setupActivity(MainActivity::class.java)
        activity.et_cep.setText("08280440")
        activity.btn_cep.performClick()
        assert(activity.fragment_endereco.logradouro.text.toString() == "Praça da Sé")
    }

    fun mockDependecies() {
        val app = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext as MyApplication

        MyApplication.graph = DaggerApplicationComponent
            .builder()
            .enderecoViewModelModule(EnderecoViewModelModule(EnderecoTestUnitRepository()))
            .build()
        MyApplication.graph.inject(app)
    }
}