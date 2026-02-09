/*
4 4
2 2 2 1
2 2 1 3
2 1 3 3
1 3 3 3
4 4 4 1
4 4 1 3
4 1 3 3
1 3 3 3

YES
NO

입력받자. 전, 후 그래프 받자. Array() { IntArray() }
값이 바뀐 칸 하나 찾기
* */


fun main() {
    val (세로, 가로) = readln().split(" ").map { it.toInt() }
    val 전 = Array(세로) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    val 후 = Array(세로) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    var 시작행 = -1
    var 시작열 = -1

    outer@ for (r in 0 until 세로) {
        for (c in 0 until 가로) {
            if (전[r][c] != 후[r][c]) {
                시작행 = r
                시작열 = c
            }
        }
    }
}