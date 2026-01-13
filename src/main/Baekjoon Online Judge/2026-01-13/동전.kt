
fun main() {
    var (n, k) = readln().split(" ").map { it.toInt() }
    val l = IntArray (n) {
        readln().toInt()
    }
    val list = l.sortedByDescending { it }
    var cnt = 0
    for (x in list) {
        if (x > k) {
            continue
        }
        cnt += k / x
        k = k % x
    }
    print(cnt)
}