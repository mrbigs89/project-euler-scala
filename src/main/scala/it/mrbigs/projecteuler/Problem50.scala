package it.mrbigs.projecteuler

/**
  * Created by simone on 2/14/16.
  */
object Problem50 extends App {

  val startTime = System.nanoTime()

  val Max = 1000000

  val startInit = System.nanoTime()
  initPrimes(Max)
  val endInit = System.nanoTime()
  println(s"Initialized primes up to $Max in ${(endInit - startInit) / 1000000}ms")

  val primes = primesBetween(2, Max)
  val cumPrimes = primes.scanLeft(0L)(_ + _)
  var currentBestPrime = 0L
  var currentBestLength = 0

  cumPrimes.indices.toStream.foreach(i => {
    ((i - currentBestLength - 1) to 0 by -1).toStream.takeWhile(j => cumPrimes(i) - cumPrimes(j) < Max)
      .find(j => (cumPrimes(i) - cumPrimes(j)).isPrime) match {
      case Some(j) =>
        currentBestLength = i - j
        currentBestPrime = cumPrimes(i) - cumPrimes(j)
      case _ =>
    }
  })

  val endTime = System.nanoTime()
  println("Execution time: " + ((endTime - startTime) / 1000000) + "ms")
  println(s"$currentBestPrime -> $currentBestLength")

}
