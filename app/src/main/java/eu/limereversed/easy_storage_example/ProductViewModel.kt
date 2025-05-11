package eu.limereversed.easy_storage_example

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow

// ViewModel is the holder of states and business logic.
class ProductViewModel(private val productRepository: ProductRepository = Graph.productRepository) : ViewModel() {

    var productSerialNumberState by mutableIntStateOf(-1)
    var productPriceState by mutableIntStateOf(-1)
    val sharedFlow = MutableSharedFlow<String>()


    fun onProductSerialNumberChange(newSerialNumber:Int){
        productSerialNumberState = newSerialNumber
    }

    fun onProductPriceChange(newPrice:Int){
        productPriceState = newPrice
    }

    lateinit var getProducts: Flow<List<Product>>

    init {
        // This has to be launched on the main thread, so we don't add the IO dispatchers here.
        viewModelScope.launch {
            getProducts = productRepository.getProducts()
        }

        // Starting event listener
        viewModelScope.launch(Dispatchers.IO) {
            sharedFlow.collect { value ->
                println("Collector 1 received: $value")
            }
        }
    }

    fun getProductById(id:Long): Flow<Product> {
        return productRepository.getProductById(id)
    }

    fun addProduct(productEntity: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.addProduct(productEntity)
            sharedFlow.emit("********************************** Item added! Yay!")
        }
    }

    fun deleteProduct(productEntity: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteProduct(productEntity)
        }
    }

    fun updateProduct(productEntity: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.updateProduct(productEntity)
        }
    }
}