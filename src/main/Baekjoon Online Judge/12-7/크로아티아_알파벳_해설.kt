fun main() {
    val s = readln()
    var i = 0
    var count = 0

    while (i < s.length) {

        val 길이안넘음 = i + 3 <= s.length
        val 매칭됨 = 길이안넘음 &&
                s.substring(i, i + 3) == "dz="

        if (매칭됨) {
            count++
            i += 3
            continue
        }

        val 길이안넘음2 = i + 2 <= s.length
        val 매칭됨2 = 길이안넘음2 &&
                s.substring(i, i + 2) in listOf("c=", "c-", "d-", "lj", "nj", "s=", "z=")

        if (매칭됨2) {
            count++
            i += 2
            continue
        }

        count++
        i++
    }

    println(count)
}
