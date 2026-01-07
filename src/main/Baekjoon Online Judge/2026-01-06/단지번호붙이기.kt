
fun main() {
    val n = readln().trim().toInt()
    val grid = MutableList(n) {
        readln().trim().map { it.digitToInt() }.toMutableList()
    }
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)
    var cnt = 0
    fun dfs(y: Int, x: Int) {
        grid[y][x] = 0
        cnt++
        for (dir in 0..3) {
            val ny = y + dy[dir]
            val nx = x + dx[dir]
            if (ny !in 0 until n || nx !in 0 until n) continue
            if (grid[ny][nx] == 1) dfs(ny, nx)
        }
    }
    val cntList = mutableListOf<Int>()
    grid.forEachIndexed { y, row ->
        row.forEachIndexed inner@ { x, v ->
            if (v == 0) return@inner
            cnt = 0
            dfs(y, x)
            cntList.add(cnt)
        }
    }
    cntList.sort()
    println(cntList.size)
    for (v in cntList) println(v)
}
