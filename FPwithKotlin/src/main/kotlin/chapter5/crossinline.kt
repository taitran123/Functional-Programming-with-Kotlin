package chapter5

fun executor1(fn: () -> Unit){
    fn()
}

inline fun executorExecutor1(crossinline  fn: () -> Unit){
    executor1 {
        fn()
    }
}