
fun main(args: Array<String>) {
    val n = readln().toInt()
    repeat(n) {
        val (a, b, x) = readln().split(" ").map { it.toInt() }
        println(a *(x-1) + b)
    }
}