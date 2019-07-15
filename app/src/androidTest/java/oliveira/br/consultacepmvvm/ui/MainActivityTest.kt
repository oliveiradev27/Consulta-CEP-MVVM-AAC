package oliveira.br.consultacepmvvm.ui

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import oliveira.br.consultacepmvvm.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, false, false)

    private fun prepare(func: MainActivityPrepare.() -> Unit) = MainActivityPrepare(
        rule
    ).apply { func() }

    private fun execute(func: MainActivityExecute.() -> Unit) = MainActivityExecute().apply { func() }
    private fun validate(func: MainActivityValidate.() -> Unit) = MainActivityValidate().apply { func() }

    @Test
    fun toInitializeActivity_sholdApppearComponents() {
        prepare {
            initializeAcitivity()
        }
        validate {
            checkWithButtonCepIsVisible()
        }
    }

    @Test
    fun whenFillInTheZipFieldAndClickTheSearchButton_shouldLoadDataFromAddress() {
        prepare {
            mockDependecies()
            initializeAcitivity()
        }
        execute {
            writeNumberCep("01001-000")
            clickButtonCepSearch()
        }
        validate {
            checkWithInfoAddressChanged()
            checkValueLogradouroInfo()
        }
    }
}

