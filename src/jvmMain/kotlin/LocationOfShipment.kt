class LocationOfShipment: ShipmentUpdateStrategy {
    override fun updateShipment(shipment: Shipment, shipmentUpdateDetails: List<String>) {
        shipment.currentLocation = shipmentUpdateDetails[3]
    }
}