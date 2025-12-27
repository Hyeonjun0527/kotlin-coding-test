fun main() {
    // List(...) { readln() } 은 입력을 리스트로 받는 가장 깔끔한 방법이니 유지합니다.
    val count = List(readln().toInt()) { readln() }.count { word ->

        val visited = mutableSetOf<Char>() // '나왔던 문자' 저장소 (repo 대체)
        var prev = ' '     // 이전 문자 기억용
        var isGroup = true // 결과 플래그

        for (c in word) {
            // 핵심 로직: 문자가 바뀌었는데(c != prev), 이미 방문한 적 있다면(c in visited)
            val 문자바뀜 = c != prev
            val 나왔던문자임 = c in visited
            if (문자바뀜 && 나왔던문자임) {
                isGroup = false
                break // 더 볼 필요 없으니 탈출!
            }

            visited.add(c) // 방문 기록 남기기
            prev = c       // 현재 문자를 이전 문자로 갱신
        }

        isGroup // true면 count 1 증가, false면 무시
    }

    println(count)
}