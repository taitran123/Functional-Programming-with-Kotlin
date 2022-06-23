package chapter14.applicative_finctor

import chapter14.tools.curry


interface Semigroup<T>{
    operator  fun plus(rh:T) :T
}

infix fun <E, T, R> ResultAp<E, (T) -> R>.applsg(a: ResultAp<E,T>) where E:Throwable, E: Semigroup<E> = a.apsg(this)


data class ValidationExceptionComposition(private val errors: List<ValidationException>): Exception(), Semigroup<ValidationExceptionComposition>{
    override fun plus(rh: ValidationExceptionComposition): ValidationExceptionComposition = ValidationExceptionComposition((this.errors + rh.errors))

    override fun getLocalizedMessage(): String {
        return errors.joinToString { it.localizedMessage }
    }
}

fun validateNameSg(name:String):ResultAp<ValidationExceptionComposition, String> =
    if(name.length>4){
        Success(name)
    }else {
        Error(ValidationExceptionComposition(listOf(ValidationException("Invalid name"))))
    }

fun validateEmailSg(email:String):ResultAp<ValidationExceptionComposition, String> =
    if(email.contains("@")){
        Success(email)
    }else {
        Error(ValidationExceptionComposition(listOf(ValidationException("Invalid email"))))
    }

fun <E,T,R> ResultAp<E,T>.apsg(
    fn: ResultAp<E, (T) -> R>): ResultAp<E,R> where E: Throwable, E: Semigroup<E> =
    when (fn) {
        is Success<(T) -> R> -> successMap(fn.value)
        is Error<E> -> when(this){
            is Success<T> -> Error(fn.error)
            is Error<E> -> Error(this.error + fn.error)
        }
    }

fun main() {
    val userBuilder = ::User.curry()
    val userApplicative = ResultAp.success(userBuilder)
    val idAp = ResultAp.success(1)
    (userApplicative appl idAp appl validateName("xx")
            appl validateName("xxx.cn"))
        .errorMap { println("Error : $it");it }
        .successMap { println("Success $it") }
    println(" ======= Semigroup approach ========")
    (userApplicative applsg idAp applsg validateNameSg("") applsg validateEmailSg("")).errorMap { println(it.localizedMessage);it }.successMap { println("Success $it") }

}