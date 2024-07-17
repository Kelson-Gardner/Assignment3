class DeliveredShipment: ShipmentUpdateStrategy {
    override fun updateShipment(shipment: Shipment, shipmentUpdateDetails: List<String>) {
        val oldStatus = shipment.status
        val newStatus = shipmentUpdateDetails[0]
        val timeStamp = shipmentUpdateDetails[2].toLong()
        val shippingUpdate = ShippingUpdate(
            oldStatus,
            newStatus,
            timeStamp
        )
        shipment.status = newStatus
        shipment.addShippingUpdate(shippingUpdate)
        shipment.notifyObservers()
    }
}