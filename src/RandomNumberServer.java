import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class RandomNumberServer {

	public static void main(String[] args) throws IOException {

		// el servidor se pone a la escucha el puerto 9999
		ServerSocket listener = new ServerSocket(9999);
		System.out.println("Servidor Iniciado");

		// mientras no reciba nada se encuentra a la espera
		try {
			while (true) {
				Socket socket = listener.accept();// acepta una conexion entrante

				// leer numero de cliente
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// variable donde almacenar el numero del cliente
				int clientNumber = 0;

				// leer el numero del cliente
				String inputString = input.readLine();

				// comprobamos que es un numero efectivamente
				if (isNumeric(inputString)) {
					clientNumber = Integer.parseInt(inputString);

				}

				// generar numero aleatorio
				Random randomGenerator = new Random();
				int rndInt = randomGenerator.nextInt(6) + 1;

				// creamos el string de salida
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				if (clientNumber == rndInt) {
					out.println("El numero " + clientNumber + " ha resultado ganador. Ehorabuena!");

				} else if (!isNumeric(inputString)) {
					out.println(clientNumber + " ni siquiera es un numero");

				} else if (clientNumber < 1 || clientNumber > 6) {
					out.println("El numero " + clientNumber + " no es valido para el sorteo.");

				} else {
					out.println("Mala suerte, el numero premiado ha sido el " + rndInt + " Intentelo de nuevo.");
				}

				socket.close();
			}

		} finally {
			listener.close();
		}

	}

	// capturamos los errores
	public static boolean isNumeric(String str) {

		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

}
