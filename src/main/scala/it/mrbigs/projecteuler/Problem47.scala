package it.mrbigs.projecteuler

/**
  * Created by simone on 2/13/16.
  */
object Problem47 extends App {
  val target = 4

  println(Stream.range(1L, Long.MaxValue).find(n => {
//    if (n % 1000 == 0)
//      println(n)
    Stream.range(n, n + target).forall(_.toPrimeFactors.size == target)
  }).get)
}
