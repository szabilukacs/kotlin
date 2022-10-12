import IDictionary.Companion.PATH
import java.io.File
import java.util.TreeSet

object TreeSetDictionary: IDictionary {
    private val words = TreeSet<String>()

    init{
        File(PATH).forEachLine{ add(it) }
    }

    override fun add(word: String): Boolean{
        return words.add(word)
    }

    override fun find(word: String): Boolean{
        return words.find{ it == word } != null
    }

    override fun size() = words.size
}