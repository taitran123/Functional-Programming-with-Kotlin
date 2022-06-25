package chapter15

import chapter7.FCons
import chapter7.FList
import chapter7.Nil
import chapter7.forEach
import chapter9.map

val products = FList.of(
    Product("1", "Eggs"),
    Product("2", "Flour"),
    Product("3", "Cake"),
    Product("4", "Pizza"),
    Product("5", "Water")

)

var currentCount = 0
fun inventoryMap(products: FList<Product>):FList<SkuProduct>{
    return products.map {
        SkuProduct(it,  "RAY-PROD-${String.format("%04d", currentCount++)}")
    }
}

fun inventoryMapWithCount(products: FList<Product>): FList<SkuProduct> {
    var internalCount = 0
    return products.map {
        SkuProduct(it,  "RAY-PROD-${String.format("%04d", internalCount++)}")
    }
}

fun listInventory(products: FList<Product>): (Int) -> Pair<Int, FList<SkuProduct>> =
    when (products) {
        is Nil -> {count: Int -> count to Nil}
        is FCons<Product> -> {count: Int ->
            val(newState, tailInventory) = listInventory(products.tail)(count)
            val sku = "RAY-PROD-${String.format("%04d", newState)}"
            newState +1 to FCons(SkuProduct(products.head, sku), tailInventory)
        }
    }


val addSku: (Product) -> State<Int, SkuProduct> = {
    prod : Product ->
    State<Int, SkuProduct> {state: Int ->
        val newSku = "RAY-PROD-${String.format("%04d", state)}"
        SkuProduct(prod, newSku) to state+1
    }
}

fun inventory(list: FList<Product>): State<Int, FList<SkuProduct>> =
    when (list) {
        is Nil -> State.lift(Nil)
        is FCons<Product> -> {
            val head = State.lift<Int, Product>(list.head).flatMap(addSku)
            val tail = inventory(list.tail)
            head.zip(tail) {a: SkuProduct, b: FList<SkuProduct> -> FCons(a,b)}
        }
    }

fun main() {
    inventoryMapWithCount(products).forEach (::println)
    println("=====111======")
    listInventory(products)(0).second.forEach(::println)
    println("======222=====")
    inventory(products)(0).first.forEach ( ::println )

}