package backjoon.`20260224`//  거스름돈

fun main(args: Array<String>) {
    //1000-380=620 , 500,100,10,10
    val n = readln().toInt()
    val list = mutableListOf(500,100,50,10,5,1)
    var 잔돈 = 1000 - n
    var sum = 0
    for (it in list) {
        if (잔돈 >= it) {
            val 횟수 = 잔돈 / it // 1 /1
            잔돈 -= 횟수 * it
            sum += 횟수
        }
    }
    print(sum)
}