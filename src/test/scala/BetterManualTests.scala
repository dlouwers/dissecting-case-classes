import org.scalatest._

class BetterManualTests extends WordSpec with Matchers with BetterManual {

  "The GroceryItem case class" should {
    "be immutable" in {
      val item = GroceryItem("Carrot", 1)
      //item.product = "Peas" <-- this will not compile
    }
    "have a constructor on its companion object" in {
      GroceryItem("Carrot", 1)
    }
    "have a sensible default equals implementation" in  {
      GroceryItem("Carrot", 1) shouldEqual GroceryItem("Carrot", 1)
      Set(GroceryItem("Carrot", 1), GroceryItem("Carrot", 1)) shouldEqual Set(GroceryItem("Carrot", 1))
    }
    "have a sensible toString method implementation" in {
      GroceryItem("Carrot", 1).toString shouldEqual "GroceryItem(Carrot,1)"
    }
    "have a sensible toHashCode method implementation" in {
      GroceryItem("Carrot", 1).hashCode shouldEqual GroceryItem("Carrot", 1).hashCode
      // Set greater than 4 elements uses hashCode
      scala.collection.immutable.HashSet(GroceryItem("Carrot", 1), GroceryItem("Carrot", 1)) shouldEqual Set(GroceryItem("Carrot", 1))
    }
    "be deconstructible" in {
      val GroceryItem(product: String, quantity: Int) = GroceryItem("Carrot", 1)
      //val GroceryItem(product: Int, quantity: Int) = GroceryItem("Carrot", 1) <-- demonstrates compile time checking
      GroceryItem(product, quantity) shouldEqual GroceryItem("Carrot", 1)
      GroceryItem(product, quantity) match {
        case GroceryItem("Carrot", 1) =>
        case _ => fail("This was not the carrot I was expecting!")
      }
    }
    "have its fields accesible by index" in {
      GroceryItem("Carrot", 1).productArity shouldEqual 2
      GroceryItem("Carrot", 1).productElement(0) shouldEqual "Carrot"
      GroceryItem("Carrot", 1).productElement(1) shouldEqual 1
      // GroceryItem("Carrot", 1).productElement(1) shouldEqual "One" <-- demonstrates runtime checking
    }
    "provide an iterator over its fields" in {
      for(field <- GroceryItem("Carrot", 1).productIterator) yield field match {
          case (product: String, quantity: Int) =>
          case _ => fail("This was no grocery item-like!!")
      }
    }
  }
}
