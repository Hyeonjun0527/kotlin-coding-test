package backjoon.치트시트.정렬

fun main() {
    val 카드개수 = readln().toInt()
    val 카드목록 = readln().split(" ").map { it.toInt() }

    val map = HashMap<Int, Int>(카드개수 * 2)
    for (숫자 in 카드목록) {
        map[숫자] = (map[숫자] ?: 0) + 1
    }

    val 질문개수 = readln().toInt()
    val 질문목록 = readln().split(" ").map { it.toInt() }

    val 출력 = StringBuilder()
    for (i in 0 until 질문개수) {
        val 찾는값 = 질문목록[i]
        출력.append(map[찾는값] ?: 0)
        if (i != 질문개수 - 1) 출력.append(' ')
    }

    print(출력.toString())
}
/*
 * https://www.acmicpc.net/problem/10816
 */
