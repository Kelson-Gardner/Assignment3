import kotlin.test.Test
import kotlin.test.assertEquals

class DeliveredShipmentTest {
    val shipmentUpdate = DeliveredShipment()
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

        shipmentUpdate.updateShipment(shipment, listOf("delivered", "12345", "123456789","11111111"))
        assertEquals(shipment.status, "delivered")
    }
    @Test
    fun testUpdateUpdateHistory(){
        shipmentUpdate.updateShipment(shipment, listOf("delivered", "12345", "123456789", "11111111"))
        assertEquals(shipment.updateHistory[0].previousStatus, "delayed")
        assertEquals(shipment.updateHistory[0].newStatus, "delivered")
        assertEquals(shipment.updateHistory[0].timeStamp , 123456789)
    }
}