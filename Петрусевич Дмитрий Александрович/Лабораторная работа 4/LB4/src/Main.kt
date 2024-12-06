fun main() {
    val network = Network()
    val scanner = java.util.Scanner(System.`in`)

    while (true) {
        println("\n--- Меню управления сетью ---")
        println("1. Добавить узел")
        println("2. Удалить узел")
        println("3. Создать соединение")
        println("4. Отключить узел")
        println("5. Мониторинг узлов")
        println("6. Выход")
        print("Выберите опцию: ")

        val choice = scanner.nextInt()
        scanner.nextLine()

        when (choice) {
            1 -> {
                print("Введите имя узла для добавления: ")
                val nodeName = scanner.nextLine()
                val newNode = SimpleNode(nodeName)
                network.addNode(newNode)
            }
            2 -> {
                println("Выберите узел для удаления:")
                network.listNodes()
                print("Введите номер узла для удаления: ")
                val nodeIndex = scanner.nextInt() - 1
                scanner.nextLine()
                val nodeToRemove = network.getNodeByIndex(nodeIndex)
                if (nodeToRemove != null) {
                    network.removeNode(nodeToRemove)
                } else {
                    println("Узел с номером ${nodeIndex + 1} не найден.")
                }
            }
            3 -> {
                println("Выберите первый узел:")
                network.listNodes()
                print("Введите номер первого узла: ")
                val firstNodeIndex = scanner.nextInt() - 1
                scanner.nextLine()

                println("Выберите второй узел:")
                network.listNodes()
                print("Введите номер второго узла: ")
                val secondNodeIndex = scanner.nextInt() - 1
                scanner.nextLine()

                val nodeA = network.getNodeByIndex(firstNodeIndex)
                val nodeB = network.getNodeByIndex(secondNodeIndex)
                if (nodeA != null && nodeB != null) {
                    network.createConnection(nodeA, nodeB)
                } else {
                    println("Один или оба узла не найдены.")
                }
            }
            4 -> {
                println("Выберите узел для отключения:")
                network.listNodes()
                print("Введите номер узла для отключения: ")
                val nodeIndex = scanner.nextInt() - 1
                scanner.nextLine()
                val nodeToDisconnect = network.getNodeByIndex(nodeIndex)
                if (nodeToDisconnect != null) {
                    nodeToDisconnect.disconnect()
                } else {
                    println("Узел с номером ${nodeIndex + 1} не найден.")
                }
            }
            5 -> {
                println("Мониторинг узлов в сети:")
                network.monitorNodes()
            }
            6 -> {
                println("Выход из программы.")
                break
            }
            else -> {
                println("Неверный выбор. Пожалуйста, попробуйте снова.")
            }
        }
    }
}