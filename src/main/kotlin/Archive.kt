data class Archive(
    override val name: String,
    override val content: MutableList<Note> = mutableListOf()
) : Named, ContentProvider<Note> {
    companion object {
        val localized = mapOf(
            "name" to "архив",
            "accusativeName" to "архив",
            "genitiveName" to "архива",
            "pluralName" to "архивы",
            "genitivePluralName" to "архивов"
        )
    }
}