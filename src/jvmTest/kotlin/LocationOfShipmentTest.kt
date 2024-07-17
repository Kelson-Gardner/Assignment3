import kotlin.test.Test
import kotlin.test.assertEquals

class LocationShipmentTest {
    val shipmentUpdate = LocationOfShipment()
    val notes = arrayOf<String>()
    val updateHistory = arrayOf<ShippingUpdate>()
    val location = "unknown"
    val shipment = Shipment(
        "shipped",
        "12345",
        notes,
        updateHistory,
        0,
        location)

    @Test
    fun testNoUpdateOfShipmentStatus(){
        shipmentUpdate.updateShipment(shipment, listOf("location", "12345", "123456789","Los Angelos"))
        assertEquals(shipment.status, "shipped")
    }
    @Test
    fun testUpdateUpdateLocation(){
        shipmentUpdate.updateShipment(shipment, listOf("location", "12345", "123456789", "Los Angelos"))
        assertEquals(shipment.currentLocation, "Los Angelos")
    }
    @Test
    fun testNoAddingOfShippingUpdates(){
        shipmentUpdate.updateShipment(shipment, listOf("location", "12345", "123456789", "Los Angelos"))
        assertEquals(shipment.updateHistory.isEmpty(), true)
    }
}