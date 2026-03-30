package backjoon.치트시트.실전문제모음.출력

// 알고리즘 분류: 출력

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val today = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    println(today.format(formatter))
}
