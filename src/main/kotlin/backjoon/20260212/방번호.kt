package backjoon.`20260212`//  방 번호

fun main(args: Array<String>) {

    val n = readln()//이걸순화할거임

    val 재고 = MutableList(9) { 1 }//0~8 리스트
    var 필요한세트개수 = 1
    재고[6] = 2
    for (c in n) {//한번순회할건데 cntList조회해보고 cnt 올린느거임. 1잇는데 또 증가하려하면 clear
        var num = c - '0'//int -> char로는 어케하지 char -> int
//        println("가자 ${num}")
        if (num == 9) {
            num = 6
        }
        재고[num]--
        val 카드없음 = 재고[num] < 0//6,9임
        if (카드없음) {
            필요한세트개수++
            for (i in 0..9) 재고[i]++
            재고[6]++
        }
    }
    println(필요한세트개수)

}