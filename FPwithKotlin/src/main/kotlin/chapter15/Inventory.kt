package chapter15

import chapter14.tools.curry
import chapter8.pipe

data class Product(val id : String, val name: String)
var count= 0
data class SkuProduct(val product: Product, val sku: String)

fun createSku(): String = "RAY-PROD-${String.format("%04d", count++)}"

val skuStateTransformer:StateTransformer<Int, String> = {state ->
    "RAY-PROD-${String.format("%04d", count++)}" to state +1
}

val assignSku: (Product, Int) -> Pair<SkuProduct, Int> = {
    product, state ->
    val newSku = "RAY-PROD-${String.format("%04d", state)}"
    SkuProduct(product, newSku)to state+1
}

val curriedAssignSku: (Product) -> StateTransformer<Int, SkuProduct> = assignSku.curry()

fun main() {
    val prod1 = Product("1", "Cheese")
    val prod2 = Product("2", "Bread")
    val prod3 = Product("3", "Cake")

//    SkuProduct(prod1, createSku()) pipe ::println
//    SkuProduct(prod2, createSku()) pipe ::println
//    SkuProduct(prod3, createSku()) pipe ::println

//    val state0 = 0
//    val (sku1, state1) = skuStateTransformer(state0)
//    SkuProduct(prod1, sku1) pipe ::println
//    val (sku2, state2) = skuStateTransformer(state1)
//    SkuProduct(prod2, sku2) pipe ::println
//    val (sku3, state3) = skuStateTransformer(state2)
//    SkuProduct(prod3, sku3) pipe ::println

//    val state0 = 0
//    val (skuProd1, state1) = curriedAssignSku(prod1)(state0)
//    skuProd1 pipe ::println
//    val (skuProd2, state2) = curriedAssignSku(prod2)(state1)
//    skuProd2 pipe ::println
//    val (skuProd3, state3) = curriedAssignSku(prod3)(state2)
//    skuProd3 pipe ::println

    val initialState = State.lift<Int, Product>(Product("1", "Cheese")) pipe ::println
}