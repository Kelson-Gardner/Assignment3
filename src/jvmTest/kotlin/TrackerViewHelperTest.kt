import kotlin.test.Test
import kotlin.test.assertEquals

class TrackerViewHelperTest {
    val notes = arrayOf<String>("This is a test note")
    val shippingUpdate = ShippingUpdate("created", "shipped", 1652712855468)
    val updateHistory = arrayOf(shippingUpdate)
    val location = "unknown"
    val shipment = Shipment(
        "delayed",
        "12345",
        notes,
        updateHistory,
        0,
        location)
    val trackerViewHelper = TrackerViewHelper()
    @Test
    fun testUpdate(){
        trackerViewHelper.update(shipment)
        assertEquals(trackerViewHelper.shipmentId.value, "12345")
        assertEquals(trackerViewHelper.shipmentNotes.value[0], "This is a test note")
        assertEquals(trackerViewHelper.shipmentUpdateHistory.value[1], "Shipment went from created to shipped at 2022-05-16 08:54:15")
        assertEquals(trackerViewHelper.expectedShipmentDeliveryDate.value, "1969-12-31 17:00:00")
        assertEquals(trackerViewHelper.shipmentStatus.value, "delayed")
        assertEquals(trackerViewHelper.shipmentLocation.value, "unknown")
    }

    @Test
    fun testFormatShipmentUpdateHistory(){
        trackerViewHelper.update(shipment)
        assertEquals(trackerViewHelper.shipmentUpdateHistory.value[1], "Shipment went from created to shipped at 2022-05-16 08:54:15")
    }
    @Test
    fun testFormatTimeStamp(){
        trackerViewHelper.update(shipment)
        assertEquals(trackerViewHelper.expectedShipmentDeliveryDate.value, "1969-12-31 17:00:00")
    }
}