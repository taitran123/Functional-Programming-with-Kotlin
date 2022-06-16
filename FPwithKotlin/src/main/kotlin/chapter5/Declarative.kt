package chapter5

fun declarative(emails : List<String>): List<String> =
    emails.filter { EMAIL_REG_EX.matches(it) }
        .filter { it.length>10 }
        .take(5)

fun main(){
    println(declarative(emails))
}