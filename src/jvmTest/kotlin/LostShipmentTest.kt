import kotlin.test.Test
import kotlin.test.assertEquals

class LostShipmentTest {
    val shipmentUpdate = LostShipment()
    val notes = arrayOf<String>()
    val updateHistory = arrayOf<ShippingUpdate>()
    val location = "unknown"
    val shipment = Shipment(
        "delayed",
        "12345",
        notes,
        updateHistory,
        0,
        location)

    @Test
    fun testUpdateShipmentStatus(){
        shipmentUpdate.updateShipment(shipment, listOf("lost", "12345", "123456789"))
        assertEquals(shipment.status, "lost")
    }

    @Test
    fun testUpdateUpdateHistory(){
        shipmentUpdate.updateShipment(shipment, listOf("lost", "12345", "123456789"))
        assertEquals(shipment.updateHistory[0].previousStatus, "delayed")
        assertEquals(shipment.updateHistory[0].newStatus, "lost")
        assertEquals(shipment.updateHistory[0].timeStamp , 123456789)
    }
}