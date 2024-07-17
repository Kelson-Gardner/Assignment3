class CanceledShipment: ShipmentUpdateStrategy {
    override fun updateShipment(shipment: Shipment, shipmentUpdateDetails: List<String>) {
        val oldStatus = shipment.status
        val newStatus = shipmentUpdateDetails[0]
        val timeStamp = shipmentUpdateDetails[2].toLong()
        val shippingUpdate = ShippingUpdate(
            oldStatus,
            newStatus,
            timeStamp
        )
        shipment.addShippingUpdate(shippingUpdate)
        shipment.status = newStatus
        shipment.notifyObservers()
    }
}