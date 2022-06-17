package chapter5

fun executor(fn:() -> Unit){
    fn()
}

fun main(){
    executor {
        var count =0
        while (true){
            count++
            if(count>5){
                return@executor
            }
        }
    }
    println("The End")
}