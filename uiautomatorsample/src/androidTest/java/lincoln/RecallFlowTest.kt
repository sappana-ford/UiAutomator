package lincoln

import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import basecomponents.BaseTest
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecallFlowTest : BaseTest() {

    @Before
    override fun setUp() {
        super.setUp()
        uiDevice.wait(Until.hasObject(By.text("Move")), 5000)
    }

    @Test
    fun fromMoveLanding_checkAllThePositiveRecallFlows() {

        //VehicleDetails to list screen
        assertTrue(findUiObjectWithResourceId("move_button").exists())
        clickOnResourceIdAndWaitForNewWindow("vehicle_details_link")
        clickOnResourceIdAndWaitForNewWindow("banner_text_view")
        assertTrue(findUiObjectWithResourceId("recall_fsa_item_title").exists())

        navigateUp()
        navigateUp()

        // Recall peek to Recall details screen
        assertTrue(findUiObjectWithResourceId("move_button").waitForExists(5000))
        clickOnResourceIdAndWaitForNewWindow("recall_peek")
        assertTrue(findUiObjectWithText("Recall Title").exists())
        assertTrue(findUiObjectWithText("DOOR LATCH REPLACEMENT").exists())
        clickOnResourceId("preferred_dealer_search_button")
        assertTrue(findUiObjectWithResourceId("find_button").waitForExists(2000))

        navigateUp()

        assertTrue(findUiObjectWithResourceId("move_button").exists())
    }
}