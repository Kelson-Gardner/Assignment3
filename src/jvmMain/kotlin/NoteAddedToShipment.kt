class NoteAddedToShipment: ShipmentUpdateStrategy {
    override fun updateShipment(shipment: Shipment, shipmentUpdateDetails: List<String>) {
        val note = shipmentUpdateDetails[3]
        shipment.addNote(note)
        shipment.notifyObservers()
    }
}