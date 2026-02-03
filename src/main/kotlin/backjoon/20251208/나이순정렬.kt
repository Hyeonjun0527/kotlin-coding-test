
fun main(args: Array<String>) {
    data class Item (val age: Int, val name: String)
    val list = List(readln().toInt()) {
        val (나이, 이름) = readln().split(" ")
        Item(나이.toInt(), 이름)
    }
//    list.sortedBy { (it.age) }
    list.sortedWith(
        compareBy<Item> { it.age }
    ).forEach { println("${it.age} ${it.name}") }
}