package backjoon.`20260202`/*
최대값
MMMK 하면 K가 읽히면 앞에 5붙이고 0을 m개수만큼 붙임
5000
최소값
MMMK 하면 K가 읽히면 m이 잇다면 1붙이고 0을 m-1개만큼 붙이고 5를 붙임.
1005

MKMM
???11

MKK?? -> 155
*/

fun main() {

    val list = readln().toCharArray()
    var m개수 = 0
    var m개수2 = 0
    val sb1 = StringBuilder()
    val sb2 = StringBuilder()
    for (i in 0 until list.size) {
        if (list[i] == 'M') {
            m개수++
            m개수2++
        }
        if (list[i] == 'K') {
            sb1.append("5")
            repeat(m개수) {
                sb1.append("0")
            }
            m개수=0

            if (m개수2 != 0) sb2.append("1")
            repeat(m개수2 - 1) {
                sb2.append("0")
            }
            m개수2=0
            sb2.append("5")
        }
    }
    repeat(m개수) {
        sb1.append("1")
    }
    if(m개수2 > 0) {
        sb2.append("1")
        repeat(m개수2 - 1) { sb2.append("0")}
    }
    println(sb1)
    println(sb2)
}