package backjoon.`20260210`//  지뢰 찾기

fun main(args: Array<String>) {
    /*
    * 저 아래판을 읽어. 그리고 x면 위에판읽어서 지뢰인지 확인해.
    * 아니면 인접한거 한바퀴 돌아서 개수세서 숫자출력.
    * x면 먼저 지뢰맵읽어 지뢰아니면 한바퀴돌아서 0..8결정해줘.
    * */
    val n = readln().toInt()
    var mineMap = mutableListOf<CharArray>()
    repeat(n) {
        mineMap.add(readln().toCharArray())
    }
    var map = mutableListOf<CharArray>()
    repeat(n) {
        map.add(readln().toCharArray())
    }
    val dr = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    var dc = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
    var sol = Array(n) { CharArray(n) { '.' } }

    var exploded = false
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (map[i][j] == 'x' && mineMap[i][j] == '*') {
                exploded = true
            }
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {//m은 고정

            if (exploded && mineMap[i][j] == '*') {
                sol[i][j] = '*'
                continue
            }

            if (map[i][j] == 'x') {
                var cnt = 0
                for (k in 0..7) {//지뢰찾기
                    val nr = i + dr[k]
                    val nc = j + dc[k]
                    if (nr !in 0 until n ||
                        nc !in 0 until n
                    ) continue
                    if (mineMap[nr][nc] == '*') {
                        cnt++
                    }
                }
                sol[i][j] = cnt.digitToChar()
            }
        }
    }
    for (i in 0 until sol.size) {
        for (j in 0 until sol[0].size) {
            print(sol[i][j])
        }
        println()
    }
}