package backjoon.`20260212`//  수열의 점수

fun main(args: Array<String>) {
    //양수끼리 모아서 내림차순 정렬하고, 2개씩 모아서 곱해줘.
//하나 남으면 그냥 합쳐.
// 그리고, 음수끼리 모아서, 내림차순 정렬하고, 2개씩모아서 곱해줘.
// 그리고 하나 남으면 그냥 합쳐
    // 0은 그냥 버려

    val n = readln().toInt()
    val 양수들 = mutableListOf<Int>()
    val 음수들 = mutableListOf<Int>()
    repeat(n) {
        val input = readln().toInt()
        if (input > 0) {
            양수들.add(input)
        }
        if (input < 0) {
            음수들.add(input)
        }
    }
    양수들.sortDescending()
    음수들.sort()

    var 전환 = 0
    var temp = 1
    val 양수들2 = mutableListOf<Int>()
    for (i in 0 until 양수들.size) {
        전환++
        temp *= 양수들[i]
        if (전환 == 2) {
            양수들2.add(temp)
            전환 = 0
            temp = 1
        }
        if (양수들.size % 2 != 0 && (i == 양수들.lastIndex)) {
            양수들2.add(양수들[i])
        }
    }
    val 음수들2 = mutableListOf<Int>()

    var temp2 = 1
    전환 = 0
    for (i in 0 until 음수들.size) {
        전환++
        temp2 *= 음수들[i]
        if (전환 == 2) {
            음수들2.add(temp2)
            전환 = 0
            temp2 = 1
        }
        if (음수들.size % 2 != 0 && i == 음수들.lastIndex) {
            음수들2.add(음수들[i])
        }
    }

    println(양수들2.sum() + 음수들2.sum())
}