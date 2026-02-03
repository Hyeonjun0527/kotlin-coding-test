
fun main(args: Array<String>) {
    //n c
    val (n, c) = readln().split(" ").map { it.toInt() }

    var a = n
    var b = n //(a, b)
    repeat(c) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        //n n 에서 x,y x를 택할거냐 y를 택할거냐. 덜손해보는쪽으로 해야함.
        // x 를 택해야함.

        if (x >= a || y >= b) {
            return@repeat
        }

        if (x * b >= y * a) {
            a = x
        } else {
            b = y
        }
    }
    println(a*b)
}