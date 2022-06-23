package chapter14.applicative_finctor

import chapter14.tools.curry

data class User(
    val id: Int,
    val name: String,
    val email: String
)

class ValidationException(msg:String) : Exception(msg)

fun validateName(name: String): ResultAp<ValidationException, String> =
    if (name.length > 4){
        Success(name)
    }else{
        Error(ValidationException("Invalid name"))
    }

fun validateEmail(email: String): ResultAp<ValidationException, String> =
    if (email.contains("@")){
        Success(email)
    }else{
        Error(ValidationException("Invalid Email"))
    }


fun main2() {
    val userBuilder = ::User.curry()
    val userApplicative = ResultAp.success(userBuilder)
    val idApp = ResultAp.success(1)
    validateEmail("thtai@grandm.vn")
        .ap(
            validateName("lksdd").ap(
                idApp.ap(userApplicative)))
        .errorMap { println("Error : $it"); it }
        .successMap { println("Success $it") }
}

fun main() {
    val userBuilder = ::User.curry()
    val userApplicative = ResultAp.success(userBuilder)
    val idAp = ResultAp.success(1)
    (userApplicative appl idAp appl validateName("xx") appl validateName("thtaigrandm.cn")).errorMap { println("Error : $it");it }.successMap { println("Success $it") }
}