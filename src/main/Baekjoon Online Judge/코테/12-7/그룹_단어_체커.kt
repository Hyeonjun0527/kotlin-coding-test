

fun main(args: Array<String>) {
    val n = readln().toInt()
    var cnt = 0
    repeat(n) {
        val s = readln()
        val repo = mutableListOf<Char>()
        var isGroupWord = true
        //repo에는 이번에 나오면 안될 문자를 저장할거야.
        //인덱스와 요소 함께 순회하는 forEach는 없나
        var sw = false//pppp이런거 중이면 스위치 온인거임.
        var beforeWord = '1'
        s.forEach { c ->
            sw = (beforeWord == c)
            repo.forEach { ch ->
                if (c == ch && !sw) isGroupWord = false
            }
            beforeWord = c
            repo.add(c)
        }
        if (isGroupWord) cnt++

    }
    print(cnt)
}
//