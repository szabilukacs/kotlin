class ItemService(private val itemRepository: ItemRepository) {

    fun selectRandomItems(number: Int): List<Item> {

        if (number > itemRepository.items.size) {
            println("")
            return emptyList()
        }

        val randomItems = mutableListOf<Item>()

        while (randomItems.size < number) {
            val item = itemRepository.randomItem()
            if (!randomItems.contains(item)) {
                randomItems.add(item)
            }
        }
        return randomItems
    }
}
