package backjoon.치트시트.실전문제모음.dataStructure

// 알고리즘 분류: dataStructure

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
