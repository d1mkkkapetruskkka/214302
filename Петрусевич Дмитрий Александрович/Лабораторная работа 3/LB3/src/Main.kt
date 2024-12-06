import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val students = mutableListOf<List<Any>>()

    while (true) {
        println("\nМеню:")
        println("1. Добавить студента")
        println("2. Показать средние оценки студентов")
        println("3. Просмотреть список студентов")
        println("4. Выйти")

        print("Выберите опцию: ")
        val choice = scanner.nextInt()

        when (choice) {
            1 -> {
                // Добавление студента
                print("Введите имя студента: ")
                val name = scanner.next()
                val grades = mutableListOf<Int>()

                print("Введите количество оценок: ")
                val count = scanner.nextInt()

                for (i in 1..count) {
                    print("Введите оценку #$i: ")
                    grades.add(scanner.nextInt())
                }

                students.add(listOf(name) + grades)
                println("Студент $name добавлен.")
            }
            2 -> {
                // Показать средние оценки
                if (students.isEmpty()) {
                    println("Нет данных о студентах.")
                } else {
                    val averageScores = students.map { student ->
                        val name = student[0] as String
                        val scores = student.drop(1).map { it as Int }
                        name to scores.average()
                    }.toMap()

                    println("Средние оценки студентов: $averageScores")
                }
            }
            3 -> {
                // Просмотр списка студентов
                if (students.isEmpty()) {
                    println("Список студентов пуст.")
                } else {
                    println("Список студентов:")
                    for (student in students) {
                        val name = student[0] as String
                        val grades = student.drop(1).joinToString(", ")
                        println("$name: [$grades]")
                    }
                }
            }
            4 -> {
                // Выход из программы
                println("Выход из программы.")
                return
            }
            else -> {
                println("Некорректный выбор. Пожалуйста, попробуйте снова.")
            }
        }
    }
}