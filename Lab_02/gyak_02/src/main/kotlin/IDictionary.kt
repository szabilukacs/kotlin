interface IDictionary{
    fun add(word: String): Boolean
    fun find(word: String): Boolean
    fun size(): Int

    companion object{
        val PATH = "C:\\Users\\lukac\\Desktop\\IV_ev\\Android\\kotlin_labor\\kotlin\\Lab_02\\gyak_02\\src\\main\\words.txt"
    }
}