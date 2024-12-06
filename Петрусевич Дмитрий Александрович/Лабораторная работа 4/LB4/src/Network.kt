class Network {
    private val nodes: MutableList<Node> = mutableListOf()
    private val connections: MutableList<AbstractConnection> = mutableListOf()

    fun addNode(node: Node) {
        nodes.add(node)
        println("Узел ${node.name} добавлен в сеть.")
    }

    fun removeNode(node: Node) {
        if (nodes.remove(node)) {
            println("Узел ${node.name} удалён из сети.")
            connections.removeIf { it.nodeA == node || it.nodeB == node }
        } else {
            println("Узел ${node.name} не найден в сети.")
        }
    }

    fun createConnection(nodeA: Node, nodeB: Node) {
        if (nodes.contains(nodeA) && nodes.contains(nodeB)) {
            val connection = Connection(nodeA, nodeB)
            connections.add(connection)
            connection.connectNodes()
        } else {
            println("Один или оба узла не найдены в сети.")
        }
    }

    fun monitorNodes() {
        if (nodes.isEmpty()) {
            println("В сети нет узлов.")
        } else {
            for (node in nodes) {
                println(node.displayStatus())
            }
        }
    }

    fun listNodes() {
        if (nodes.isEmpty()) {
            println("В сети нет узлов.")
        } else {
            nodes.forEachIndexed { index, node ->
                println("${index + 1}. ${node.name}")
            }
        }
    }

    // Метод для поиска узла по индексу
    fun getNodeByIndex(index: Int): Node? {
        return if (index in 0 until nodes.size) {
            nodes[index]
        } else {
            null
        }
    }
}