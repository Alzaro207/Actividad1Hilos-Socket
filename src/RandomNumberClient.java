import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//Cliente para el servidor de numero aleatorios. Inicialmente requiere la direccion IP del servidor,
//el cual estará escuchando en el puerto 9999. Despues solicitará un numero entre el 1 y 6, que
//será enviado al servidor como un numero de participación, para posteriormente recibir la respuesta
//correspondiente

public class RandomNumberClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		// pedir la IP
		String serverAddress = System.console().readLine("Introduce la dirección IP del servidor de numero aleatorios\n");

		while (true) {
			// solicitar numero del 1 al 6
			String number = System.console().readLine("Introduce un numero del 1 al 6 para participar en el sorteo\n");

			// creamos el socket
			Socket socket = new Socket(serverAddress, 9999);

			// enviar numero al servidor
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(number);

			// recibimos la respuesta del servidor
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String answer = input.readLine();

			// imprimir mensaje
			System.out.println(answer);

			// cerrar el socket
			socket.close();

		}

	}
}
