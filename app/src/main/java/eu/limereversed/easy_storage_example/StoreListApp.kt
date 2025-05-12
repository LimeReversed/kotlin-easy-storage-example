package eu.limereversed.easy_storage_example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import eu.limereversed.easy_storage_example.owner.Owner
import eu.limereversed.easy_storage_example.owner.OwnerViewModel
import eu.limereversed.easy_storage_example.product.Product
import eu.limereversed.easy_storage_example.product.ProductViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArraySet
import androidx.lifecycle.viewModelScope
import eu.limereversed.easy_storage_example.events.Event

@Composable
fun StoreListApp(innerPadding: PaddingValues) {
    // StoreListApp.kt
    val pViewModel: ProductViewModel = viewModel()
    val oViewModel: OwnerViewModel = viewModel()
    val products = pViewModel.getProducts.collectAsState(initial = listOf<Product>()).value
    val owners = oViewModel.getOwners.collectAsState(initial = listOf<Owner>()).value

    val productAddedExecutors = CopyOnWriteArraySet<() -> Unit>()

    LaunchedEffect(Unit) {
        pViewModel.sharedFlow.collect { event ->
            when (event){
                is Event.ProductAdded -> println("🎉 Product added: ${event.product.serialNumber}")
                else -> println("Fail")
            }

        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = {
                    pViewModel.addProduct(
                        Product(
                            serialNumber = (0..1000).random(),
                            price = (1..50).random()
                        )
                    )
                }) {
                Text("Add")
            }
            Button(onClick = {

                // If empty?
                pViewModel.deleteProduct(products[products.size - 1])
            }) {
                Text("Delete")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(products, key = { product: Product -> product.id }) { item ->
                Text(
                    text = "${item.serialNumber}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}