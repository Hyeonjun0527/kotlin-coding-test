package backjoon.`20260212`//  임스와 함께하는 미니게임

fun main(args: Array<String>) {
    val (n, s) = readln().split(" ")

    var 게임종류 = when (s) {
        "Y" -> 2
        "F" -> 3
        "O" -> 4
        else -> 100
    }

    val set = HashSet<String>()
    var 사람수 = 0
    repeat(n.toInt()) {
        val 사람 = readln()
        if (사람 !in set) {
            set.add(사람)
            사람수++
        }
    }
    게임종류--
    println(사람수 / 게임종류)

}