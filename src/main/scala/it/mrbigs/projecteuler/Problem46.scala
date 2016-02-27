package it.mrbigs.projecteuler

/**
  * Created by simone on 2/13/16.
  */
object Problem46 extends App {
  println(Stream.from(4).map(_ * 2 + 1).filter(!_.isPrime).find(n => {
    !(1 to Math.sqrt(n/2).toInt).map(i => i * i).exists(sq => (n - 2 * sq).isPrime)
  }).get)
}
