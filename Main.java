import java.math.BigDecimal;
import java.math.MathContext;

public class Main {
  static MathContext p
    = new MathContext(99999);
  static BigDecimal c1
    = new BigDecimal("545140134");
  static BigDecimal c2
    = new BigDecimal("13591409");
  static BigDecimal c3
    = new BigDecimal(
      "-262537412640768000");
  static BigDecimal c4
    = new BigDecimal("426880");
  static BigDecimal c5
    = c4.multiply(
      new BigDecimal(Math.sqrt(10005)));
  static BigDecimal one
    = BigDecimal.ONE;
  static BigDecimal z
    = BigDecimal.ZERO;

  static BigDecimal fac(int v) {
    BigDecimal r = one;

    BigDecimal c = new BigDecimal(v);

    while (c.compareTo(one) > 0) {
      r = r.multiply(c);
      c = c.subtract(one);
    }

    return r;
  }

  static BigDecimal calcTerm(int q) {
    BigDecimal nt1 = fac(6 * q);

    BigDecimal nt2 = new BigDecimal(q);
    nt2 = nt2.multiply(c1);
    nt2 = nt2.add(c2);

    BigDecimal dt1 = fac(3 * q);
    BigDecimal dt2 = fac(q);
    dt2 = dt2.pow(3);

    BigDecimal dt3 = c3;
    dt3 = dt3.pow(q);

    BigDecimal n = nt1.multiply(nt2);

    BigDecimal d = dt1.multiply(dt2);
    d = d.multiply(dt3);

    return n.divide(d, p);
  }

  static BigDecimal calcPi() {
    BigDecimal ds = z;

    for (int k = 0; k < 1; k++) {
      ds = ds.add(calcTerm(k));
    }

    return c5.divide(ds, p);
  }

  public static void main(String[] a) {
    long start = System.nanoTime();
    System.out.println(calcPi());
    long end = System.nanoTime();
    long exec = end - start;
    double s = (double)exec/1000000000;
    System.out.println(""+s+"");
  }
}