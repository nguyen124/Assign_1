/*
 *  This file contain the functions related to prime numbers 
 *  Author: Sadi, Prasi
 */

public class PrimeService {

	/*
	 * This function check if a number is prime number. It returns True if it is
	 * prime and False otherwise 
	 * Input: Integer 
	 * Output: Boolean
	 */
	public static boolean isPrime(int num) {
		// 1 is not prime
		if (num == 1) {
			return false;
		}
		// 2 is the first prime number
		if (num == 2) {
			return true;
		}
		// even number is not prime
		if (num % 2 == 0) {
			return false;
		}
		// check odd number only, just need to check from number itself up to
		// square root of it
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Check to see if 2 primes are 1 digit away different. If they are then
	 * return True, False if otherwise. 
	 * 
	 * Input: Integer, Integer 
	 * Output: Bolean
	 */
	public static boolean isOneDigitAwayDiffer(int prime1, int prime2) {
		// if prime1 is bigger then prime2 then swap them
		if (prime1 != prime2) {
			// swap order of prime1, prime2 make prime1 always smaller than
			// prime2;
			if (prime1 > prime2) {
				int temp = prime1;
				prime1 = prime2;
				prime2 = temp;
			}
			// the number of different digits
			int countDifference = 0;
			// number of digits of prime1
			// int noOfDigitsOfPrime1 = 0;
			while (prime1 != 0) {
				int lastDigitOfPrime1 = prime1 % 10;
				int lastDigitOfPrime2 = prime2 % 10;
				// if 2 last digits of two prime are different then increase the
				// difference
				if (lastDigitOfPrime1 != lastDigitOfPrime2) {
					countDifference++;
					// if 2 prime has 2 different digits then return false
					if (countDifference == 2) {
						return false;
					}
				}
				// remove last digit of both primes
				prime1 = prime1 / 10;
				prime2 = prime2 / 10;
			}
			// prime1 became 0 , countDifference ==0 or 1 now,
			// and if prime2 is still not 0 then keep checking with prime2
			if (prime2 != 0) {
				// if 2 primes have last digits which are identical up to now
				if (countDifference == 0) {
					int lastDigitOfPrime2;
					do {
						lastDigitOfPrime2 = prime2 % 10;
						prime2 = prime2 / 10;
						// keep checking the last number of prime2
					} while (lastDigitOfPrime2 == 0 & prime2 != 0);
					// if prime2 is not 0 and lastDigitOfPrime2 is 0,
					// this case gonna be like 7 and 2207 (Assumpt 2207 is
					// prime) and this is not 1 digit away different
					if (prime2 != 0) {
						return false;
						// else, this case will be like 3 and 2003, when we
						// remove 2, 2003 will become 3 and it is 1 digit away
						// different
					} else {
						return true;
					}
					// else mean countDiffence fis 1 now, but because prime2 is
					// still not 0 yet then it means this can't be 1 digit away
					// different
				} else {
					return false;
				}
				// if prime2 = 0 and countDifference is 1, it means 2 primes are
				// 1 digit away different. countDifference here has to be at least
				// 1 because prime1 and prime2 are different. Example 3 turn into 7 is 1
				// digit away different
			} else {
				return true;
			}
		}
		// return false in any other case
		return false;
	}

}
