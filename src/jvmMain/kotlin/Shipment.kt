class Shipment(
    var status: String,
    val id: String,
    notes: Array<String>,
    updateHistory: Array<ShippingUpdate>,
    var expectedDeliveryTimeStamp: Long,
    var currentLocation: String,
) : ShipmentSubject {
    var observers = mutableListOf<ShipmentObserver>()

    var notes: Array<String> = notes
        private set

    var updateHistory: Array<ShippingUpdate> = updateHistory
        private set

    fun addNote(note: String){
        notes += note
    }

    fun addShippingUpdate(update: ShippingUpdate){
        updateHistory += update
    }

    override fun notifyObservers(){
        for(observer in observers){
            observer.update(this)
        }
    }

    override fun subscribe(observer: ShipmentObserver){
        observers += observer
    }

    override  fun unsubscribe(observer: ShipmentObserver){
        observers.remove(observer)
    }

}