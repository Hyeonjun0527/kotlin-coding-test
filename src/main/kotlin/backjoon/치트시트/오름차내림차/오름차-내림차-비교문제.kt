package backjoon.치트시트.오름차내림차
fun main() {
    // 문자열 배열 입력 또는 하드코딩
    val arr = listOf("apple", "cat", "banana", "kiwi", "dog", "carrot")

    // 정렬: 길이 backjoon.오름차순 → 길이가 같으면 사전순
    val sorted = arr.sortedWith(
        compareBy<String> { it.length }.
        thenBy { it }
    )

    println("원본: $arr")
    println("정렬: $sorted")
}
