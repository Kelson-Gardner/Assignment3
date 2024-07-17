import kotlin.test.Test
import kotlin.test.assertEquals

class ShippedShipmentTest {
    val shipmentUpdate = ShippedShipment()
    val notes = arrayOf<String>()
    val updateHistory = arrayOf<ShippingUpdate>()
    val location = "unknown"
    val shipment = Shipment(
        "created",
        "12345",
        notes,
        updateHistory,
        0,
        location)

    @Test
    fun testUpdateShipmentStatus(){

        shipmentUpdate.updateShipment(shipment, listOf("shipped", "12345", "123456789","11111111"))
        assertEquals(shipment.status, "shipped")
    }
    @Test
    fun testUpdateExpectedDeliveryTime(){
        shipmentUpdate.updateShipment(shipment, listOf("shipped", "12345", "123456789","11111111"))
        assertEquals(shipment.expectedDeliveryTimeStamp, 11111111)
    }
    @Test
    fun testUpdateUpdateHistory(){
        shipmentUpdate.updateShipment(shipment, listOf("shipped", "12345", "123456789", "11111111"))
        assertEquals(shipment.updateHistory[0].previousStatus, "created")
        assertEquals(shipment.updateHistory[0].newStatus, "shipped")
        assertEquals(shipment.updateHistory[0].timeStamp , 123456789)
    }
}