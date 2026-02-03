
fun main(args: Array<String>) {
    var a = readln()
    var cnt = 0
    while (a.length > 1) {
        val temp = a.fold(1L) { acc, c -> acc * c.digitToInt() }
        a = temp.toString()
        cnt++
    }
    print(cnt)
}
//        var temp = 1L
//        for (c in a) {
//            temp *= c.digitToInt()
//        }