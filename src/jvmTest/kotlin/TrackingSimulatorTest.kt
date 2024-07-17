import kotlin.test.Test
import kotlin.test.assertEquals

class TrackingSimulatorTest {
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
    fun testAddAndFindShipment(){
        TrackingSimulator.addShipment(shipment)
        assertEquals(TrackingSimulator.findShipment("12345"), shipment)
    }

    @Test
    fun testFindNoShipment(){
        assertEquals(TrackingSimulator.findShipment("1212"), null)
        assertEquals(TrackingSimulator.findShipment("1234"), null)
        assertEquals(TrackingSimulator.findShipment("198273847"), null)
    }
//    @Test
//    fun testRunSimulation(){
//        TrackingSimulator.runSimulation()
//        assertEquals(TrackingSimulator.findShipment("s1000000"), null)
//    }
}