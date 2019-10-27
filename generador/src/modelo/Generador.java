package modelo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generador {
	
	private Map<Integer, BigInteger> memo;
	public List<NumeroAleatorio> numeros;
	
	public Generador() {
		memo = new HashMap<>();
		numeros = new ArrayList<>();
	}
	
	private BigInteger fibonacci3(int n) {
	    if (n == 0 || n == 1) {
	        return BigInteger.ONE;
	    }
	    if (memo.containsKey(n)) {
	        return memo.get(n);
	    }
	    BigInteger v = fibonacci3(n - 2).add(fibonacci3(n - 1));
	    memo.put(n, v);
	    return v;
	}
	
	public void generar(int n, int numeros, int digitos) {
		List<BigInteger> lista = new ArrayList<>();
		List<String> array = new ArrayList<>();
		
		BigInteger k = fibonacci3(n);
		BigInteger m = BigInteger.TEN;
		
		String cadenaAnt = "";
		
		for (int i = 0; i < numeros; i++) {
			
			if (!lista.contains(k)) 
				lista.add(k);
			else 
				k = fibonacci3(n + i);
			
			while (m.compareTo(k) == -1) 
				m = m.multiply(BigInteger.TEN);
			
			BigDecimal d = new BigDecimal(k);
			BigDecimal div = d.divide(new BigDecimal(m));
			
			String cadena = div.toString();
			int puntoIndex = cadena.indexOf('.');		
			int nn = 0;
			
			if (cadena.length() >= digitos + 2) {
				nn = Integer.parseInt(cadena.substring(puntoIndex + 1, puntoIndex + digitos + 1));
			} else {
				nn = Integer.parseInt(cadena.substring(puntoIndex + 1));
			}
			
			cadena = cadena.substring(puntoIndex + 1, puntoIndex + 3);
			
			NumeroAleatorio numero;
			
			if (i == 0) {
				numero = new NumeroAleatorio(i + 1, n, k, m, div, Integer.parseInt(cadena), nn);
			} else {
				numero = new NumeroAleatorio(i + 1, Integer.parseInt(cadenaAnt), k, m, div, Integer.parseInt(cadena), nn);
			}
			
			this.numeros.add(numero);
			
			if (!array.contains(cadena.substring(puntoIndex + 1))) array.add(cadena);
			else break;
			
			k = fibonacci3(Integer.parseInt(cadena));		
			m = BigInteger.TEN;
		
			cadenaAnt = cadena;
		}
	}

	public List<NumeroAleatorio> getNumeros() {
		return numeros;
	}
	
}
