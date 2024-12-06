class SimpleNode(override val name: String) : Node {
    override var status: String = "offline"

    override fun connect() {
        status = "online"
        println("$name теперь в сети.")
    }

    override fun disconnect() {
        status = "offline"
        println("$name отключен.")
    }

    override fun displayStatus(): String {
        return "$name статус: $status"
    }
}