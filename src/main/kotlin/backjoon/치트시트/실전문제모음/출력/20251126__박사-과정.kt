package backjoon.치트시트.실전문제모음.출력

// 알고리즘 분류: 출력

fun main() {
    val n = readln().toInt()
    repeat(n) {
        val s = readln()
        if (s[0]==('P')) {
            println("skipped")
        } else {
            val r = s.split("+").sumOf { it.toInt() }.toString()
            println(r)
        }
    }
}
