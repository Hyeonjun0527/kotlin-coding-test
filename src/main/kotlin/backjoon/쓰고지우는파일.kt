package backjoon

fun main() {
    var n = 100
    var digits = mutableListOf<Int>()
    while (n > 0) {
        digits.add(n % 8)
        n /= 8
    }
    digits.reverse()
}