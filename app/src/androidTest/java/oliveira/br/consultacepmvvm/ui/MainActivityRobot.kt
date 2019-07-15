package oliveira.br.consultacepmvvm.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import oliveira.br.consultacepmvvm.R
import oliveira.br.consultacepmvvm.application.MyApplication
import oliveira.br.consultacepmvvm.di.components.DaggerApplicationComponent
import oliveira.br.consultacepmvvm.di.modules.EnderecoViewModelModule
import oliveira.br.consultacepmvvm.repository.EnderecoTestRepository
import oliveira.br.consultacepmvvm.ui.MainActivityRobotCostants.BUTTON_GET_CEP_ID
import oliveira.br.consultacepmvvm.ui.activities.MainActivity

object MainActivityRobotCostants {
    const val BUTTON_GET_CEP_ID = R.id.btn_cep
}

class MainActivityPrepare(private val activityTestRule: ActivityTestRule<MainActivity>) {

    internal fun initializeAcitivity() {
        activityTestRule.launchActivity(null)
    }

    internal fun mockDependecies() {
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

class MainActivityExecute {

    internal fun writeNumberCep(cep: String) =
        onView(withId(R.id.et_cep))
            .perform(scrollTo(), typeText(cep))

    internal fun clickButtonCepSearch() =
        onView(withId(BUTTON_GET_CEP_ID))
            .perform(scrollTo(), click())

}

class MainActivityValidate {

    internal fun checkWithButtonCepIsVisible() = onView(withId(BUTTON_GET_CEP_ID)).check(matches(isDisplayed()))

    internal fun checkWithInfoAddressChanged() =
        onView(withId(R.id.cl_container_address_info)).check(matches(isDisplayed()))

    internal fun checkValueLogradouroInfo() =
        onView(withId(R.id.logradouro)).perform(scrollTo()).check(matches(withText("Praça da Sé")))
}