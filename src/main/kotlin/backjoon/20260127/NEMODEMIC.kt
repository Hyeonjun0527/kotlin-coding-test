
fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val list = MutableList(n) {
        mutableListOf<Char>()
    }
    repeat(n) {
        list.add(readln().toMutableList())
    }
//    var 벽이한개이하 = false
    var 벽개수 = 0
    var 한방향바이러스개수 = 0
//    var 한방향바이러스1개이하 = false
    var 모든방향바이러스개수 = 0
    var 백신개수 = 0
    var 시작점잇다 = 0
    var 끝점잇다 = 0
    list.forEach { row ->
        row.forEach { v ->
            if (v == '#') {
                벽개수++
            }
            if (v == 'U' || v == 'D' || v == 'L' || v == 'R') {
                한방향바이러스개수++
            }
            if (v == 'A') {
                모든방향바이러스개수++
            }
            if (v == 'V') {
                백신개수++
            }
            if (v == 'S') {
                시작점잇다++
            }
            if (v == 'E') {
                끝점잇다++
            }   
        }
    }

    if (시작점잇다 != 1 || 끝점잇다 != 1) {
        println(-1)
        return
    }

    if (벽개수 <= 1 && 한방향바이러스개수 <= 1 && 모든방향바이러스개수 == 0 && 백신개수 == 0) {
        println(1)
        return
    }
    if (모든방향바이러스개수 == 0 && 백신개수 == 0) {
        println(2)
        return
    }
    if (모든방향바이러스개수 == 0) {
        println(3)
        return
    }
    println(4)
}

/*
. : 빈 칸
# : 벽
U, D, L, R : 한 방향 바이러스로, 각각 활성화된 방향이 상, 하, 좌, 우다.
A : 모든 방향 바이러스
V : 백신
S : 시작점
E : 탈출구
* */