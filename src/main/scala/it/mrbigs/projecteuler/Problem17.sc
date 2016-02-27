val oneToNine = 3 + 3 + 5 + 4 + 4 + 3 + 5 + 5 + 4
val oneToNineteen = oneToNine + 3 + 6 + 6 + 8 + 8 + 7 + 7 + 9 + 8 + 8
val tens = 6 + 6 + 5 + 5 + 5 + 7 + 6 + 6
val oneToNinetyNine = oneToNineteen + 8 * oneToNine + tens * 10
println(oneToNinetyNine)
val hundredAnd = 10
val hundreds = 9 * 7 + oneToNine
val oneTo999 = hundreds + 99 * oneToNine + 99 * 9 * hundredAnd + 10 * oneToNinetyNine
println(oneTo999 + 11)