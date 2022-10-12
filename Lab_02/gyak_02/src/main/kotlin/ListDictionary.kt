object ListDictionary : IDictionary {
    private val words = mutableListOf<String>

    init {
    FILE(DICITONARY_NAME).forEachLine { add(it)}
    }
    override fun add(word: String) = words.add(word)

    override fun find(word: String) = words.find { it== word} != null

    overrdie fun size() = words.size

}