/*
 *  This file contain the functions related to prime numbers 
 *  Author: Hai Nguyen, Harika Kopparapu 
 */

public class PrimeService {

	/*
	 * This function check if a number is prime number. It returns True if it is
	 * prime and False otherwise
	 * 
	 * Input: Integer Output: Boolean
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
	 * Input: Integer, Integer Output: Bolean
	 */
	public static boolean isOneDigitAwayDiffer(int prime1, int prime2) {
		if (prime1 != prime2) {	// if prime1 is bigger then prime2 then swap them
			if (prime1 > prime2) {
				int temp = prime1;
				prime1 = prime2;
				prime2 = temp;
			}
			int countDifference = 0;
			int lastDigitOfPrime1 = 0;
			int lastDigitOfPrime2;
			while (prime1 != 0) {
				lastDigitOfPrime1 = prime1 % 10;
				lastDigitOfPrime2 = prime2 % 10;
				// if 2 last digits of two prime are different then increase the difference
				if (lastDigitOfPrime1 != lastDigitOfPrime2) {
					countDifference++;
					if (countDifference == 2) {
						return false;// if 2 prime has 2 different digits then return false
					}
				}
				prime1 = prime1 / 10;							
				prime2 = prime2 / 10;
			}			
			if (prime2 != 0) {				
				if (countDifference == 0) {				
					do {// this is for case prime2 has 0 in middle, for example 1003
						lastDigitOfPrime2 = prime2 % 10;
						prime2 = prime2 / 10;
					} while (lastDigitOfPrime2 == 0 & prime2 != 0);
					if (prime2 == 0) {
						return true; // this return true for example prime1=3 prime2=1003
					}
				}
			} else { 
				return true; // this will return true for example prime1=2 prime2=5
			}
		}
		// return false in any other case
		return false;
	}
}
