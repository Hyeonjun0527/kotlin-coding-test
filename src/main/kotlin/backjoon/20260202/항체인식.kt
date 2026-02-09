package backjoon.`20260202`

import java.util.ArrayDeque

fun main() {
    val (세로, 가로) = readln().split(" ").map { it.toInt() }

    val 전 = Array(세로) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    val 후 = Array(세로) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    // 1) 값이 바뀐 칸 하나 찾기
    var 시작행 = -1
    var 시작열 = -1

    outer@ for (r in 0 until 세로) {
        for (c in 0 until 가로) {
            if (전[r][c] != 후[r][c]) {
                시작행 = r
                시작열 = c
                break@outer
            }
        }
    }

    // 하나도 안 바뀌면 YES (우연히 같은 값으로 업데이트 가능)
    if (시작행 == -1) {
        print("YES")
        return
    }

    // 2) before에서 시작점과 같은 값으로 연결된 덩어리(BFS) 찾기
    val 원래값 = 전[시작행][시작열]
    val 방문 = Array(세로) { BooleanArray(가로) }

    val 큐 = ArrayDeque<Pair<Int, Int>>()
    큐.add(시작행 to 시작열)
    방문[시작행][시작열] = true

    val dr = intArrayOf(-1, 1, 0, 0)
    val dc = intArrayOf(0, 0, -1, 1)

    while (큐.isNotEmpty()) {
        val (r, c) = 큐.removeFirst()
        for (k in 0 until 4) {
            val nr = r + dr[k]
            val nc = c + dc[k]
            if (nr !in 0 until 세로 || nc !in 0 until 가로) continue
            if (방문[nr][nc]) continue
            if (전[nr][nc] != 원래값) continue
            방문[nr][nc] = true
            큐.add(nr to nc)
        }
    }

    // 3) 검증
    val 바뀐값 = 후[시작행][시작열]

    for (r in 0 until 세로) {
        for (c in 0 until 가로) {
            if (방문[r][c]) {
                // 덩어리 안: after 값이 전부 같아야 함
                if (후[r][c] != 바뀐값) {
                    print("NO")
                    return
                }
            } else {
                // 덩어리 밖: 값이 바뀌면 안 됨
                if (전[r][c] != 후[r][c]) {
                    print("NO")
                    return
                }
            }
        }
    }

    print("YES")
}
