package basecomponents

import PASSWORD
import SELECTED_COUNTRY
import TARGET_APPLICATION_ID
import USER_NAME
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    protected var uiDevice: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    init {
        uiDevice.pressHome()
        uiDevice.wait(Until.hasObject(By.pkg(TARGET_APPLICATION_ID)), 5000)
        startApplication()
        initializeApp()
    }

    open fun setUp() {
        startApplication()
    }

    private fun startApplication() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val intent = context.packageManager.getLaunchIntentForPackage(TARGET_APPLICATION_ID)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        allowPermission()
    }

    private fun initializeApp() {
        val byText: BySelector = By.text(SELECTED_COUNTRY)
        uiDevice.wait(Until.hasObject(byText), 5000)
        if (uiDevice.hasObject(byText)) {
            val usSelector = uiDevice.findObject(UiSelector().text(SELECTED_COUNTRY))
            usSelector.click()

            clickOnResourceIdAndWaitForNewWindow("next_button")
            login()
            skipTutorial()
            allowPermission()
        }
        if (hasResourceId("remember_username")) {
            login()
            skipTutorial()
            allowPermission()
        }
    }

    private fun skipTutorial() {
        findUiObjectWithResourceId("video_player_skip").apply {
            if (waitForExists(2000)) clickAndWaitForNewWindow()
        }
    }

    private fun login() {
        if (findUiObjectWithResourceId("login_username_text").exists()) {
            findUiObjectWithResourceId("login_username_text").text = USER_NAME
            findUiObjectWithResourceId("login_password_text").text = PASSWORD
            findUiObjectWithResourceId("login_button_login").clickAndWaitForNewWindow()
            findUiObjectWithResourceId("accept_button").apply {
                if (waitForExists(2000)) clickAndWaitForNewWindow()
            }
            enterPin()
            enterPin()
        }
    }

    private fun allowPermission() {
        repeat(2) {
            findUiObjectByCustomResourceId("com.android.packageinstaller:id/permission_allow_button").apply { if (exists()) clickAndWaitForNewWindow() }
        }
    }

    private fun enterPin() {
        repeat(3) {
            clickOnResourceId("widget_pin_pad_button_one")
        }
        clickOnResourceIdAndWaitForNewWindow("widget_pin_pad_button_two")
    }

    protected fun findUiObjectByCustomResourceId(id: String): UiObject = uiDevice.findObject(UiSelector().resourceId(id))

    protected fun clickOnResourceId(resourceId: String) = findUiObjectByCustomResourceId("$TARGET_APPLICATION_ID:id/$resourceId").click()

    protected fun clickOnResourceIdAndWaitForNewWindow(resourceId: String) = findUiObjectByCustomResourceId("$TARGET_APPLICATION_ID:id/$resourceId").clickAndWaitForNewWindow()

    protected fun clickOnTextAndWaitForNewWindow(text: String) = findUiObjectWithText(text).clickAndWaitForNewWindow()

    protected fun clickOnText(text: String) = findUiObjectWithText(text).click()

    protected fun findUiObjectWithResourceId(resourceId: String): UiObject = findUiObjectByCustomResourceId("$TARGET_APPLICATION_ID:id/$resourceId")

    protected fun findUiObjectWithText(text: String): UiObject = uiDevice.findObject(UiSelector().text(text))

    protected fun findUiObjectWithTextContains(text: String): UiObject = uiDevice.findObject(UiSelector().textContains(text))

    protected fun hasResourceId(resourceId: String): Boolean = uiDevice.hasObject(By.res(
        TARGET_APPLICATION_ID, resourceId))

    protected fun navigateUp() = uiDevice.pressBack()

    protected fun scrollToObjectWithId(id: String) {
        val scrollView = UiScrollable(UiSelector().scrollable(true))
        scrollView.scrollIntoView(UiSelector().resourceId(id))
    }
}
