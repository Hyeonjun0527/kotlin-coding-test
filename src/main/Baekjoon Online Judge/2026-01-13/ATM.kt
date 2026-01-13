
fun main() {
    val n = readln().toInt()
    val l = readln().split(" ").map { it.toInt()}
    val list = l.sorted()
    var sum = 0
    for (i in 0 until list.size) {
        for (j in 0..i) {
            sum += list[j]
        }
    }
    println(sum)
}

//1 2 3 3 4
//1
//+
//1 + 2
//+
//1 + 2 + 3