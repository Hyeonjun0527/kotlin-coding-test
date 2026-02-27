package backjoon

fun main() {
    fun solution(arr: IntArray): IntArray {
        val 덱 = ArrayDeque<Int>()

        for (값 in arr) {
            if (덱.isEmpty() || 덱.last() != 값) {
                덱.addLast(값)
            }
        }

        // ArrayDeque -> IntArray
        return 덱.toIntArray()
    }

    val 테스트1 = intArrayOf(1, 1, 3, 3, 0, 1, 1)
    val 결과1 = solution(테스트1)
    println(결과1.joinToString(prefix = "[", postfix = "]"))

    val 테스트2 = intArrayOf(4, 4, 4, 3, 3)
    val 결과2 = solution(테스트2)
    println(결과2.joinToString(prefix = "[", postfix = "]"))
}