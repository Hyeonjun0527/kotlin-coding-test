

fun main(args: Array<String>) {
    val inputList = ArrayList<Int>()
    repeat(28) {
        inputList.add(readln().toInt())
    }
    val sol = (1..30).filter {it !in inputList}
    sol.forEach { println(it) }
}