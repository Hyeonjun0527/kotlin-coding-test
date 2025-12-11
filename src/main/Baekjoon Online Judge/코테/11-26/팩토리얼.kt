
//fun fact(n :Int):Int {
//    if (n == 0) return 1
//    if (n == 1) return 1
//    return n*fact(n-1)
//}

fun main(args: Array<String>) {
    val n = readln().toInt()
    val sol = (1..n).fold(1L) {acc, i -> acc * i}
    println(sol)
}