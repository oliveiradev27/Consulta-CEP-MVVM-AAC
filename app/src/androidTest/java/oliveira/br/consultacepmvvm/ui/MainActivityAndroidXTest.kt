package oliveira.br.consultacepmvvm.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import oliveira.br.consultacepmvvm.R

import oliveira.br.consultacepmvvm.application.MyApplication
import oliveira.br.consultacepmvvm.di.components.DaggerApplicationComponent
import oliveira.br.consultacepmvvm.di.modules.EnderecoViewModelModule
import oliveira.br.consultacepmvvm.repository.EnderecoTestRepository
import oliveira.br.consultacepmvvm.ui.activities.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityAndroidXTest {

    @Test
    fun clickingButtonSearchZip_shouldLoadAddressData() {
        mockDependecies()

        val scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.et_cep)).perform(typeText("08280440"))
        onView(withId(R.id.btn_cep)).perform(click())
        onView(withId(R.id.logradouro)).check(matches(withText("Praça da Sé")))
    }

    @Test
    fun clickingButtonSearchZip_shouldLoadAddressData2() {
        mockDependecies()

        val scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->
            onView(withId(R.id.et_cep)).perform(typeText("08280440"))
            onView(withId(R.id.btn_cep)).perform(click())
            onView(withId(R.id.logradouro)).check(matches(withText("Praça da Sé")))
        }
    }

    private fun mockDependecies() {
        val app = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext as MyApplication

        MyApplication.graph = DaggerApplicationComponent
            .builder()
            .enderecoViewModelModule(EnderecoViewModelModule(EnderecoTestRepository()))
            .build()
        MyApplication.graph.inject(app)
    }

}