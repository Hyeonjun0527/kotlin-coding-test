import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main1() {
    val name = "Kotlin"
    val age = 10;

    println("Hello $name! You are $age years old.")
}

fun main2() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())

    repeat(n) { i ->
        sb.append("i = ").append(i + 1)
            .append(", num = ").append(st.nextToken())
            .append('\n')
    }

    print(sb)
}

fun main3() {
    val n = readln().toInt()        // 첫 번째 줄: n 읽기
    val nums = readln().split(' ')  // 두 번째 줄: 공백으로 구분된 숫자들 읽기

    repeat(n) { i ->
        println("i = ${i + 1}, num = ${nums[i]}")
    }
}

fun main() {
    println("1번")
    val n = readln().toInt()
    val result = readln()
        .split(' ')
        .map { it.toInt() }
        .filter { it % 2 == 0 }
        .map { it * 2 }
    result.forEach { print("$it ") }//4 8

    println("\n2번")
    val sb = StringBuilder()
    result.forEach{ sb.append("$it ") }
    println(sb.toString().trim())//4 8

    println("3번")
    listOf(1, 2, 3).forEach { num -> println(num) }

    println("4번")
    println(result.joinToString(", ","[","]"))

    println("5번")
    val sb1 = StringBuilder("---")
    println(listOf(1, 2, 3).joinToString { "${sb1.toString() + it.toString()} ---$it" })

    println("6번")
    println(sb1)

}