abstract class AbstractConnection(val nodeA: Node, val nodeB: Node) {
    abstract fun connectNodes()
    abstract fun disconnectNodes()
}