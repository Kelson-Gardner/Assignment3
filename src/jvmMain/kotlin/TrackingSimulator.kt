import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

object TrackingSimulator {
    private var shipments: Array<Shipment> = arrayOf()

    private val strategies = mapOf(
        "created" to CreatedShipment(),
        "shipped" to ShippedShipment(),
        "location" to LocationOfShipment(),
        "delivered" to DeliveredShipment(),
        "delayed" to DelayedShipment(),
        "lost" to LostShipment(),
        "canceled" to CanceledShipment(),
        "noteadded" to NoteAddedToShipment()
    )

    fun findShipment(id: String): Shipment?{
        return shipments.find {it.id == id}
    }

    fun addShipment(shipment: Shipment){
        shipments += shipment
    }

    private fun createShipment(shipment: String): Shipment{
        val notes = arrayOf<String>()
        val updateHistory = arrayOf<ShippingUpdate>()
        val location = "unknown"
        val shipmentDetails = shipment.split(',')
        val shipmentStatus = shipmentDetails[0]
        val shipmentId = shipmentDetails[1]
        return Shipment(
            shipmentStatus,
            shipmentId,
            notes,
            updateHistory,
            0,
            location)
    }

    private fun updateShipment(updateStrategy: String){
        val shipmentDetails = updateStrategy.split(',')
        val shipment = findShipment(shipmentDetails[1])
        val shipmentUpdate = strategies[shipmentDetails[0]] ?: CreatedShipment()
        if (shipment != null) {
            shipmentUpdate.updateShipment(shipment, shipmentDetails)
        }
    }

    fun runSimulation() = runBlocking {
            launch {
        readFileLineByLine("src/jvmMain/resources/test.txt")
            }
    }

    private suspend fun readFileLineByLine(filePath: String) {
        val file = File(filePath)
        file.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                if(line.contains("created")){
                    val shipment = createShipment(line)
                    addShipment(shipment)
                }
                else{
                    updateShipment(line)
                }
                delay(1000)
            }
        }
    }
}