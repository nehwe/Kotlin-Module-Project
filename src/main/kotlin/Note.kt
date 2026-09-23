data class Note(
    override val name: String,
    override val content: List<String> = mutableListOf()
) : Named, ContentProvider<String> {
    companion object {
        val localized = mapOf(
            "name" to "заметка",
            "accusativeName" to "заметку",
            "genitiveName" to "заметки",
            "pluralName" to "заметки",
            "genitivePluralName" to "заметок"
        )
    }
    fun getNote() {
        val str = "=== ${localized["name"]}: ${this.name} ==="
        println(str)
        content.forEach { println(it) }
        repeat(str.length) {
            print("=")
        }
        println("\nдля выхода нажмите [Enter]")
    }
}