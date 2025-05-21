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
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.runtime.mutableStateOf
import eu.limereversed.easy_storage_example.owner.Owner
import eu.limereversed.easy_storage_example.owner.OwnerViewModel
import eu.limereversed.easy_storage_example.product.Product
import eu.limereversed.easy_storage_example.product.ProductViewModel
import eu.limereversed.easy_storage_example.events.Event
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import eu.limereversed.easy_storage_example.owner.OwnerWithProducts

@Composable
fun StoreListApp(innerPadding: PaddingValues) {
    // StoreListApp.kt
    val pViewModel: ProductViewModel = viewModel()
    val oViewModel: OwnerViewModel = viewModel()
    val owners = oViewModel.getOwners.collectAsState(initial = listOf<Owner>()).value
    var currentOwnerIndex by remember { mutableIntStateOf(0) }
    val ownerIds = owners.map { it.id }
    val currentOwner: OwnerWithProducts? = oViewModel
        .getOwnerWithProducts(ownerIds.getOrNull(currentOwnerIndex) ?: 0L)
        .collectAsState(
            initial = OwnerWithProducts(Owner(0, ""), emptyList())
        ).value

    var ownerText by remember(currentOwner?.owner?.id) {
        mutableStateOf(currentOwner?.owner?.name ?: "")
    }

    LaunchedEffect(Unit) {
        pViewModel.sharedFlow.collect { event ->
            when (event) {
                is Event.ProductAdded -> println("🎉 Product added: ${event.product.serialNumber}")
                else -> println("Fail")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = {
                    oViewModel.addOwner(
                        Owner(
                            name = ownerText
                        )
                    )
                }) {
                Text("Add Owner")
            }
            if (owners.isNotEmpty()) {
                Button(onClick = {
                    currentOwnerIndex = if (currentOwnerIndex == owners.size - 1) 0 else currentOwnerIndex + 1
                }) {
                    Text("Next owner")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = ownerText, onValueChange = { ownerText = it })

        Spacer(modifier = Modifier.height(16.dp))

        if (owners.isNotEmpty()) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        pViewModel.addAndAssignProductTo(
                            currentOwner?.owner?.id,
                            Product(
                                serialNumber = (0..1000).random(),
                                price = (1..50).random()
                            )
                        )
                    }) {
                    Text("Give product")
                }
                Button(onClick = {
                    if (currentOwner != null) {
                        val productID = currentOwner.products[currentOwner.products.size - 1].id
                        oViewModel.unassignProductFromOwner(currentOwner.owner.id, productID)
                    }
                }) {
                    Text("Steal product")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(ownerText)
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                currentOwner?.products?.let { productList ->
                    items(productList, key = { it.id }) { item ->
                        Text(
                            text = "${item.serialNumber}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}