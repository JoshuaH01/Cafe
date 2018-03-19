package CoffeeShop

import scala.util.{Failure, Success, Try}
import models._

object CoffeeShop extends App {


  case class Water(temperature: Int = 40)

  abstract class Coffee(val water : Water, val groundBeans: GroundBeans)

  case class GrindingBeanException(message: String) extends Exception(message)


  def grindBeans(coffeeBeans: Option[CoffeeBeans]): Try[GroundBeans] = {
    coffeeBeans match {
      case Some(_: ArabicaBeans) => Success(GroundArabicaBeans())
      case _ => Failure(GrindingBeanException("No beans provided!"))
    }
  }

  def heat(water: Water): Water = {
    if (water.temperature >= 40) {
      water
    } else {
      Water(60)
    }
  }

  def brewCoffee(water: Water, groundBeans: GroundBeans): CoffeeWithOutMilk = {
    water.temperature match {
      case (w) if w > 40 =>
        CoffeeWithOutMilk(Water(50), groundBeans)
      case (w) if w <= 40 =>
        throw new IllegalArgumentException("The water is to cold")
      case (_) =>
        throw new IllegalArgumentException("Error, you don't know how to make coffee")
    }
  }

  def frothMilk(milk: Milk): FrothedMilk = {
    milk match {
      case m: WholeMilk => new FrothedWholeMilk
      case _: SemiSkimmedMilk => throw new IllegalArgumentException("Can not use Semi Skimmed Milk")
    }
  }

  def addMilk(coffee: Coffee, milk: Milk): CoffeeWithMilk = {
    val w = coffee.water.copy(coffee.water.temperature - 10)
    CoffeeWithMilk(w, coffee.groundBeans, milk)
  }


  /// instantiate beans
  // instatiate milk
  // instantiate water
  // heat water
  // grind beans
  // get coffee brewed
  // if (wantMilk?) then {
  //  froth milk as well
  //  addMilk(coffee)
  //}
  // else return blackCoffee already brewed


}



