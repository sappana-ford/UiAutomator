package ford

import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import basecomponents.BaseTest
import junit.framework.Assert.assertFalse
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
        assertTrue(findUiObjectWithResourceId("recall_fsa_list_title").exists())
        assertTrue(findUiObjectWithResourceId("recall_fsa_title").exists())
        assertTrue(findUiObjectWithResourceId("recall_fsa_title").exists())

        navigateUp()
        navigateUp()

        // Recall peek to List screen
        assertTrue(findUiObjectWithResourceId("move_button").waitForExists(5000))
        clickOnResourceIdAndWaitForNewWindow("recall_peek")
        assertTrue(findUiObjectWithResourceId("recall_fsa_list_title").exists())
        assertTrue(findUiObjectWithResourceId("recall_fsa_title").exists())
        assertTrue(findUiObjectWithResourceId("recall_fsa_title").exists())

        // Recall list to Recall details screen
        clickOnTextAndWaitForNewWindow("SIDE DOOR LATCH REPLACEMENT")
        assertTrue(findUiObjectWithText("Recall Title").exists())

        navigateUp()

        // Recall list to FSA details screen
        clickOnTextAndWaitForNewWindow("TRANSMISSION CLUTCH SHUDDER / TRANSMISSION INPUT SHAFT SEAL WARRANTY EXTENSION")
        assertFalse(findUiObjectWithText("Recall Title").exists())
        assertTrue(findUiObjectWithText("FSA Title").exists())
        assertTrue(findUiObjectWithResourceId("preferred_dealer_search_button").exists())
        clickOnResourceId("preferred_dealer_search_button")
        assertTrue(findUiObjectWithResourceId("find_button").waitForExists(2000))
    }
}