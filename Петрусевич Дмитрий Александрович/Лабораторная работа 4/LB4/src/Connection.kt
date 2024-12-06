class Connection(nodeA: Node, nodeB: Node) : AbstractConnection(nodeA, nodeB) {
    override fun connectNodes() {
        println("Соединяем ${nodeA.name} и ${nodeB.name}.")
        nodeA.connect()
        nodeB.connect()
    }

    override fun disconnectNodes() {
        println("Отключаем ${nodeA.name} и ${nodeB.name}.")
        nodeA.disconnect()
        nodeB.disconnect()
    }
}