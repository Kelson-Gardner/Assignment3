import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class TrackerViewHelper (): ShipmentObserver {
    var shipmentId: MutableState<String> = mutableStateOf("")
        private set
    var shipmentNotes: MutableState<Array<String>> = mutableStateOf(arrayOf(""))
        private set
    var shipmentUpdateHistory: MutableState<Array<String>> = mutableStateOf(arrayOf(""))
        private set
    var expectedShipmentDeliveryDate: MutableState<String> = mutableStateOf("")
        private set
    var shipmentStatus: MutableState<String> = mutableStateOf("")
        private set
    var shipmentLocation: MutableState<String> = mutableStateOf("")

    override fun update(shipment: Shipment){
        shipmentId.value = shipment.id
        shipmentNotes.value = shipment.notes
        shipmentUpdateHistory.value = formatShipmentUpdateHistory(shipment.updateHistory)
        expectedShipmentDeliveryDate.value = formatTimeStamp(shipment.expectedDeliveryTimeStamp)
        shipmentStatus.value = shipment.status
        shipmentLocation.value = shipment.currentLocation
    }

    private fun formatShipmentUpdateHistory(updates: Array<ShippingUpdate>): Array<String>{
        var formattedShippingUpdates = arrayOf("")
        for(update in updates){
            formattedShippingUpdates += "Shipment went from ${update.previousStatus} to ${update.newStatus} at ${formatTimeStamp(update.timeStamp)}"
        }
        return formattedShippingUpdates
    }

    private fun formatTimeStamp(timeStamp: Long): String{
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return dateTime.format(formatter)
    }
}