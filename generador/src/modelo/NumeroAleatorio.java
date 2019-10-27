package modelo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumeroAleatorio {
	private int k;
	private int n;
	private BigInteger f;
	private BigInteger m;
	private BigDecimal fm;
	private int nn;
	private int num;
	
	public NumeroAleatorio(int k, int n, BigInteger f, BigInteger m, BigDecimal fm, int nn, int num) {
		super();
		this.k = k;
		this.n = n;
		this.f = f;
		this.m = m;
		this.fm = fm;
		this.nn = nn;
		this.num = num;
	}
	
	public NumeroAleatorio() {
		super();
		k = 0;
		n = 0;
		f = BigInteger.ZERO;
		m = BigInteger.ZERO;
		fm = BigDecimal.ZERO;
		nn = 0;
		num = 0;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public BigInteger getF() {
		return f;
	}

	public void setF(BigInteger f) {
		this.f = f;
	}

	public BigInteger getM() {
		return m;
	}

	public void setM(BigInteger m) {
		this.m = m;
	}

	public BigDecimal getFm() {
		return fm;
	}

	public void setFm(BigDecimal fm) {
		this.fm = fm;
	}

	public int getNn() {
		return nn;
	}

	public void setNn(int nn) {
		this.nn = nn;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
