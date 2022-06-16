package chapter4

import kotlin.reflect.KProperty

fun testDelegate(){
    var variable by object {
        var localInt:Int? = null
        operator fun getValue(thisRef: Any?, property: KProperty<*>):Int?{
            println("Getter Invoked returning ${localInt}")
            return localInt
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int?){
            println("Setter Invoked with value $value")
            localInt = value
        }
    }
    variable = 10
    println("Reading $variable")
}

fun multiLazy(){
    val multiLambda by lazy { println("I am Lazy") }
    multiLambda
    multiLambda
    multiLambda
    multiLambda
    multiLambda
    multiLambda
    multiLambda
}

fun main(){
    multiLazy()
}