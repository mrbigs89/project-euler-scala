package it.mrbigs.projecteuler

/**
  * Created by simone on 2/13/16.
  */
object Problem48 extends App {
  println(Range.BigInt.inclusive(BigInt(1), BigInt(1000), BigInt(1))
    .map(n => n.pow(n.toInt))
    .sum
    % 10000000000L
  )
}
