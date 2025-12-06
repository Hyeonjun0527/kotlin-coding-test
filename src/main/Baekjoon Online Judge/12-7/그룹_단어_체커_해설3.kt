
fun main() {
    val count = List(readln().toInt()) { readln() }.count { word ->
        val visited = mutableSetOf<Char>()
        var prev = ' '

        // word의 모든 문자(c)에 대해 중괄호 안의 로직이 true여야 함
        // 하나라도 false가 나오면 즉시 중단(break)됨 -> 이게 그룹 단어 실패 조건
        word.all { c ->
            if (c != prev && c in visited) {
                false // 1. 여기서 false를 리턴하면 all 함수가 즉시 종료됨 (break 효과)
            } else {
                visited.add(c)
                prev = c
                true // 2. 통과했으면 true 리턴 -> 다음 문자로 넘어감
            }
        }
    }
    println(count)
}