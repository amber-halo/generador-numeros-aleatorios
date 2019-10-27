package modelo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Principal {
	
	/* 
	 * 1. Ingresar un numero n que sera la posicion F en un vector conteniendo la serie de Fibonacci, siendo n < 100
	 * 2. Ingresar la cantidad de numeros k aleatorios a generar.
	 * 3. Elegir una cantidad de numeros p a ser considerados.
	 * 4. Dividir el numero dado por la serie de Fibonacci entre m = 10. En caso de ser mayor a m, este se multiplicara por 10 hasta ser mayor
	 *    al numero dado.
	 * 5. A partir del punto decimal del numero generado, se tomaran los primeros numeros p que seran el numero aleatorio generado.
	 * 6. A partir del punto decimal del numero generado, se tomaran los dos primeros digitos que representaran la nueva posicion en el vector 
	 *    conteniendo la serie de Fibonacci
	 * 7. Repetir el proceso hasta k, revisando en cada iteracion que el numero en la serie de Fibonacci no se encuentre previamente generado,
	 *    en caso de repeticion se tomara la posicion n y se le sumara k(i)
	 *    
	 *    
	 * k	n	F[n]	m	F[n]/m		n-nuevo		Num. Al. Gen.
	 * 
	 *  */
	
	private static Map<Integer, BigInteger> memo = new HashMap<>();

	public static void main(String[] args) {	
		System.out.println(fibonacci3(16));
		
//		generador(29, 100, 4);
		
//		int n = leerInt(JOptionPane.showInputDialog(null, "Ingrese un numero mayor a 17"));
//		int tamanio = leerInt(JOptionPane.showInputDialog(null, "Ingrese el tamanio del generador."));
//		
//		generador(n, tamanio);
	}
	
	public static void generador(int n, int numeros, int digitos) {
		List<BigInteger> lista = new ArrayList<>();
		List<String> array = new ArrayList<>();
		
		BigInteger k = fibonacci3(n);
		BigInteger m = BigInteger.TEN;
		
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
			
			if (cadena.length() >= digitos + 2) {
				System.out.println("Aleatorio " + (i+1) + " = " + cadena.substring(puntoIndex + 1, puntoIndex + digitos + 1));
			} else {
				System.out.println("Aleatorio " + (i+1) + " = " + cadena.substring(puntoIndex + 1));
			}
			
			cadena = cadena.substring(puntoIndex + 1, puntoIndex + 3);
			if (!array.contains(cadena.substring(puntoIndex + 1))) array.add(cadena);
			else break;
			
			k = fibonacci3(Integer.parseInt(cadena));
			
			m = BigInteger.TEN;
		}
	}

	public static BigInteger fibonacci3(int n) {
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
	
	public static int leerInt(String cadena) {
		do {
			try {
				int numero = Integer.parseInt(cadena);
				return numero;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ingrese un numero entero.");
//				return -1;
			}
		} while (true);
	}

}
