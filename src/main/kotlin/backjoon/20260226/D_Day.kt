package backjoon.`20260226`//  D-Day 1308
import java.time.LocalDate
fun main() {
    val (y1,m1,d1) = readln().split(" ").map { it.toInt() }
    val (y2,m2,d2) = readln().split(" ").map { it.toInt() }

    val 시작일 = LocalDate.of(y1,m1,d1)
    val 마감일 = LocalDate.of(y2,m2,d2)
    val 초과일 = LocalDate.of(y1 + 1000,m1,d1)

    if (마감일.isAfter(초과일)) {
        print("gg")
        return
    }

    val 디데이 = java.time.temporal.ChronoUnit.DAYS.between(시작일,마감일)
    print("D-${디데이}")
}

/*
package backjoon.`20260226` // D-Day

fun 윤년인가(y: Int): Boolean {
    return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0)
}

fun 누적일수(y: Int, m: Int, d: Int): Long {
    val y1 = y - 1
    val 윤년수 = y1 / 4 - y1 / 100 + y1 / 400
    var sum = 365L * y1 + 윤년수.toLong()

    val 월일수 = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    for (i in 0 until (m - 1)) sum += 월일수[i].toLong()
    if (m > 2 && 윤년인가(y)) sum += 1

    sum += (d - 1).toLong()
    return sum
}

fun 기준이상(y2:Int,m2:Int,d2:Int, y:Int,m:Int,d:Int): Boolean {
    if (y2 != y) return y2 > y
    if (m2 != m) return m2 > m
    return d2 >= d
}

fun main() {
    val (y1, m1, d1) = readln().split(" ").map { it.toInt() }
    val (y2, m2, d2) = readln().split(" ").map { it.toInt() }

    val 기준년 = y1 + 1000
    if (기준이상(y2, m2, d2, 기준년, m1, d1)) {
        print("gg")
        return
    }

    val 오늘 = 누적일수(y1, m1, d1)
    val 디데이 = 누적일수(y2, m2, d2)
    val 남은일수 = 디데이 - 오늘
    print("D-$남은일수")
}
* */