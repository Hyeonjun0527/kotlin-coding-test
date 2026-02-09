package backjoon.`20260209`//  이번학기 평점은 몇점?

fun main(args: Array<String>) {
    val n = readln().toInt()

    var sum = 0.0
    var 총학점 = 0

    repeat(n) {
        val list = readln().split(" ")

        val value = when (list[2]) {
            "A+" -> 4.3
            "A0" -> 4.0
            "A-" -> 3.7
            "B+" -> 3.3
            "B0" -> 3.0
            "B-" -> 2.7
            "C+" -> 2.3
            "C0" -> 2.0
            "C-" -> 1.7
            "D+" -> 1.3
            "D0" -> 1.0
            "D-" -> 0.7
            "F" -> 0.0
            else -> 0.0
        }
        sum += list[1].toInt() * value
        총학점 += list[1].toInt()
    }
    println("%.2f".format(sum/총학점))
}