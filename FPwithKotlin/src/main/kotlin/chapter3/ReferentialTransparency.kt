package chapter3



fun main(){
    val expr1 = {1 *30}
    val (a1, a2) = expr1() to expr1()
    val expr1Eval = expr1()
    val (a1Eval, a2Eval) = expr1Eval to expr1Eval

}