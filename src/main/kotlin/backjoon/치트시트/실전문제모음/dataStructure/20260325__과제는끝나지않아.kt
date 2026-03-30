package backjoon.치트시트.실전문제모음.dataStructure

// 알고리즘 분류: dataStructure

fun main(args: Array<String>) {

    val 총분 = readln().toInt()
    data class 과제(var 점수: Int, var 시간:Int)
    val stack = ArrayDeque<과제>()
    var sum = 0
    var 현재분 = 0
    repeat(총분) {
        val inputs = readln().split(" ").map { it.toInt() }
        val 명령 = inputs[0]
        if (명령 == 1) {
            val 점수 = inputs[1]
            var 걸리는시간 = inputs[2]
            걸리는시간--
            stack.addFirst(과제(점수,걸리는시간))
        } else {
            if (stack.isNotEmpty()) {
                var 과제 = stack.removeFirst()
                과제.시간--
                stack.addFirst(과제)
            }
        }

        if (stack.isNotEmpty()) {
            var 과제 = stack.removeFirst()
//            println(과제)
            if (과제.시간 == 0) {
                sum += 과제.점수
            } else {
                stack.addFirst(과제)
            }
        }
        현재분++
    }
    print(sum)
    //순서대로 과제를 한다
    //과제진행중에 새로운게 나왔는지 새로운게 나왔다면 스택에 하고 남은 시간 넣기.
    //스택을써야해.
    //과제.시간--는 안되네
}
