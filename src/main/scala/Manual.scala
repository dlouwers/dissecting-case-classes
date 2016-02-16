trait Manual {
  class GroceryItem(val product: String, val quantity: Int) {
    override def equals(other: Any): Boolean = other match {
      case possibleMatch: GroceryItem => possibleMatch.product == product && possibleMatch.quantity == quantity
      case _ => false
    }
    override val toString: String = s"GroceryItem($product,$quantity)"
    override val hashCode: Int = { // As per Josh Bloch's Effective Java
      var result = 42
      result = 37 * result + product.hashCode
      37 * result + quantity.hashCode
      // or:
      //Seq(product.hashCode, quantity.hashCode).foldLeft(42)(37 * _ + _)
    }
    val productArity: Int = 2
    def productElement(index: Int): Any = index match {
      case 0 => product
      case 1 => quantity
      case _ => throw new IndexOutOfBoundsException(s"Unknown index: $index")
    }
    val productIterator = Array(product, quantity).iterator
  }
  object GroceryItem {
    def apply(product: String, quantity: Int) = new GroceryItem(product, quantity)
    def unapply(groceryItem: GroceryItem): Option[(String, Int)] = Some((groceryItem.product, groceryItem .quantity))
  }
}
