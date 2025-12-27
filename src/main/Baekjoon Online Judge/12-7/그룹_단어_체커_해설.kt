fun main() {
    // 1. N을 읽고(3), 그 횟수만큼 readln()을 반복 실행해 리스트를 만듭니다.
    // 결과: words = ["happy", "new", "year"]
    val words = List(readln().toInt()) { readln() }

    val count = words.count { word ->
        // 2. 각 단어별 그룹 단어 체크
        val compressed = word.filterIndexed { index, c ->
            index == 0 || word[index - 1] != c
        }
        compressed.length == compressed.toSet().size
    }

    println(count)
}