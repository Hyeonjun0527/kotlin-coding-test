package backjoon.soma2cha.DFSBFS.`20260305` // https://www.acmicpc.net/problem/10026

fun main() {
    val yCnt = readln().toInt()
    val xCnt = yCnt

    val graph = MutableList(yCnt) { MutableList(xCnt) { ' ' } }
    for (y in 0 until yCnt) {
        val 줄 = readln()
        for (x in 0 until xCnt) {
            graph[y][x] = 줄[x]
        }
    }

    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(-1, 1, 0, 0)

    fun 같은구역인가(기준색: Char, 다음색: Char, 색약모드: Boolean): Boolean {
        return if (!색약모드) {
            기준색 == 다음색
        } else {
            // 색약: R/G는 같은색, B는 따로
            if (기준색 == 'B') 다음색 == 'B'
            else 다음색 != 'B'
        }
    }

    fun dfs(x: Int, y: Int, visited: MutableList<MutableList<Boolean>>, 기준색: Char, 색약모드: Boolean) {
        visited[y][x] = true

        for (방향 in 0 until 4) {
            val nx = x + dx[방향]
            val ny = y + dy[방향]

            if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
            if (visited[ny][nx]) continue

            val 다음색 = graph[ny][nx]
            if (!같은구역인가(기준색, 다음색, 색약모드)) continue

            dfs(nx, ny, visited, 기준색, 색약모드)
        }
    }

    fun 구역개수세기(색약모드: Boolean): Int {
        val visited = MutableList(yCnt) { MutableList(xCnt) { false } }
        var 구역수 = 0

        for (y in 0 until yCnt) {
            for (x in 0 until xCnt) {
                if (!visited[y][x]) {
                    구역수++
                    val 기준색 = graph[y][x]
                    dfs(x, y, visited, 기준색, 색약모드)
                }
            }
        }
        return 구역수
    }

    val 일반 = 구역개수세기(false)
    val 색약 = 구역개수세기(true)

    print("$일반 $색약")
}
