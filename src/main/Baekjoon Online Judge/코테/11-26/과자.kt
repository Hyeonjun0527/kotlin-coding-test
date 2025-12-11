
fun main(args: Array<String>) {
    val (a,b,c) = readln().split(" ").map {it.toInt()}
    if (a*b > c) {
        println(a*b-c)
    } else {
        println(0)
    }
}