////  단지번호붙이기
//
//fun main(args: Array<String>) {
//    val n = readln().toInt()
//
//    val adj_array = MutableList(n) {
//        readln().map { it.digitToInt() }.toMutableList() }
//
//    val dx = intArrayOf(+1, 0, -1, 0)
//    val dy = intArrayOf(0, +1, 0, -1)//시계 방향
//
//    fun dfs(y: Int,x: Int, cnt: Int):Int {
//        adj_array[y][x] = 0//방문하면 0으로 변경
//        cnt++
//        for (i in 0..3) {
//            if (y + dy < 0 || x + dx < 0) continue
//            dfs(y + dy, x + dx, cnt)
//        }
//    }
//    val cntList = mutableListOf<Int>()
//    adj_array.forEachIndexed { (y, row) ->
//        row.forEachIndexed { (x,it) ->
//            if (it == 0) return@forEach
//            var cnt = 0
//            cntList.add(dfs(y,x,cnt))
//        }
//    }
//    cntList.forEach {
//        println(it)
//    }
//
//}
//
//
///*
//* -------------------------x
//* |
//* |y
//* */