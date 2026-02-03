fun main() {
    val (세로, 가로) = readln().split(" ").map { it.toInt() }

    val 전 = Array(세로) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    val 후 = Array(세로) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    // 1) 바뀐 칸 하나 찾기
    var 시작행 = -1
    var 시작열 = -1

    찾기@ for (r in 0 until 세로) {
        for (c in 0 until 가로) {
            if (전[r][c] != 후[r][c]) {
                시작행 = r
                시작열 = c
                break@찾기
            }
        }
    }

    // 하나도 안 바뀌면 YES (업데이트 값이 우연히 원래값과 같을 수도 있음)
    if (시작행 == -1) {
        print("YES")
        return
    }

    val 원래값 = 전[시작행][시작열]
    val 방문 = Array(세로) { BooleanArray(가로) }

    val dr = intArrayOf(-1, 1, 0, 0)
    val dc = intArrayOf(0, 0, -1, 1)

    fun dfs(r: Int, c: Int) {
        방문[r][c] = true
        for (k in 0 until 4) {
            val nr = r + dr[k]
            val nc = c + dc[k]
            if (nr !in 0 until 세로 || nc !in 0 until 가로) continue
            if (방문[nr][nc]) continue
            if (전[nr][nc] != 원래값) continue
            dfs(nr, nc)
        }
    }

    // 2) 덩어리 찾기
    dfs(시작행, 시작열)

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
