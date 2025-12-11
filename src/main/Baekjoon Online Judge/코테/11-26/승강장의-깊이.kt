
fun main(args: Array<String>) {
    val n = readln().toInt()
    var acc = 0
    repeat(n) {
        val(a,b) = readln().split(" ").map{it.toInt()}
        acc += (a-b)
        println(acc)
    }
}