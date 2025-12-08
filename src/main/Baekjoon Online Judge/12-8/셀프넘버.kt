//  셀프 넘버
//1~10000 모두 생성자가각자 잇겟지. 생성자 구해.
// 구하고, 그거 set에 쭈욱 모아. 1~10000리스트의 모든요소 돌아. 요소가 set에 잇으면 지워
fun main(args: Array<String>) {
    //it = 357 -> 357 + 3 + 5 + 7 이런거는 (모듈러 + 나머지) 방법 사용
    //
    //%10 7
    /*
    * 357 -> 35 7
    * 35 -> 3 5
    * 3 -> 0 3
    * */
    //%10
//    x.toString().sumOf { x.code() } 도 가능.
    val mySet = (1..10000).map {
        var x = it
        var sum = x
        while (x > 0) {
            sum += x % 10
            x /= 10
        }
//        val x = it
//        val sum = x.toString().sumOf { it - '0' }
        sum
    }.toSet()

    (1..10000).filter { it !in mySet }.forEach(::println)
}