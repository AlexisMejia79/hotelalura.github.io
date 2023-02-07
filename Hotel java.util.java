import java.sql.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelApplication {
  private static final int NUM_ROOMS = 10;
  private static Room[] rooms = new Room[NUM_ROOMS];
  private static Connection connection;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Bienvenido a la aplicación de reservas de habitaciones del Hotel");
    initializeRooms();
    connectToDatabase();

    while (true) {
      System.out.println("Selecciona una opción:");
      System.out.println("1. Ver habitaciones disponibles");
      System.out.println("2. Reservar habitación");
      System.out.println("3. Cancelar reserva");
      System.out.println("4. Ver reservas");
      System.out.println("5. Salir");

      int option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1:
          displayAvailableRooms();
          break;
        case 2:
          reserveRoom(scanner);
          break;
        case 3:
          cancelReservation(scanner);
          break;
        case 4:
          displayReservations();
          break;
        case 5:
          System.out.println("Gracias por usar la aplicación de reservas de habitaciones del Hotel");
          return;
        default:
          System.out.println("Opción no válida, inténtalo de nuevo");
      }
    }
  }

  private static void initializeRooms() {
    for (int i = 0; i < NUM_ROOMS; i++) {
      rooms[i] = new Room(i + 1);
    }
  }

  private static void connectToDatabase() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "username", "password");
      System.out.println("Conexión a la base de datos exitosa");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void displayAvailableRooms() {
    System.out.println("Habitaciones disponibles:");
    for (int i = 0; i < NUM_ROOMS; i++) {
      if (rooms[i].isAvailable()) {
        System.out.println((i + 1) + " - Habitación " + (i + 1));
      }
    }
  }

  private static void reserveRoom(Scanner scanner) {
    displayAvailableRooms();
    System.out.print("Ingresa el número de habitación que deseas reservar: ");
    int

