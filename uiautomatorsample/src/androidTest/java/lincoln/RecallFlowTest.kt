package lincoln

import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import basecomponents.BaseTest
import junit.framework.Assert.assertTrue
import org.junit.Test

class RecallFlowTest : BaseTest() {

    @Test
    fun fromMoveLanding_checkAllThePositiveRecallFlows() {
        uiDevice.wait(Until.hasObject(By.text("Move")), 5000)

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