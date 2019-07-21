package oliveira.br.consultacepmvvm

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
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
        onView(withId(R.id.btn_cep)).check(matches(withText( "Consultar")))
        onView(withId(R.id.btn_cep)).perform(scrollTo(), click())
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