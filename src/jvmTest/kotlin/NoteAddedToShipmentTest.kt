import kotlin.test.Test
import kotlin.test.assertEquals

class NoteAddedToShipmentTest {
    val shipmentUpdate = NoteAddedToShipment()
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
    fun testNoUpdateOfShipmentStatus(){
        shipmentUpdate.updateShipment(shipment, listOf("delayed", "12345", "123456789","This package was damaged!"))
        assertEquals(shipment.status, "delayed")
    }
    @Test
    fun testUpdateExpectedDeliveryTime(){
        shipmentUpdate.updateShipment(shipment, listOf("delayed", "12345", "123456789","This package was damaged!"))
        assertEquals(shipment.notes[0], "This package was damaged!")
    }
    @Test
    fun testUpdateUpdateHistory(){
        shipmentUpdate.updateShipment(shipment, listOf("delayed", "12345", "123456789", "This package was damaged!"))
        assertEquals(shipment.updateHistory.isEmpty(), true)
    }
}