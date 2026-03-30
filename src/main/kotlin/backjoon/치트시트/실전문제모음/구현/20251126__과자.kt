package backjoon.치트시트.실전문제모음.구현

// 알고리즘 분류: 구현

fun main(args: Array<String>) {
    val (a,b,c) = readln().split(" ").map {it.toInt()}
    if (a*b > c) {
        println(a*b-c)
    } else {
        println(0)
    }
}
