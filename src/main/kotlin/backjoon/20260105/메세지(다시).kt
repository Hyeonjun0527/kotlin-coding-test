fun main() {
    val sb = StringBuilder()
    var group = 1
    while (true) {
        val n = readln().toInt()
        if (n == 0) break
        //사람 5명 쪽지 4개
        val names = MutableList(n) { "" }
        val papers = MutableList(n) { MutableList(n-1) { "" } }

        var i = 0;//i : 0 ~ n-1

        sb.append("Group ${group}\n")

        for (i in 0 until n) {
            val inputs = readln().trim().split(" ")
            names[i] = inputs[0]
            for (t in 0 until n - 1) {
                papers[i][t] = inputs[t + 1]
            }
        }

        var found = false

        for (i in 0 until n) {
            for (t in 0 until n - 1) {
                if (papers[i][t] == "N") {
                    val writer = (i + (n - t - 1)) % n
                    sb.append("${names[writer]} was nasty about ${names[i]}\n")
                    found = true
                }
            }
        }

        if (!found) sb.append("Nobody was nasty\n")
        sb.append('\n')
        group++
    }

    print(sb.toString())
}