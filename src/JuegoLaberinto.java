import java.util.Scanner;
import java.util.Random;

public class JuegoLaberinto {

    //Defino constantes
    private static final byte
            MIN_OPC_SALA = 1, MAX_OPC_SALA = 3,
            MIN_OPC_AVZ = 1, MAX_OPC_AVZ = 2;

    public static void main(String[] args) {

        //Defino librerías
        Scanner scn = new Scanner(System.in);
        Random rndm = new Random();

        //Defino variables
        boolean
                juegoActivo = true,
                valorCorrecto = false;
        double valorRandom;
        int energiaJugador = 100;
        byte posicionJugador = 0;
        byte opcionSala = -1;
        byte opcionAvz = -1;
        String mensBienvenida = "||| Bienvenid@ al juego del Laberinto |||\n" + "Reglas del juego:\n" + "1. Explora las 4 salas del laberinto hasta llegar a la sala del tesoro para ganar la partida.\n" + "2. Empezarás con 100 puntos de energía que podrá aumentar o disminuir conforme vayas inspeccionando cada sala.\n" + "3. Si tus puntos de energía llegan a 0 o menos, pierdes la partida.\n" + "Comencemos...\n";        String[] posicionesJugador = {"de los espejos", "de los susurros", null, "de las sombras"};
        String opcSala = "¿Qué deseas hacer?\n" + "Opción 1: Avanzar a la siguiente sala\n" + "Opción 2: Inspeccionar la sala actual\n" + "Opción 3: Salir del juego";
        String opcAvz = "Sigamos explorando entonces...\n\n¿Hacia dónde quieres ir?\n" + "Opción 1: A la izquierda\n" + "Opción 2: A la derecha";
        String eventIncrEne = "¡Genial! Has encontrado una bolsa llena de monedas, tu energía se ha incrementado en 10 puntos.";
        String eventRedEne = "¡Oh! Has caído en una trampa... Tu energía se ha reducido en 15 puntos.";
        String eventNada = "No has encontrado nada.";
        String finJuego = "Has elegido finalizar la partida, el juego ha terminado. ¡Hasta la próxima!";
        String errValOpcSala = "ERROR: valor incorrecto, introduce un valor entero entre " + MIN_OPC_SALA + " y " + MAX_OPC_SALA + " según las opciones:";
        String errFormOpcSala = "ERROR: formato incorrecto, introduce un valor entero entre " + MIN_OPC_SALA + " y " + MAX_OPC_SALA + " según las opciones:";
        String errValOpcAvz = "ERROR: valor incorrecto, introduce un valor entero entre " + MIN_OPC_AVZ + " y " + MAX_OPC_AVZ + " según las opciones:";
        String errFormOpcAvz = "ERROR: formato incorrecto, introduce un valor entero entre " + MIN_OPC_AVZ + " y " + MAX_OPC_AVZ + " según las opciones:";
        String fin0Ene = "¡Oh no! Te has quedado sin energía, has perdido... El juego ha terminado. ¡Hasta la próxima!";
        String hasGanado = "¡Enhorabuena, has llegado a la sala del tesoro! Partida ganada, el juego ha terminado. ¡Hasta la próxima!";


        //Inicio juego

        System.out.println(mensBienvenida);

        while (juegoActivo) {

            if (posicionJugador == 2) {
                System.out.println(hasGanado);
                juegoActivo = false;

            } else if (energiaJugador <= 0) {
                System.out.println(fin0Ene);
                juegoActivo = false;

            } else {
                System.out.println("Te encuentras en la sala " + posicionesJugador[posicionJugador] + " con " + energiaJugador + " puntos de energía.\n");

                //Reset valorCorrecto
                valorCorrecto = false;

                System.out.println(opcSala);

                while (!valorCorrecto) {

                    if (scn.hasNextByte()) {
                        opcionSala = scn.nextByte();
                        scn.nextLine();

                        if (opcionSala >= MIN_OPC_SALA && opcionSala <= MAX_OPC_SALA) {
                            valorCorrecto = true;
                        } else {
                            System.out.println(errValOpcSala);
                        }
                    } else {
                        System.out.println(errFormOpcSala);
                        scn.nextLine();
                    }

                    switch (opcionSala) {

                        case 1:
                            //Reset valorCorrecto
                            valorCorrecto = false;

                            System.out.println(opcAvz);

                            while (!valorCorrecto) {

                                if (scn.hasNextByte()) {
                                    opcionAvz = scn.nextByte();
                                    scn.nextLine();

                                    if (opcionAvz == MIN_OPC_AVZ || opcionAvz == MAX_OPC_AVZ) {
                                        valorCorrecto = true;
                                    } else {
                                        System.out.println(errValOpcAvz);
                                    }
                                } else {
                                    System.out.println(errFormOpcAvz);
                                    scn.nextLine();
                                }
                            }

                            switch (opcionAvz) {

                                case 1:

                                    switch (posicionJugador) {

                                        case 0:
                                            posicionJugador = 1;
                                            break;

                                        case 1, 3:
                                            posicionJugador = 0;
                                    }
                                    break;

                                case 2:
                                    switch (posicionJugador) {

                                        case 0:
                                            posicionJugador = 2;
                                            break;

                                        case 1:
                                            posicionJugador = 3;
                                            break;

                                        case 3:
                                            posicionJugador = 1;
                                    }
                            }
                            break;

                        case 2:
                            valorRandom = rndm.nextDouble();

                            if (valorRandom <= (1.0 / 3.0)) {
                                energiaJugador += 10;
                                System.out.println(eventIncrEne);
                            } else if (valorRandom <= (2.0 / 3.0)) {
                                energiaJugador -= 15;
                                System.out.println(eventRedEne);
                            } else {
                                System.out.println(eventNada);
                            }
                            break;

                        case 3:
                            System.out.println(finJuego);
                            juegoActivo = false;

                    }
                }
            }
        }
        //Cierro librería Scanner
        scn.close();
    }
}