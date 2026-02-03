//  유기농 배추

fun main(args: Array<String>) {
    val n = readln().toInt()
    repeat(n) {
        var cnt = 0
        val (m, n, k) = readln().split(" ").map { it.toInt() }
        val grid = MutableList (n) { MutableList(m) { 0 } }
        repeat(k) {//x가 m이고, y가 n이다.
            val (x,y) = readln().split(" ").map { it.toInt() }
            grid[y][x] = 1
        }
        //배추 심엇음 이제 전부 순회하는거임. grid를 그리고, dfs를 다돌려. 0으로 바꺼. dfs 돌린횟수 = 지렁이 개수

        val dy = arrayOf(0,1,0,-1)
        val dx = arrayOf(1,0,-1,0)

        fun dfs(y: Int, x: Int) {
            grid[y][x] = 0
            for (i in 0..3) {
                val ny = y + dy[i]
                val nx = x + dx[i]
                if (ny !in 0 until n || nx !in 0 until m) continue
                if (grid[ny][nx] == 1) {
                    dfs(ny,nx)
                }
            }
        }

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, it ->
                if (grid[y][x] == 1) {
                    cnt++
                    dfs(y,x)
                }
            }
        }
        println(cnt)
    }

}