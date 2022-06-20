package chapter8

fun interface Logger {
    fun log(msg :String)
}

fun interface Calculator{
    fun multiply(a:Double, b:Double):Double
}

fun interface DB{
    fun save(result: Double)
}

fun interface CalculatorFactory{
    fun create(db:DB, logger: Logger):Calculator
}

val calculatorFactoryImpl = CalculatorFactory{
    db, logger ->
    Calculator { a, b ->
        val result = a * b
        db.save(result)
        logger.log("$result")
        result
    }
}

fun main2() {
    val db = DB{
        println("Saving value $it")
    }

    val simpleLogger = Logger { println("Logging $it") }
    val fileLogger = Logger { println("Logging on File $it")  }
    val calculator1 = calculatorFactoryImpl.create(db, simpleLogger)
    val calculator2 = calculatorFactoryImpl.create(db, fileLogger)
    println(calculator1.multiply(2.0, 3.0))
    println(calculator2.multiply(2.0, 3.0))
}


fun main() {
    val db = DB{
        println("Saving value $it")
    }
    val simpleLogger = Logger { println("Logging $it") }
    val fileLogger = Logger { println("Logging on File $it")  }
    val partialFactory = calculatorFactoryImpl::create.curry()
    val partialFactoryWithDb = db pipe partialFactory
    val calculator1 = partialFactoryWithDb(simpleLogger)
    val calculator2 = partialFactoryWithDb(fileLogger)
        println(calculator1.multiply(2.0, 3.0))
    println(calculator2.multiply(2.0, 3.0))

}