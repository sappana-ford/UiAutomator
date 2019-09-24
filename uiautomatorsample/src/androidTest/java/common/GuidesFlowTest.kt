package common

import basecomponents.BaseTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import basecomponents.APPLICATION_ID
import basecomponents.FIRST_NAME
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GuidesFlowTest : BaseTest() {

    @Before
    override fun setUp() {
        super.setUp()
        uiDevice.wait(Until.hasObject(By.res(APPLICATION_ID,"guides_button")), 2000)
    }

    @Test
    fun fromGuides_checkAllThePositiveFlows() {
        clickOnResourceIdAndWaitForNewWindow("guides_button")
        assertTrue(findUiObjectWithResourceId("guide_chat_image").exists())
        assertTrue(findUiObjectWithResourceId("guide_call_image").exists())
        assertTrue(findUiObjectWithResourceId("guide_email_image").exists())
        findUiObjectWithResourceId("help_search_view").apply {
            assertTrue(exists())
            assertTrue(FIRST_NAME.toUpperCase() in text)
        }
        findUiObjectWithResourceId("popular_topics").apply {
            waitForExists(1000)
            assertTrue(text.equals("My Vehicles", true))
        }
        clickOnResourceIdAndWaitForNewWindow("guides_faq_text")
        assertTrue(findUiObjectWithText("Ask your question here").waitForExists(1000))
        navigateUp()
        findUiObjectWithResourceId("guides_button").exists()
        clickOnResourceIdAndWaitForNewWindow("change_popular_topics")
        clickOnTextAndWaitForNewWindow("Account")
        findUiObjectWithText("Forgot Password FordPass").waitForExists(2000)
        clickOnResourceId("guide_chat_image")
        findUiObjectWithResourceId("common_dialog").apply {
            waitForExists(2000)
            assertTrue(hasResourceId("common_dialog_content"))
            assertTrue(hasResourceId("common_dialog_image"))
            assertTrue(hasResourceId("common_dialog_title"))
        }
        navigateUp()
        clickOnResourceIdAndWaitForNewWindow("guide_call_image")
        findUiObjectWithResourceId("call_guide_estimated_time_header").exists()
        navigateUp()
        navigateUp()
        clickOnResourceIdAndWaitForNewWindow("guide_email_image")
        findUiObjectByCustomResourceId("com.google.android.gm:id/gmail_logo")
    }
}