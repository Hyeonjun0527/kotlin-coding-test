package backjoon.치트시트.실전문제모음.출력

// 알고리즘 분류: 출력

fun main() {
    val n = readln().toInt()
    val message = when (n) {
        in 620..780 -> "Red"
        in 590..620 -> "Orange"
        in 570..590 -> "Yellow"
        in 495..570 -> "Green"
        in 450..495 -> "Blue"
        in 425..450 -> "Indigo"
        in 380..425 -> "Violet"
        else -> ""
    }
    println(message)
}
