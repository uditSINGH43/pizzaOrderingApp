package com.example.pizzaorderingapp.ui.theme

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onConfirm: (Set<Toppings>) -> Unit = {}
) {
    val pizza = dummyPizzaRepository()[pizzaId]
    var selectedToppings by remember { mutableStateOf(emptySet<Toppings>()) }

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
                    ToppingItem(toppings = it) { selection ->

                        if (selection.isChecked) {
                            selectedToppings = selectedToppings.plusElement(selection)
                        } else {
                            selectedToppings = selectedToppings.minusElement(selection)
                        }
                        Log.d("Toppings Selected", "$selectedToppings")
                    }
                }

            }


        }

    }

}

@Composable
fun ToppingItem(
    modifier: Modifier = Modifier, toppings: Toppings,
    onToppingSelected: (Toppings) -> Unit
) {
    var selectedCheckBox by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Checkbox(checked = selectedCheckBox, onCheckedChange = {
                Log.d("CheckBox Value", "$it")
                selectedCheckBox = it
                toppings.isChecked = selectedCheckBox
                onToppingSelected(toppings)
            }
            )
            Text(text = toppings.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "${toppings.charges}")

        }
    }

}

@Preview
@Composable
private fun ToppingsItemsPreview() {
    ToppingItem(toppings = Toppings("cheese", 12.00)) {}

}

@Preview(showSystemUi = true)
@Composable
private fun TSSPreview() {
    ToppingSelectionScreen(pizzaId = 0)

}