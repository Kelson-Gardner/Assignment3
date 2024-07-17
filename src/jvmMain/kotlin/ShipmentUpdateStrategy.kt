interface ShipmentUpdateStrategy {
    fun updateShipment(shipment: Shipment, shipmentUpdateDetails: List<String>)
}