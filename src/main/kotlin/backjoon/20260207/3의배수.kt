package backjoon.`20260207`//  3의 배수

fun main(args: Array<String>) {
    val n = readln().toInt()
    var cnt = 0
    for (a in 3..n-6 step 3) {
        for (b in 3..n-a-3 step 3) {
            val c = n - a - b
            if (c >= 3 && c % 3 == 0) cnt++
        }
    }
    println(cnt)
}