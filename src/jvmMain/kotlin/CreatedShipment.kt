class CreatedShipment: ShipmentUpdateStrategy {
    override fun updateShipment(shipment: Shipment, shipmentUpdateDetails: List<String>){
        shipment.status = shipmentUpdateDetails[0]
    }
}