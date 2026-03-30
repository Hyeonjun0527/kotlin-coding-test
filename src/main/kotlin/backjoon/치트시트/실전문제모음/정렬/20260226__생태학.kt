package backjoon.치트시트.실전문제모음.정렬

// 알고리즘 분류: 정렬

fun main() {
    var 전체수 = 0
    val 사전 = java.util.TreeMap<String,Int>()
    while(true) {
        val 종이름 = try {
            readln()
        } catch (e: Exception) {
            break
        }
        사전[종이름] = (사전[종이름] ?: 0) + 1
        전체수++
    }
    val sb = StringBuilder()
    for ((이름,개수) in 사전) {
        val 비율 = "%.4f".format((개수.toDouble() / 전체수) * 100)
        sb.append("${이름} ${비율}\n")
    }
    print(sb)
}
