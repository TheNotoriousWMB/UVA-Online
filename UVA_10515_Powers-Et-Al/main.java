import java.math.BigInteger;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String mBuff, nBuff;

		// Gets integers m and n
		mBuff = scan.next();
		nBuff = scan.next();

		while (!(mBuff.equals("0") && (nBuff.equals("0")))) {
			// res = (digit in 1's position of m)^(n mod 4 or 4 if n mod 4 = 0)
			double res = Math.pow(getM(mBuff), getN(nBuff));

			// String version of res
			String strRes = Integer.toString((int) res);

			// Print 1's position of res
			System.out.printf("%s\n", strRes.charAt(strRes.length() - 1));

			// Get new integers m and n
			mBuff = scan.next();
			nBuff = scan.next();
		}
	}

	// Returns the digit in the 1's position of integer m
	public static int getM(String s) {
		return (Integer.parseInt(String.valueOf(s.charAt(s.length() - 1))));
	}

	// Taking into account only the last two digits of n, we can still discern
	// the necessary mod value with respect to divisibility by 4
	public static int getN(String s) {
		String nStr = null;

		if (s.length() >= 2)
			nStr = s.substring(s.length() - 2);
		else
			nStr = s;

		int n = Integer.parseInt(nStr);

		int rem = n % 4;

		if (rem == 0)
			return 4;
		else
			return rem;
	}
}
