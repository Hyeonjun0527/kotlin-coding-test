package backjoon.치트시트.실전문제모음.greedy

// 알고리즘 분류: greedy

fun main(args: Array<String>) {
    /*
    회의를 최대한 많이 해야함. 겹칠수 없음.
    무조건 시작시간 빠른거부터 하면 되나? => 아님 0~12면 그냥 그거때문에 다 잡아먹음
    그럼 회의빨리끝나는거부터 하면 되나? => 그럴거같기는해...
    그리고, 두번째로는 끝나는 시간 오름차순으로 정렬하고 가능한거만 택.
    * */
    val n = readln().toInt()
    data class 회의(val 시작시간:Int, val 끝나는시간:Int)
    val 회의들 = MutableList(n) {
        val (시작시간,끝나는시간) = readln().split(" ").map { it.toInt() }
        회의(시작시간,끝나는시간)
    }
    회의들.sortWith(
        compareBy<회의> { it.끝나는시간 }
            .thenBy { it.시작시간 }
    )
    //회의를 하나 꺼내 택해. 택한다는건 머지 끝나는 시간 s 설정해. 끝나는시간
    var 현재시간 = 0
    var 회의수 = 0
    회의들.forEach {
        if (현재시간 <= it.시작시간) {
            회의수++
            현재시간 = it.끝나는시간
        }
    }
    println(회의수)
}
