//  섬의 개수

fun main(args: Array<String>) {
    while (true) {
        val (maxw, maxh) = readln().split(" ").map { it.toInt() }
        if (maxw == 0 && maxh == 0) break
        //h만큼 한라인 읽으면 댐 한 라인의 길이는 w 임.

        val grid = MutableList (maxh) {
            val row = readln().split(" ").map { it.toInt() }.toMutableList()
            row
        }//row의 길이는 w이다.
        val dh = arrayOf(-1,-1,0,1,1,1,0,-1)//세로, 바깥, 앞
        val dw = arrayOf(0,1,1,1,0,-1,-1,-1)//가로, 안쪽, 뒤
        //이제 grid 를 순회하고, 대각선 방향으로 해서 모든 셀마다, dfs 돌리면 됨. 그리고 돌리면 0으로 바꾸기. 섬의 개수
        fun dfs(h: Int,w: Int) {
            grid[h][w] = 0
            for (i in 0..7) {
                val nh = h + dh[i]
                val nw = w + dw[i]
                if (nh !in 0 until maxh || nw !in 0 until maxw) continue
                if (grid[nh][nw] == 1) {
                    dfs(nh,nw)
                }
            }
        }
        var cnt = 0
        for (i in 0 until maxh) {
            for (j in 0 until maxw) {
                if (grid[i][j] == 1) {
                    cnt++
                    dfs(i,j)
                }
            }
        }
        println(cnt)
    }
}