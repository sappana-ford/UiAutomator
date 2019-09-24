package ford

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