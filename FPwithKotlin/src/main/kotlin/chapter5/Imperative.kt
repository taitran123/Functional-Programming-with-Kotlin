package chapter5

val emails = listOf(
    "email@emmmaail.com",
    "max@fgh.it",
    "hsajdkjshh",
    "mike@mcarli.it",
    "first.second@ggg.com",
    "test@test.co.uk",
    "12345@qqq.com",
    "123.45",
    "12345@a.b.c.d",
    "fp_is_great@funprog.com",
    "aaaaaaaaa.bbbbb@cccc.com",
    "aaaaaaacccc.com",
    "valid@jjjj.lll",
)
val EMAIL_REG_EX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()

fun imperative(emails:List<String>):List<String> =
    mutableListOf<String>().apply {
        for (email in emails){
            if (EMAIL_REG_EX.matches(email) && email.length>10){
                add(email)
            }
            if (size>5){
                break
            }
        }
    }


fun main(){
    println(imperative(emails))
}