package backjoon.`20260328`//  B - Personnel Change

fun main(args: Array<String>) {
    val (n,m) = readln().split(" ").map { it.toInt() }//m은 부서수
    val sb = StringBuilder()

    var list = MutableList(m+1) { 0 }
    repeat(n) {

        val (전부서, 이번부서) = readln().split(" ").map { it.toInt() }
        //각 부서마다 사람의 차이 기록해야댐.
        list[전부서]--
        list[이번부서]++
    }

    list.drop(1).forEach {
        sb.append(it).append("\n")
    }
    println(sb)
}