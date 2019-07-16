package oliveira.br.consultacepmvvm

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_endereco.view.*
import oliveira.br.consultacepmvvm.application.MyApplication
import oliveira.br.consultacepmvvm.di.components.DaggerApplicationComponent
import oliveira.br.consultacepmvvm.di.modules.EnderecoViewModelModule
import oliveira.br.consultacepmvvm.repository.EnderecoTestUnitRepository
import oliveira.br.consultacepmvvm.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityAndroidXTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickingButtonSearchZip_shouldLoadAddressData() {
        mockDependecies()
        val scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->
            activity.et_cep.setText("08280440")
            activity.btn_cep.performClick()
            assert(activity.fragment_endereco.logradouro.text.toString() == "Praça da Sé")
        }
    }

    private fun mockDependecies() {
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