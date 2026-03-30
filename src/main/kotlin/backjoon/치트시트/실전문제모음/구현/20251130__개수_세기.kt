package backjoon.치트시트.실전문제모음.구현

// 알고리즘 분류: 구현

fun main(args: Array<String>) {
    readln()
    val list = readln().split(" ").map {it.toInt()}
    val a = readln().toInt()
    println(list.count { it == a })
//    println(list.filter { it == a }.size)
//    println(list.filter (a::equals).size)
//    fun isTarget(n: Int) :Boolean = (n == a)
//    println(list.filter(::isTarget).size)
}
