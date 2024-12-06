interface Node {
    val name: String
    var status: String

    fun connect()
    fun disconnect()
    fun displayStatus(): String
}