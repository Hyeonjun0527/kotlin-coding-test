package backjoon.`20260207` // 수 찾기

fun main() {
    val n = readln().toInt()
    val 집합 = HashSet<Int>(n * 2)

    val 수목록 = readln().split(" ").map { it.toInt() }
    for (수 in 수목록) {
        집합.add(수)
    }

    val 질문수 = readln().toInt()
    val 질문목록 = readln().split(" ").map { it.toInt() }

    val 출력 = StringBuilder()
    for (찾는값 in 질문목록) {
        출력.append(if (집합.contains(찾는값)) 1 else 0).append('\n')
    }

    print(출력.toString())
}

/*
https://www.acmicpc.net/problem/1920
*/
