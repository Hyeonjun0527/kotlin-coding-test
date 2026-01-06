
//  종이2에서 종이1을 뺏을때 x좌표가 k만큼 차이가 나면 10-k만큼 겹침.
//  y좌표가 l만큼 차이나면 l만큼 겹침.
//  둘이 곱해. k,l은 0이될수도잇음. 종이 3개잇으면 3c2번 비교하면 댐.
//  즉, 집합으로...2개택. 그렇게 총 겹치는거 더하고, 전체에서 빼면댐.
// 이렇게는 못풀음 0~99중 겹침까지 고려해야되기 때문.
// “전체합에서 겹치는 걸 빼는 순간, 3중,n중 겹침(이상)이 있는지부터 본다.”
fun main(args: Array<String>) {
    data class Dot(val x: Int, val y: Int)
    val n = readln().toInt()
    //2차원배열어케만들지 mutable[x][y]
    val table = MutableList(100) {
        MutableList(100) { 0 }
    }

    repeat(n) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        for (i in a until a+10) {
            for (j in b until b+10) {
                    table[i][j] = 1
            }
        }
    }

    print(table.sumOf { it.sum() })
}


/*
MutableList(100) { MutableList(100) { 0 } }
List(100) { List(100) { 0 } }


* */