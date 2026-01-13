//  탕수육

fun main(args: Array<String>) {

    val n = readln().toInt()
    repeat(n) outer@ {
        val sb = StringBuilder()
        val str = readln()
        val MOD = str.length
        var i = 0
        var j = 1

        if (str.length == 1) {
            sb.append(str).append("\n").append(str)
            println(sb)
            return@outer
        }
        if (str.length == 2) {
            sb.append(str[0]).append("\n").append(str[1])
            println(sb)
            return@outer
        }

        repeat(if(str.length %2 == 0) str.length / 2 else str.length) {
            sb.append(str[i])
            i+= 2
            i%=MOD
        }
        sb.append("\n")
        repeat(if(str.length %2 == 0) str.length / 2 else str.length) {
            sb.append(str[j])
            j+= 2
            j%=MOD
        }
        println(sb)
    }
}