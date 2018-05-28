
import java.util.*;

public class codigoHipodromo {

    public static void main(String args[]) throws InterruptedException {         //Math.random()*5+1;
        int c1, c2, c3, c4, p, retirar = 0, dineritos, amaño, ret, opcion, z, ganador = 0, apuesta, fin = 1, movCab1, movCab2, movCab3, movCab4, cabAle, acuMov1, acuMov2, acuMov3, acuMov4;
        double saldo = 50, cantidad = 0;
        String password, caballo1 = "\u001b[31m\uD83D\uDC0E\u001b[0m", caballo2 = "\u001b[32m\uD83D\uDC0E\u001b[0m", caballo3 = "\u001b[33m\uD83D\uDC0E\u001b[0m", caballo4 = "\u001b[34m\uD83D\uDC0E\u001b[0m";
        Scanner sc = new Scanner(System.in);
        //negrita e inverso								//cierra
        System.out.println("\u001b[1m\u001b[7m¡Bienvenido al Hipodromo Vivas!\u001b[0m");
        System.out.println("");
        System.out.println("Apueste con nosotros");
        System.out.println("Disculpe, el sistema funciona con lentitud"
                + "\nCargando...");
        Thread.sleep(1500);
        do {
            z = 0;
            fin = 1;
            acuMov1 = 0;
            acuMov2 = 0;
            acuMov3 = 0;
            acuMov4 = 0;
            System.out.println("\u001b[1m");
            System.out.println("*----------------*");
            System.out.println("|MENU DE APUESTAS|");
            System.out.println("*----------------*");
            System.out.println("\u001b[0m");
            System.out.println("1.Apostar a un caballo\n2.Ver resultado de la siguiente carrera"
                    + "\n3.Ver saldo\n4.Irse del hipódromo\n\n>Por jugar por primera vez le regalamos 50€");
            System.out.println("¿Qué desea hacer?");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1://Apostar
                    do {
                        System.out.println("\u001b[2J\u001b[H");
                        System.out.println("Elija al caballo por el que quiere apostar:\n1.Spirit(3.50)"
                                + "\n2.GeneTokyoDrift(4.20)\n3.GañanAltruista(3.80)\n4.BlackStalin(5.40)\n5.No apostar por ninguno"
                                + "\n\n\n#Amañar carrera[En caso de detectarlo, será expulasdo del hipodromo]");

                        if (saldo <= 0) {
                            apuesta = 5;
                            System.out.println("\nSu saldo es 0€\nNo puede apostar");
                            Thread.sleep(750);
                        } else {
                            apuesta = sc.nextInt();
                            if (apuesta > 0 && apuesta < 5) {
                                do {
                                    dineritos = 1;
                                    System.out.print("Cantidad a apostar: ");
                                    cantidad = sc.nextDouble();
                                    if (cantidad > 0 && cantidad <= saldo) {
                                        saldo -= cantidad;
                                    } else {
                                        System.out.print("Cantidad no valida");
                                        dineritos = 0;
                                    }
                                } while (dineritos == 0);

                            }
                        }
                        if (apuesta != 1 && apuesta != 2 && apuesta != 3 && apuesta != 4 && apuesta != 5) {
                            System.out.println("Opcion no valida");
                        }
                    } while (apuesta != 1 && apuesta != 2 && apuesta != 3 && apuesta != 4 && apuesta != 5);
                    System.out.println("Las apuestas han sido cerradas");
                    System.out.println("");
                    System.out.println("La carrera comenzará en unos segundos");
                    Thread.sleep(2000);
                    while (fin != 0) {

                        cabAle = (int) (Math.random() * 4 + 1);
                        switch (cabAle) {
                            case 1:
                                movCab1 = (int) (Math.random() * 3 + 1);
                                acuMov1 += movCab1;
                                if (acuMov1 >= 40) {
                                    acuMov1 = 40;
                                    fin = 0;
                                    ganador = 1;
                                }
                                //~ System.out.printf("\u001b[2J");
                                break;
                            case 2:
                                movCab2 = (int) (Math.random() * 3 + 1);
                                acuMov2 += movCab2;
                                if (acuMov2 >= 40) {
                                    acuMov2 = 40;
                                    fin = 0;
                                    ganador = 2;
                                }
                                //~ System.out.printf("\u001b[2J");
                                break;
                            case 3:
                                movCab3 = (int) (Math.random() * 3 + 1);
                                acuMov3 += movCab3;
                                if (acuMov3 >= 40) {
                                    acuMov3 = 40;
                                    fin = 0;
                                    ganador = 3;
                                }
                                //~ System.out.printf("\u001b[2J");
                                break;
                            case 4:
                                movCab4 = (int) (Math.random() * 3 + 1);
                                acuMov4 += movCab4;
                                if (acuMov4 >= 40) {
                                    acuMov4 = 40;
                                    fin = 0;
                                    ganador = 4;
                                }
                                //~ System.out.printf("\u001b[2J");
                                break;
                        }
                        System.out.printf("\u001b[2J");
                        System.out.println("************************************************************");
                        System.out.println("************************************************************");
                        System.out.println("**                          CARRERA                       **");
                        System.out.println("** ______________________________________________________ **");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov1) {
                                System.out.print(caballo1);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov2) {
                                System.out.print(caballo2);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov3) {
                                System.out.print(caballo3);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov4) {
                                System.out.print(caballo4);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.println("**|______________________________________________________|**");
                        System.out.println("**                                                        **");
                        System.out.println("************************************************************");
                        System.out.println("************************************************************");
                        Thread.sleep(100);

                    }
                    Thread.sleep(4000);
                    System.out.println("El ganador de la carrera ha sido el...");
                    System.out.printf("\u001b[7mCABALLO %d\u001b[0m\n", ganador);
                    if (apuesta != 5) {
                        if (ganador == apuesta) {
                            System.out.println("\n\u001b[1mFelicidades!!\u001b[0m Has ganado tu apuesta.");
                            switch (apuesta) {
                                case 1:
                                    cantidad *= 3.50;
                                    saldo += cantidad;
                                    break;
                                case 2:
                                    cantidad *= 4.20;
                                    saldo += cantidad;
                                    break;
                                case 3:
                                    cantidad *= 2.80;
                                    saldo += cantidad;
                                    break;
                                case 4:
                                    cantidad *= 6.40;
                                    saldo += cantidad;
                                    break;
                            }
                        } else {
                            System.out.println("\nLo siento, su caballo no ha ganado");
                        }
                    }
                    Thread.sleep(5000);
                    System.out.println("\u001b[2J\u001b[H");
                    break;
                case 2://Simular una carrera
                    System.out.println("La carrera va a comenzar.\nLos caballos están preparándose");
                    System.out.println("\nLas apuestas han sido cerradas");
                    while (fin != 0) {
                        cabAle = (int) (Math.random() * 4 + 1);
                        switch (cabAle) {
                            case 1:
                                movCab1 = (int) (Math.random() * 3 + 1);
                                acuMov1 += movCab1;
                                if (acuMov1 >= 40) {
                                    acuMov1 = 40;
                                    fin = 0;
                                    ganador = 1;
                                }
                                System.out.printf("\u001b[2J");
                                break;
                            case 2:
                                movCab2 = (int) (Math.random() * 3 + 1);
                                acuMov2 += movCab2;
                                if (acuMov2 >= 40) {
                                    acuMov2 = 40;
                                    fin = 0;
                                    ganador = 2;
                                }
                                System.out.printf("\u001b[2J");
                                break;
                            case 3:
                                movCab3 = (int) (Math.random() * 3 + 1);
                                acuMov3 += movCab3;
                                if (acuMov3 >= 40) {
                                    acuMov3 = 40;
                                    fin = 0;
                                    ganador = 3;
                                }
                                System.out.printf("\u001b[2J");
                                break;
                            case 4:
                                movCab4 = (int) (Math.random() * 3 + 1);
                                acuMov4 += movCab4;
                                if (acuMov4 >= 40) {
                                    acuMov4 = 40;
                                    fin = 0;
                                    ganador = 4;
                                }
                                System.out.printf("\u001b[2J");
                                break;
                        }

                        //inicio
                        System.out.println("\n\n");
                        System.out.printf("\u001b[2J");
                        System.out.println("************************************************************");
                        System.out.println("************************************************************");
                        System.out.println("**                          CARRERA                       **");
                        System.out.println("** ______________________________________________________ **");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov1) {
                                System.out.print(caballo1);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov2) {
                                System.out.print(caballo2);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov3) {
                                System.out.print(caballo3);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.print("**|       ");
                        for (int i = 0; i <= 40; i++) {
                            if (i == acuMov4) {
                                System.out.print(caballo4);
                            } else {
                                System.out.print("\u001b[34m_\u001b[0m");
                            }
                        }
                        System.out.println("      |**");
                        System.out.println("**|                                                      |**");
                        System.out.println("**|______________________________________________________|**");
                        System.out.println("**                                                        **");
                        System.out.println("************************************************************");
                        System.out.println("************************************************************");
                        Thread.sleep(100);
                        //final
                    }
                    System.out.printf("El ganador de esta carrera ha sido: \u001b[7mCABALLO %d\u001b[0m\n", ganador);
                    Thread.sleep(5000);
                    System.out.println("\u001b[2J\u001b[H");
                    break;
                case 3://Saldo
                    do {
                        p = 0;
                        retirar = 0;
                        System.out.println("Para acceder a su saldo, introduzca contraseña: [Contraseña por defecto: 0000]");
                        password = sc.nextLine();
                        if (!password.equals("0000")) {
                            System.out.println("Contraseña incorrecta");
                            p = 1;
                        } else {
                            System.out.printf("Su saldo es de: %.2f €\n", saldo);
                            Thread.sleep(200);
                            do {
                                ret = 0;
                                System.out.println("¿Desea: 1.Seguir apostando o 2.Retirar su dinero e irse?");
                                retirar = sc.nextInt();
                                switch (retirar) {
                                    case 1:
                                        System.out.println("Estamos encantados de que sigas jugando");
                                        break;
                                    case 2:
                                        System.out.println("Has retirado tu dinero[Para volver a jugar debes introducir dinero]");
                                        saldo = 0;
                                        System.out.println(""
                                                + "\n    ,  ,        \u001b[47m\u001b[36mADIOS!\u001b[0m"
                                                + "\n   _)\\/)"
                                                + "\n  / ¬ ¬)>        o"
                                                + "\n //    /\\>   o "
                                                + "\n/(,__//  \\>         _____"
                                                + "\n\\__(' \\   \\>       /___  \\"
                                                + "\n      /    \\>___,----. )  )"
                                                + "\n     (                 \\  ("
                                                + "\n     /                 |  )"
                                                + "\n     |  '              | /"
                                                + "\n     | \\__|  /___,/    /("
                                                + "\n      \\ | | / (  (   _/"
                                                + "\n      ('| ( )  \\_ \\_ >"
                                                + "\n       \\\\ |/      | )/"
                                                + "\n        \\)\\\\      )//"
                                                + "\n        /,/,     /(<,"
                                                + "\n     ,,/_/_),,,,/ )_),,, ,,_"
                                        );
                                        System.out.println("Esperamos que vuelva pronto.");
                                        z = 1;
                                        break;
                                    default:
                                        System.out.println("Opcon invalida");
                                        ret = 1;
                                }
                            } while (ret == 1);
                        }
                    } while (p == 1);
                    break;
                case 4://Salir
                    System.out.println(""
                            + "\n    ,  ,        \u001b[47m\u001b[36mADIOS!\u001b[0m"
                            + "\n   _)\\/)"
                            + "\n  / ¬ ¬)>        o"
                            + "\n //    /\\>   o "
                            + "\n/(,__//  \\>         _____"
                            + "\n\\__(' \\   \\>       /___  \\"
                            + "\n      /    \\>___,----. )  )"
                            + "\n     (                 \\  ("
                            + "\n     /                 |  )"
                            + "\n     |  '              | /"
                            + "\n     | \\__|  /___,/    /("
                            + "\n      \\ | | / (  (   _/"
                            + "\n      ('| ( )  \\_ \\_ >"
                            + "\n       \\\\ |/      | )/"
                            + "\n        \\)\\\\      )//"
                            + "\n        /,/,     /(<,"
                            + "\n     ,,/_/_),,,,/ )_),,, ,,_"
                    );
                    System.out.print("Esperamos que vuelva pronto.");
                    z = 1;
                    break;
                default:
                    System.out.println("Opcion no válida");
                    z = 0;
                    break;
            }
        } while (z == 0);
    }
}
