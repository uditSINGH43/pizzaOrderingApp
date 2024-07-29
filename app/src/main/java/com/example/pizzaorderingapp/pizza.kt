package com.example.pizzaorderingapp

enum class PizzaType {
    VEG, NON_VEG
}

data class Pizza(
    val title: String,
    val image: Int,
    val price: Double,
    val pizzaType: PizzaType,
)

data class Toppings(
    val title: String,
    val charges: Double,
    var isChecked: Boolean = false
)

fun dummyPizzaRepository(): List<Pizza> {
    return listOf(
        Pizza(
            "Farmhouse Pizza",
            R.drawable.farm_pizza,
            299.00,
            PizzaType.VEG
        ),
        Pizza(
            pizzaType = PizzaType.VEG,
            title = "Mushroom Pizza",
            image = R.drawable.mushroom_pizza,
            price = 349.00,
        ),
        Pizza(
            title = "Pepperoni Pizza",
            image = R.drawable.pepporoni_pizza,
            price = 399.00,
            pizzaType = PizzaType.NON_VEG
        ),
        Pizza(
            title = "Veg Surprise Pizza",
            image = R.drawable.veg_surprise_pizza,
            price = 299.00,
            pizzaType = PizzaType.VEG
        ),
    )
}

fun dummyToppingsRepository(): List<Toppings> {
    return listOf(
        Toppings(
            "ketchup",
            10.00
        ),
        Toppings(
            "seasoning",
            2.00
        ),
        Toppings(
            "chilli Flakes",
            2.00
        ),
        Toppings(
            "Cheese",
            100.00
        ),
        Toppings(
            "tomato",
            59.00
        ),
        Toppings(
            "onion",
            49.00
        ),
        Toppings(
            "mushroom",
            89.00
        ),
    )


}