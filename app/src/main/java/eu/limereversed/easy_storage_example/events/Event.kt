package eu.limereversed.easy_storage_example.events

import eu.limereversed.easy_storage_example.product.Product

sealed class Event {
    data class ProductAdded(val product: Product) : Event()
    data class ProductDeleted(val product: Product) : Event()
    data class ProductUpdated(val product: Product) : Event()
    object DatabaseError : Event()
    data class CustomMessage(val message: String) : Event()
}