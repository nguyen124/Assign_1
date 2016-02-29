

public class PrimeService {
	// check is number a prime number
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

	// Check to see if 2 primes are 1 digit away different; prim1 < prime2, if
	// its not then swap them;
	public static boolean isOneDigitAwayDiffer(int prime1, int prime2) {
		// HashSet<Integer> set1 = new HashSet<Integer>();
		// HashSet<Integer> set2 = new HashSet<Integer>();
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
				// noOfDigitsOfPrime1++;
				// remove last digit of both primes
				prime1 = prime1 / 10;
				prime2 = prime2 / 10;
			}
			// if prime2 is still not 0 then keep checking with prime2
			if (prime2 != 0) {
				if (countDifference == 0) {
					int lastDigitOfPrime2;
					do {
						lastDigitOfPrime2 = prime2 % 10;
						prime2 = prime2 / 10;
					} while (lastDigitOfPrime2 == 0 & prime2 != 0);
					if (prime2 != 0) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	
	
}
