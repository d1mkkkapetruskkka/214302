fun main() {
    while (true) {
        println("Выберите действие:")
        println("1. Вынесение общего множителя за скобки")
        println("2. Упрощение математического выражения")
        println("Введите 'exit' для выхода")

        val choice = readLine()?.trim()

        when (choice) {
            "1" -> {
                println("Введите математическое выражение для вынесения за скобки:")
                val input = readLine() ?: break
                try {
                    val optimizedExpression = optimizeExpression(input)
                    println("Упрощенное выражение: $optimizedExpression")
                } catch (e: Exception) {
                    println("Ошибка: ${e.message}")
                }
            }
            "2" -> {
                println("Введите математическое выражение для упрощения:")
                val input = readLine() ?: break
                try {
                    val result = simplifyNumericalExpressions(input)
                    println("Результат упрощения: $result")
                } catch (e: Exception) {
                    println("Ошибка: ${e.message}")
                }
            }
            "exit" -> break
            else -> println("Неверный выбор. Пожалуйста, попробуйте снова.")
        }
    }
}

fun optimizeExpression(expression: String): String {
    val trimmedExpression = expression.replace("\\s".toRegex(), "")
    return factorOutCommonTerm(trimmedExpression)
}

fun factorOutCommonTerm(expression: String): String {
    val terms = expression.split("(?=[+-])".toRegex()).map { it.trim() }
    val coefficients = mutableListOf<Int>()
    val variables = mutableListOf<String>()

    for (term in terms) {
        val regex = Regex("([+-]?\\d*)([a-zA-Z]+)")
        val match = regex.find(term)

        if (match != null) {
            val coefficient = match.groupValues[1].toIntOrNull() ?: if (term.startsWith('-')) -1 else 1
            val variable = match.groupValues[2]
            coefficients.add(coefficient)
            variables.add(variable)
        }
    }

    val commonCoefficient = coefficients.reduce { acc, i -> gcd(acc, i) }

    val newTerms = mutableListOf<String>()
    for (i in coefficients.indices) {
        val newCoefficient = coefficients[i] / commonCoefficient
        val formattedTerm = when {
            newCoefficient == 1 -> "${variables[i]}"
            newCoefficient == -1 -> "-${variables[i]}"
            else -> "${newCoefficient}${variables[i]}"
        }
        newTerms.add(formattedTerm)
    }

    val result = newTerms.joinToString(" + ").replace(" + -", " - ")
    return "$commonCoefficient($result)"
}

fun simplifyNumericalExpressions(expression: String): String {
    val regex = Regex("([0-9]+)([+\\-*\\/\\^])([0-9]+)")
    var simplifiedExpression = expression

    while (regex.containsMatchIn(simplifiedExpression)) {
        simplifiedExpression = regex.replace(simplifiedExpression) { matchResult ->
            val left = matchResult.groupValues[1].toInt()
            val operator = matchResult.groupValues[2]
            val right = matchResult.groupValues[3].toInt()

            when (operator) {
                "+" -> (left + right).toString()
                "-" -> (left - right).toString()
                "*" -> (left * right).toString()
                "/" -> if (right != 0) (left / right).toString() else throw ArithmeticException("Деление на ноль")
                "^" -> Math.pow(left.toDouble(), right.toDouble()).toInt().toString()
                else -> matchResult.value
            }
        }
    }

    return simplifyConstants(simplifiedExpression)
}

fun simplifyConstants(expression: String): String {
    val regex = Regex("([0-9]*)([a-zA-Z])(\\+|-|\\*|/|\\^)\\s*([0-9]*)([a-zA-Z])")
    var simplifiedExpression = expression

    while (regex.containsMatchIn(simplifiedExpression)) {
        simplifiedExpression = regex.replace(simplifiedExpression) { matchResult ->
            val leftCoefficient = matchResult.groupValues[1].toIntOrNull() ?: 1
            val leftVariable = matchResult.groupValues[2]
            val operator = matchResult.groupValues[3]
            val rightCoefficient = matchResult.groupValues[4].toIntOrNull() ?: 1
            val rightVariable = matchResult.groupValues[5]

            if (leftVariable == rightVariable) {
                when (operator) {
                    "+" -> "${leftCoefficient + rightCoefficient}$leftVariable"
                    "-" -> "${leftCoefficient - rightCoefficient}$leftVariable"
                    "*" -> "${leftCoefficient * rightCoefficient}$leftVariable^2"
                    "/" -> if (rightCoefficient != 0) "${leftCoefficient / rightCoefficient}$leftVariable" else throw ArithmeticException("Деление на ноль")
                    else -> matchResult.value
                }
            } else {
                "${matchResult.value}"
            }
        }
    }

    return simplifiedExpression
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) Math.abs(a) else gcd(b, a % b)
}