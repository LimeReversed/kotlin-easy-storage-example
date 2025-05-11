package eu.limereversed.easy_storage_example

import kotlinx.coroutines.flow.Flow

// A repository is responsible for communicating with the database.
class ProductRepository(private val productDao: ProductDao) {

    fun getProducts(): Flow<List<Product>> = productDao.getAllProducts()

    fun getProductById(id: Long): Flow<Product> = productDao.getProductById(id)

    suspend fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }
}