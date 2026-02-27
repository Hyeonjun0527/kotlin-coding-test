package backjoon

fun main() {
    // 1) 프로그래머스 입력 형태와 동일
    val lines: Array<IntArray> = arrayOf(
        intArrayOf(0, 2),
        intArrayOf(-3, -1),
        intArrayOf(-2, 1)
    )

    // ✅ Array<T> -> MutableList<T>
    val arrayToList: MutableList<IntArray> = lines.toMutableList()
    println("arrayToList: ${arrayToList.map { it.joinToString(prefix = "[", postfix = "]") }}")

    // ✅ IntArray -> List<Int> / MutableList<Int>
    val intArray0: IntArray = lines[0]
    val list0: List<Int> = intArray0.toList()
    val mutable0: MutableList<Int> = intArray0.toMutableList()
    println("list0: $list0")
    println("mutable0: $mutable0")

    // ⚠️ 이건 환경에 따라 타입추론이 꼬일 수 있어서 비추
     val bad = lines.map { it.toMutableList() }.toMutableList()
    println("bad: $bad")
    val origin = bad.map { it.toIntArray() }.toTypedArray()//Array<T>로 바꾼다는 의미.

    // ✅ 안전 1) IntArray에서 값 2개를 직접 꺼내서 만들기 (가장 추천)
    val 선분들1: MutableList<MutableList<Int>> =
        lines.map { mutableListOf(it[0], it[1]) }.toMutableList()
    println("선분들1: $선분들1")

    // ✅ 안전 2) IntArray -> List -> MutableList로 단계적으로
    val 선분들2: MutableList<MutableList<Int>> =
        lines.map { it.toList().toMutableList() }.toMutableList()
    println("선분들2: $선분들2")

    // (추가) Array<IntArray>를 먼저 MutableList<IntArray>로 바꾼 다음,
    // 각 IntArray를 MutableList<Int>로 바꿔도 됨
    val 선분들3: MutableList<MutableList<Int>> =
        lines.toMutableList().map { it.toList().toMutableList() }.toMutableList()
    println("선분들3: $선분들3")
}