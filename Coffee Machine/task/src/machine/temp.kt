package machine

/*fun main() {
*//*    println(24 / 2)
    println(12 % 13)
    println(25 / 2)
    println(24 % 2)*//*
*//*    val f: Float = 20.0 + 20.02f // 1
    val l: Long = 10 + 2L        // 2
    val n: Int = l + 5           // 3*//*
*//*    val a: Float = 2f
    val b: Byte = 2 + 3            //1
    val s: Short = 2 + b           //2
    val n: Int = s.toByte() + 2    //3
    val l: Long = n + 4            //4
    val f: Float = l.toFloat() + 1 //5
    val d: Double = f / 1          //6*//*
*//*    val magic = 2_000_000_000
    val giant = 3_000_000_000
    val x = 100L - magic
    val y = (magic + giant) % 10
    val z = magic + magic + 1
    val w = giant - magic
    val list: List<Any> = listOf(x, y, z, w)
    for (attr in list) {
        when (attr) {
            is String -> println(attr.toString() + "is string")
            is Int -> println(attr.toString() + "is int")
            is Double -> println(attr.toString() + "is double")
            is Long -> println(attr.toString() + "is long")
            is Byte -> println(attr.toString() + "is byte")
            is Short -> println(attr.toString() + "is short")
            is Float -> println(attr.toString() + "is float")
        }
    }*//*
}*/
fun main() {
    val smartOne = Smartphone("Ericsong")
    smartOne.price = -24
    val smartTwo = Smartphone("iNokhe")
    print(smartTwo.price - smartOne.price)
}

class Smartphone(val name: String) {
    var price: Int = -5
        get() = name.length - field
}