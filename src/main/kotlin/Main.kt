import java.util.Scanner

fun main() {
    val archives = mutableListOf<Archive>()
    val view = View(archives, Archive.localized)
    var isHold = true

    while (isHold) {
        view.showMenu(archives, "Главное меню")
        var choice = view.getUserChoice()

        isHold = view.handle(
            choice,
            archives,
            onCreate =  { archives.add(Archive(view.getName())) },
            onView = {
                val archive = archives[choice - 1]
                val notes = archive.content
                val viewArchive = View(notes, Note.localized)
                while (isHold) {
                    viewArchive.showMenu(
                        notes,
                        Archive.localized["name"] +": " + archive.name
                    )
                    choice = viewArchive.getUserChoice()
                    isHold = viewArchive.handle(
                        choice,
                        notes,
                        onCreate = {
                            var emptyContent: Boolean
                            emptyContent = true
                            val name = viewArchive.getName()
                            val content = viewArchive.getText()
                            for (item in content) {
                                if (!item.isBlank()) {
                                    emptyContent = false
                                    break
                                }
                            }
                            if (!emptyContent) {
                                notes.add(Note(name, content))
                            } else {
                                true
                            }
                                   },
                        onView = {
                            val note = notes[choice - 1]
                            note.getNote()
                            while (isHold) {
                                Scanner(System.`in`).nextLine()
                                isHold = false
                            }
                        },
                        onExit = { false }
                    )
                }
                     },
            onExit = { false }
        )
    }
}