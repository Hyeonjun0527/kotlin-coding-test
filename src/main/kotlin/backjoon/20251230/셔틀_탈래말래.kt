//  셔틀 탈래 말래 탈래 말래 애매하긴 해

fun main(args: Array<String>) {
    val (a,b,c,d) = readln().split(" ").map { it.toInt() }
    // d : 지각안하는시간
    var plan1 = a + b <= d
    var plan2 = c <= d

    if (plan1 && !plan2) {
        println("Shuttle")
    } else if (!plan1 && plan2) {
        println("Walk")
    } else if (plan1 && plan2) {
        println("~.~")
    } else {
        println("T.T")
    }
}       