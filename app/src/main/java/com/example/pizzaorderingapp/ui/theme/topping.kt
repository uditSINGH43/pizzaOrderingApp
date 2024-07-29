package com.example.pizzaorderingapp.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaorderingapp.Toppings
import com.example.pizzaorderingapp.dummyPizzaRepository
import com.example.pizzaorderingapp.dummyToppingsRepository

@Composable
fun ToppingSelectionScreen(
    modifier: Modifier = Modifier,
    pizzaId: Int,
) {
    val pizza = dummyPizzaRepository()[pizzaId]

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = "Topping Selection",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(text = "${pizza.title} is selected")
            Spacer(modifier = Modifier.padding(10.dp))
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp)


            ) {
                items(dummyToppingsRepository()) {
                    ToppingItem(toppings = it)
                }

            }


        }

    }

}

@Composable
fun ToppingItem(modifier: Modifier = Modifier, toppings: Toppings) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Checkbox(checked = toppings.isChecked, onCheckedChange = { toppings.isChecked = it }
            )
            Text(text = toppings.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "${toppings.charges}")

        }
    }

}

@Preview
@Composable
private fun ToppigsItemsPreview() {
    ToppingItem(toppings = Toppings("cheese", 12.00))

}

@Preview(showSystemUi = true)
@Composable
private fun TSSPreview() {
    ToppingSelectionScreen(pizzaId = 0)

}