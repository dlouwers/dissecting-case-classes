trait BetterManual {
  class GroceryItem(val product: String, val quantity: Int) extends Product {
    import scala.runtime._
    override def canEqual(other: Any): Boolean = other.isInstanceOf[GroceryItem]
    override def equals(other: Any): Boolean = ScalaRunTime._equals(this, other)
    override val productPrefix: String = "GroceryItem"
    override val hashCode: Int = ScalaRunTime._hashCode(this)
    override val productArity: Int = 2
    // ↓↓↓ Highly optimized since it depends on productArity and productPrefix ↓↓↓
    override val toString: String = ScalaRunTime._toString(this)
    override def productElement(index: Int): Any = index match {
      case 0 => product
      case 1 => quantity
      case _ => throw new IndexOutOfBoundsException(s"Unknown index: $index")
    }
  }
  object GroceryItem {
    def apply(product: String, quantity: Int) = new GroceryItem(product, quantity)
    def unapply(groceryItem: GroceryItem): Option[(String, Int)] = Some((groceryItem.product, groceryItem.quantity))
  }
}
