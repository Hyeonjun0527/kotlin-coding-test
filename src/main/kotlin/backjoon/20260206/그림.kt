package backjoon.`20260206`//  그림

fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    //모든원소에 대해서 dfs를 돌리자. 상하좌우로
    val dr = arrayOf(0,0,-1,+1)
    val dc = arrayOf(-1,+1,0,0)
    val visited = Array(n) { BooleanArray(m)}

    fun dfs(x:Int, y: Int):Int {
        visited[x][y] = true
        var area = 1
        for (k in 0..3) {
            val nx = x + dr[k]
            val ny = y + dc[k]

            if (nx !in 0 until n || ny !in 0 until m) continue
            if (visited[nx][ny]) continue
            if (map[nx][ny] == 0) continue
            area += dfs(nx,ny)
        }
        return area
    }

    var maxArea = 0
    var cnt = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (map[i][j] == 1 && !visited[i][j]) {
                cnt++
                maxArea = maxOf(maxArea, dfs(i, j))
            }
        }
    }
    println(cnt)
    println(maxArea)
}