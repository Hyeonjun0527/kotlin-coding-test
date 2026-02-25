package backjoon.`20260224` // 디지털 티비

import java.util.Collections.swap

fun main(args: Array<String>) {
    val n = readln().toInt()
    val 채널들 = mutableListOf<String>()
    repeat(n) { 채널들.add(readln()) }

    val sb = StringBuilder()
    var 커서 = 0

    fun 올리기(목표채널: String, 목표위치: Int) {
        var pos = 채널들.indexOf(목표채널)

        while (커서 < pos) {
            sb.append('1')
            커서++
        }

        while (pos > 목표위치) {
            sb.append('4')
            swap(채널들, pos, pos - 1)
            pos--
            커서--
        }
    }

    올리기("KBS1", 0)
    올리기("KBS2", 1)

    print(sb)
}