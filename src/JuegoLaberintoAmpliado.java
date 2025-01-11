import java.util.Scanner;
import java.util.Random;

public class JuegoLaberintoAmpliado {

    //Defino constantes
    private static final byte
            MIN_OPC_SALA = 1, MAX_OPC_SALA = 4,
            MIN_OPC_AVZ = 1, MAX_OPC_AVZ = 2;

    private static final int
            MIN_ENER_SALA = -20, MAX_ENER_SALA = 20;

    public static void main(String[] args) {

        //Defino librerías
        Scanner scn = new Scanner(System.in);
        Random rndm = new Random();

        //Defino variables
        boolean
                juegoActivo = true,
                valorCorrecto = false;
        byte posicionJugador = 0;
        byte opcionSala = -1;
        byte opcionAvz = -1;
        double valorRandomEventSala;
        int energiaJugador = 100;
        int[] energiaSalas = new int[4];
        String mensBienvenida = "||| Bienvenid@ al juego del Laberinto |||\n" + "Reglas del juego:\n" + "1. Explora las 4 salas del laberinto hasta llegar a la sala del tesoro con al menos 30 puntos de energía para ganar la partida.\n" + "2. Empezarás con 100 puntos de energía.\n" + "3. Cada sala tendrá una energía de entre -20 y 20 puntos asignada al principio de cada partida, que se sumará o restará de tu energía. Podrás consultar esta información si lo deseas.\n" + "4. También podrás ganar 10 puntos o perder 15 puntos de energía al inspeccionar una sala.\n" + "5. Si te quedas sin puntos de energía, perderás la partida.\n" + "Comencemos...";
        String[] posicionesJugador = {"de los espejos", "de los susurros", "del tesoro", "de las sombras"};
        String opcSala = "Opción 1: Avanzar a la siguiente sala\n" + "Opción 2: Inspeccionar la sala actual\n" + "Opción 3: Consultar la energía de las salas\n" + "Opción 4: Salir del juego\n\n" + "¿Qué deseas hacer? (Introduce el número de la opción):" ;
        String opcAvz = "Sigamos explorando entonces...\n\n¿Hacia dónde quieres ir?\n" + "Opción 1: A la izquierda\n" + "Opción 2: A la derecha\n" + "(Introduce el número de la opción):";
        String eventIncrEne = "¡Genial! Has encontrado una poción que te da vida, tu energía se ha incrementado en 10 puntos.";
        String eventRedEne = "¡Oh no! Has caído en un agujero lleno de púas... Tu energía se ha reducido en 15 puntos.";
        String eventNada = "No has encontrado nada.";
        String finJuego = "Has elegido finalizar la partida, el juego ha terminado. ¡Hasta la próxima!";
        String errVal = "ERROR: valor incorrecto, introduce un valor entero entre ";
        String errForm = "ERROR: formato incorrecto, introduce un valor entero entre ";
        String errOpcSala = MIN_OPC_SALA + " y " + MAX_OPC_SALA + " según las opciones:";
        String errOpcAvz = MIN_OPC_AVZ + " y " + MAX_OPC_AVZ + " según las opciones:";
        String fin0Ene = "¡Oh no! Te has quedado sin energía, has perdido... El juego ha terminado. ¡Hasta la próxima!";
        String hasGanado = "¡Enhorabuena, has llegado a la sala del tesoro con al menos 30 puntos de energía! Partida ganada, el juego ha terminado. ¡Hasta la próxima!";


        //Genero valores fijos de manera aleatoria para el impacto de las salas sobre la energía del jugador

        for (int i = 0; i < energiaSalas.length; i++) {
            energiaSalas[i] = rndm.nextInt((MAX_ENER_SALA - MIN_ENER_SALA) + 1) + MIN_ENER_SALA;
        }

        //Inicio el juego

        System.out.println(mensBienvenida);

        while (juegoActivo) {

            if (energiaJugador >= 30 && posicionJugador == 2) {
                System.out.println(hasGanado);
                juegoActivo = false;

            } else if (energiaJugador <= 0) {
                System.out.println(fin0Ene);
                juegoActivo = false;

            } else {
                System.out.println("\nTe encuentras en la sala " + posicionesJugador[posicionJugador] + " con " + energiaJugador + " puntos de energía.\n");

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
                            System.out.println(errVal + errOpcSala);
                        }
                    } else {
                        System.out.println(errForm + errOpcSala);
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
                                        System.out.println(errVal + errOpcAvz);
                                    }
                                } else {
                                    System.out.println(errForm + errOpcAvz);
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
                            energiaJugador += energiaSalas[posicionJugador];
                            break;

                        case 2:
                            valorRandomEventSala = rndm.nextDouble();

                            if (valorRandomEventSala <= (1.0 / 3.0)) {
                                energiaJugador += 10;
                                System.out.println(eventIncrEne);
                            } else if (valorRandomEventSala <= (2.0 / 3.0)) {
                                energiaJugador -= 15;
                                System.out.println(eventRedEne);
                            } else {
                                System.out.println(eventNada);
                            }
                            break;

                        case 3:
                            for (int i = 0; i < energiaSalas.length; i++) {
                                System.out.println("El impacto de la sala " + posicionesJugador[i] + " sobre tu energía será de " + energiaSalas[i] + " puntos.");
                            }
                            break;

                        case 4:
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