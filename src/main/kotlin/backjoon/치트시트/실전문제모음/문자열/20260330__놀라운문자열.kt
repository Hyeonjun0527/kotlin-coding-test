package backjoon.치트시트.실전문제모음.문자열

// 알고리즘 분류: 문자열

fun main(args: Array<String>) {
    var sb = StringBuilder()
    while(true) {
        var line = readln()
        if (line == "*") break

        var 모두유일 = true

        var 최대쌍 = line.length - 2
        for (쌍 in 0..최대쌍) {
            var i = 0
            var j = i + 쌍 + 1
            var 총쌍개수 = line.length - 1 - 쌍
            var set = HashSet<String>()
            while (j <= line.length - 1) {
                set.add(line[i].toString() + line[j].toString())
                i++
                j++
            }
            if (set.size != 총쌍개수) {
                모두유일 = false
            }
        }

        if (모두유일) {
            sb.append("$line is surprising.").append("\n")
        } else {
            sb.append("$line is NOT surprising.").append("\n")
        }
    }
    println(sb)
}
