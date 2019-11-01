package ford

import basecomponents.BaseTest
import org.junit.Assert.assertTrue
import org.junit.Test

class VehicleDetailsDetailsTest: BaseTest() {

    @Test
    fun fromMoveLanding_checkForTheVinNumberInTheVehicleDetailsDetailsScreen() {
        assertTrue(findUiObjectWithResourceId("move_button").exists())
        clickOnResourceIdAndWaitForNewWindow("vehicle_details_link")
        scrollToObjectWithId("vehicle_details_glove_box_vehicle_details")
        clickOnResourceIdAndWaitForNewWindow("vehicle_details_glove_box_vehicle_details")
        assertTrue(findUiObjectWithResourceId("vin_label_text").exists())
        assertTrue(findUiObjectWithResourceId("vin_text").exists())
        assertTrue(findUiObjectWithText("1FADP3E24DL121004").exists())
    }
}