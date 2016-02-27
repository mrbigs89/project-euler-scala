package it.mrbigs.projecteuler

/**
  * Created by simone on 2/13/16.
  */
object Problem43 extends App{
  val test = 1406357289
  val pandigitals = (0 to 9).permutations.toStream
  val firstPrimes = List(2, 3, 5, 7, 11, 13, 17).reverse

  def hasProperty(n: List[Int]): Boolean = (0 to 6).toStream.zipWithIndex
    .forall { case (offset, index) => {
      val number = n.slice(offset, offset + 3).toLong
      number % firstPrimes(index) == 0
    }}

//  println(hasProperty(test.toList))
  println(pandigitals.map(_.toList).filter(hasProperty).map(_.toLong).sum)


}
