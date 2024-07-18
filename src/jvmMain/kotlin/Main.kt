import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("") }
    var trackerCards by remember { mutableStateOf(listOf<TrackerViewHelper>()) }
    var shipmentNotFoundCards by remember { mutableStateOf(listOf<String>())}
    @Composable
    fun trackerCard(shipment: TrackerViewHelper){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        ) {
            Column{
            Text(
                text = "Tracking Shipment: ${shipment.shipmentId.value}\n" +
                        "Status: ${shipment.shipmentStatus.value}\n" +
                        "Location: ${shipment.shipmentLocation.value}\n" +
                        "Expected Delivery: ${shipment.expectedShipmentDeliveryDate.value}\n" +
                        "Status Updates: ${shipment.shipmentUpdateHistory.value.joinToString("\n")}\n" +
                        "Notes: \n${shipment.shipmentNotes.value.joinToString("\n")}\n",
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                trackerCards = trackerCards.filter { it != shipment}
            },
                modifier = Modifier.align(Alignment.End)
            ){
                Text("Stop Tracking")
            }
            }
        }
    }

    @Composable
    fun shipmentNotFoundCard(shipmentNotFoundMessage: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = 4.dp
        )
        {
            Column {
                Text(shipmentNotFoundMessage)

                Button(
                    onClick = {
                        shipmentNotFoundCards = shipmentNotFoundCards.filter { it != shipmentNotFoundMessage }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Close Notification")
                }
            }
        }
    }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Shipment ID") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                val shipmentSearched = TrackingSimulator.findShipment(text)
                if (shipmentSearched != null) {
                    val trackerViewHelper = TrackerViewHelper()
                    shipmentSearched.subscribe(trackerViewHelper)
                    trackerViewHelper.update(shipmentSearched)
                    trackerCards += trackerViewHelper
                } else {
                    shipmentNotFoundCards += "The shipment $text was not found!"
                }
            }) {
                Text("Track")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(shipmentNotFoundCards) {
                cardText -> shipmentNotFoundCard(cardText)
            }
            items(trackerCards) { cardText ->
                trackerCard(cardText)
            }
        }
    }
    }


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
        val scope = CoroutineScope(Dispatchers.Default + Job())
        scope.launch{
        TrackingSimulator.runSimulation()
        }
    }
}
