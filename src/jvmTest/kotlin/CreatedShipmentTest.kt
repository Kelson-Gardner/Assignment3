import kotlin.test.Test
import kotlin.test.assertEquals
class CreatedShipmentTest {
    val shipmentUpdate = CreatedShipment()
    val notes = arrayOf<String>()
    val updateHistory = arrayOf<ShippingUpdate>()
    val location = "unknown"
    val shipment = Shipment(
        "noneExistant",
        "12345",
        notes,
        updateHistory,
        0,
        location)

    @Test
    fun testUpdateShipment(){
        shipmentUpdate.updateShipment(shipment, listOf("created"))
        assertEquals(shipment.status, "created")
    }
}