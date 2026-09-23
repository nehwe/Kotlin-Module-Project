import java.util.Scanner

class View<T> (
    val items: List<T>,
    val labels: Map<String, String>,
) {
    val scanner = Scanner(System.`in`)

    fun getUserChoice(): Int {
        var choice: Int = -1
        while (choice !in 0..items.size + 2) {
            print("Выберите пункт меню от 0 до ${items.size + 1}: ")
            val userInput = scanner.nextLine().trim()
            // проверяем на число
            if (userInput matches Regex("^\\d+$")) {
                choice = userInput.toInt()
            }
        }
        return choice
    }

    fun getName(): String {
        var name: String
        name = ""
        while (name.isBlank()) {
            print("Введите имя ${labels["genitiveName"]}" +
                    "\n(не должно быть пустым): ")
            name = scanner.nextLine()
        }
        return name
    }

    fun getText(): List<String> {
        val text = mutableListOf<String>()
        var str: String
        var isComplete = false
        println("=============" +
                "\nОкно ввода текста" +
                "\nдля выхода из окна введите [:q]")
        while (!isComplete) {
            str = scanner.nextLine()
            if (str == ":q") {
                isComplete = true
            } else {
                text.add(str)
            }
        }
        println("=============")
        return text
    }
    //  Алиса AI подсказала список с лямбдами
    fun <T : Named> handle(
        choice: Int,
        items: MutableList<T>,
        onCreate: () -> Boolean,
        onView: (T) -> Unit,
        onExit: () -> Boolean
    ): Boolean {
        val commands = mutableListOf<() -> Boolean>()
        commands.add { onCreate() }
        if (!items.isEmpty()) {
            items.forEach { item ->
                commands.add {
                    onView(item)
                    true
                }
            }
        }
        commands.add { onExit() }

        return commands[choice]()
    }


    // отображаем меню, с выводом списка
    fun <T : Named> showMenu(
        items: MutableList<T>,
        title: String
    ) {

        println("=== $title ===")
        println()

        println("Список ${labels["genitivePluralName"]}:")
        var i = 0
        println("0. Создать ${labels["accusativeName"]}")
        if (!items.isEmpty()) {
            for (item in items) {
                println("${++i}. ${item.name}")
            }
        }
        println("${++i}. Выход")

        println()
        repeat(title.length + 8) {
            print("=")
        }
        println()
    }
}
