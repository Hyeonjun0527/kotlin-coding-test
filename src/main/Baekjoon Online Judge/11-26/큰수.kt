
fun main() {
    val line = readln()
    var r = 0L
    val mod = 20000303
    line.forEach {
        r = (r*10 + it.digitToInt()) % mod
    }
    println(r)
}