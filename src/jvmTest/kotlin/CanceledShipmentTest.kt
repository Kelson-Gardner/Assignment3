import kotlin.test.Test
import kotlin.test.assertEquals

class CanceledShipmentTest {
    val shipmentUpdate = CanceledShipment()
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
    fun testUpdateShipmentStatus(){

        shipmentUpdate.updateShipment(shipment, listOf("canceled", "12345", "123456789"))
        assertEquals(shipment.status, "canceled")
    }
    @Test
    fun testUpdateUpdateHistory(){
        shipmentUpdate.updateShipment(shipment, listOf("canceled", "12345", "123456789"))
        assertEquals(shipment.updateHistory[0].previousStatus, "shipped")
        assertEquals(shipment.updateHistory[0].newStatus, "canceled")
        assertEquals(shipment.updateHistory[0].timeStamp , 123456789)
    }
}