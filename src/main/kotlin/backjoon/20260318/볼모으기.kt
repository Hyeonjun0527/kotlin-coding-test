package backjoon.`20260318`

import kotlin.text.iterator

fun main() {
    val n = readln().toInt()
    val 공 = readln()

    var 빨 = 0
    var 파 = 0

    for (c in 공) {
        if (c == 'R') 빨++
        else 파++
    }

    var 왼빨 = 0
    for (i in 0 until n) {
        if (공[i] == 'R') 왼빨++
        else break
    }

    var 오른빨 = 0
    for (i in n - 1 downTo 0) {
        if (공[i] == 'R') 오른빨++
        else break
    }

    var 왼파 = 0
    for (i in 0 until n) {
        if (공[i] == 'B') 왼파++
        else break
    }

    var 오른파 = 0
    for (i in n - 1 downTo 0) {
        if (공[i] == 'B') 오른파++
        else break
    }

    val a = 빨 - 왼빨
    val b = 빨 - 오른빨
    val c = 파 - 왼파
    val d = 파 - 오른파

    println(minOf(a, b, c, d))
}