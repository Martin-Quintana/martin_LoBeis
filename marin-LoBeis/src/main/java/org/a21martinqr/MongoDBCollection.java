package org.a21martinqr;

import com.mongodb.Block;
import com.mongodb.DocumentToDBRefTransformer;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiConsumer;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.*;

public class MongoDBCollection {

    public MongoDBCollection() {
    }

    //Metodos de conexion a la base de datos
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("LoBeis");

    //Creacion de las colecciones

    MongoCollection<Document> jugadoresCollection = database.getCollection("Jugadores");

    MongoCollection<Document> equiposCollection = database.getCollection("Equipos");

    MongoCollection<Document> estadiosCollection = database.getCollection("Estadios");


    //Insertar datos en las colecciones

    public void insertarDatos() {
        //Primero insertamos los datos de los jugadores
        ArrayList<Document> jugadores = new ArrayList<Document>();
        //Añademe jugadores del real madrid
        jugadores.add(new Document("Nombre", "Luka Modric").append("Posicion", "Centrocampista").append("Equipo", "Real Madrid").append("Dorsal", 10).append("Edad", 33));
        jugadores.add(new Document("Nombre", "Sergio Ramos").append("Posicion", "Defensa central").append("Equipo", "Real Madrid").append("Dorsal", 4).append("Edad", 34));
        jugadores.add(new Document("Nombre", "Karim Benzema").append("Posicion", "Delantero").append("Equipo", "Real Madrid").append("Dorsal", 9).append("Edad", 32));
        jugadores.add(new Document("Nombre", "Toni Kroos").append("Posicion", "Centrocampista").append("Equipo", "Real Madrid").append("Dorsal", 8).append("Edad", 30));
        jugadores.add(new Document("Nombre", "Luka Jovic").append("Posicion", "Delantero").append("Equipo", "Real Madrid").append("Dorsal", 18).append("Edad", 23));
        jugadores.add(new Document("Nombre", "Cristiano Ronaldo").append("Posicion", "Delanero").append("Equipo", "Real Madrid").append("Dorsal", 24).append("Edad", 38));
        //Añademe jugadores del Barcelona
        jugadores.add(new Document("Nombre", "Lionel Messi").append("Posicion", "Delantero").append("Equipo", "Barcelona").append("Dorsal", 10).append("Edad", 33));
        jugadores.add(new Document("Nombre", "Gerard Pique").append("Posicion", "Defensa central").append("Equipo", "Barcelona").append("Dorsal", 3).append("Edad", 33));
        jugadores.add(new Document("Nombre", "Sergio Busquets").append("Posicion", "Centrocampista").append("Equipo", "Barcelona").append("Dorsal", 5).append("Edad", 32));
        jugadores.add(new Document("Nombre", "Antoine Griezmann").append("Posicion", "Delantero").append("Equipo", "Barcelona").append("Dorsal", 7).append("Edad", 29));


        jugadoresCollection.insertMany(jugadores);

        //Insertamos los datos de los equipos
        ArrayList<Document> equipos = new ArrayList<Document>();
        //Añademe equipos de la liga española
        equipos.add(new Document("Nombre", "Atletico de Madrid").append("Ciudad", "Madrid").append("Pais", "España").append("Estadio", "Wanda Metropolitano"));
        equipos.add(new Document("Nombre", "Real Madrid").append("Ciudad", "Madrid").append("Pais", "España").append("Estadio", "Santiago Bernabeu"));
        equipos.add(new Document("Nombre", "Barcelona").append("Ciudad", "Barcelona").append("Pais", "España").append("Estadio", "Camp Nou"));
        equipos.add(new Document("Nombre", "Sevilla").append("Ciudad", "Sevilla").append("Pais", "España").append("Estadio", "Ramón Sánchez Pizjuán"));
        equipos.add(new Document("Nombre", "Valencia").append("Ciudad", "Valencia").append("Pais", "España").append("Estadio", "Mestalla"));
        equipos.add(new Document("Nombre", "Villarreal").append("Ciudad", "Villarreal").append("Pais", "España").append("Estadio", "Estadio de la Cerámica"));


        equiposCollection.insertMany(equipos);

        //Insertamos los datos de los estadios
        ArrayList<Document> estadios = new ArrayList<Document>();
        //Añademe estadios de la liga española
        estadios.add(new Document("Nombre", "Wanda Metropolitano").append("Ciudad", "Madrid").append("Pais", "España").append("Capacidad", 68000));
        estadios.add(new Document("Nombre", "Santiago Bernabeu").append("Ciudad", "Madrid").append("Pais", "España").append("Capacidad", 81044));
        estadios.add(new Document("Nombre", "Camp Nou").append("Ciudad", "Barcelona").append("Pais", "España").append("Capacidad", 99354));
        estadios.add(new Document("Nombre", "Ramón Sánchez Pizjuán").append("Ciudad", "Sevilla").append("Pais", "España").append("Capacidad", 45000));
        estadios.add(new Document("Nombre", "Mestalla").append("Ciudad", "Valencia").append("Pais", "España").append("Capacidad", 55000));
        estadios.add(new Document("Nombre", "Estadio de la Cerámica").append("Ciudad", "Villarreal").append("Pais", "España").append("Capacidad", 30000));

        estadiosCollection.insertMany(estadios);

    }

    public void borradoDatos() {
        //Borramos los datos de las colecciones
        jugadoresCollection.drop();
        equiposCollection.drop();
        estadiosCollection.drop();
    }


    public void menuConsultas() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Consulta 1");
            System.out.println("2. Consulta 2");
            System.out.println("3. Consulta 3");
            System.out.println("4. Consulta 4");
            System.out.println("5. Consulta 5");
            System.out.println("6. Consulta 6");
            System.out.println("7. Consulta 7");
            System.out.println("8. Consulta 8");
            System.out.println("0. Salir");
            System.out.println("Elige una opción");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consulta1();
                    break;
                case 2:
                    consulta2();
                    break;
                case 3:
                    consulta3();
                    break;
                case 4:
                    consulta4();
                    break;
                case 5:
                    consulta5();
                    break;
                case 6:
                    consulta6();
                    break;
                case 7:
                    consulta7();
                    break;
                case 8:
                    consulta8();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 0 y 10");
            }

        }
    }

    private void consulta1() {
        try {
            System.out.println("Consulta 1");
            System.out.println("Jugadores que juegan en el Real Madrid");
            //Consulta 1
            //Jugadores que juegan en el Real Madrid
            database.getCollection("Jugadores").find(eq("Equipo", "Real Madrid")).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
            //Generar archivo .JSON con todos los jugadores que juegan en el real y guardarlo en la carpeta resources/json
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadrid.json")));
            database.getCollection("Jugadores").find(eq("Equipo", "Real Madrid")).forEach((Block<? super Document>) document -> {
                try {
                    bw.write(document.toJson());
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void consulta2() {
        try {
            System.out.println("Consulta 2");
            System.out.println("Jugadores que juegan en el Real Madrid y son delanteros");
            //Consulta 2
            //Jugadores que juegan en el Real Madrid y son delanteros
            database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), eq("Posicion", "Delantero"))).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
            //Generar archivo .JSON con todos los jugadores que juegan en el real y son delanteros y guardarlo en la carpeta resources/json
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridDelanteros.json")));
            database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), eq("Posicion", "Delantero"))).forEach((Block<? super Document>) document -> {
                try {
                    bw.write(document.toJson());
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

        private void consulta3 () {
            try {
                System.out.println("Consulta 3");
                System.out.println("Jugadores que juegan en el Real Madrid o en el Barcelona");
                //Consulta 3
                //Jugadores que juegan en el Real Madrid o en el Barcelona
                database.getCollection("Jugadores").find(or(eq("Equipo", "Real Madrid"), eq("Equipo", "Barcelona"))).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON con todos los jugadores que juegan en el real o en el barcelona y guardarlo en la carpeta resources/json
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridBarcelona.json")));
                database.getCollection("Jugadores").find(or(eq("Equipo", "Real Madrid"), eq("Equipo", "Barcelona"))).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        private void consulta4 () {
            try {
                System.out.println("Consulta 4");
                System.out.println("Jugadores que juegan en el Real Madrid y no son delanteros");
                //Consulta 4
                //Jugadores que juegan en el Real Madrid y no son delanteros
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), ne("Posicion", "Delantero"))).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON con todos los jugadores que juegan en el real y no son delanteros y guardarlo en la carpeta resources/json
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridNoDelanteros.json")));
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), ne("Posicion", "Delantero"))).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void consulta5 () {
            try {
                System.out.println("Consulta 5");
                System.out.println("Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas");
                //Consulta 5
                //Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")))).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON con todos los jugadores que juegan en el real y son delanteros o son centrocampistas y guardarlo en la carpeta resources/json
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridDelanterosCentrocampistas.json")));
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")))).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void consulta6 () {
            try {
                System.out.println("Consulta 6");
                System.out.println("Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas y su edad es mayor de 30 años");
                //Consulta 6
                //Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas y su edad es mayor de 30 años
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")), gt("Edad", 30))).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON con todos los jugadores que juegan en el real y son delanteros o son centrocampistas y su edad es mayor de 30 años y guardarlo en la carpeta resources/json
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridDelanterosCentrocampistasMayores30.json")));
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")), gt("Edad", 30))).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void consulta7 () {
            try {
                System.out.println("Consulta 7");
                System.out.println("Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas y su edad es mayor de 30 años ordenados por edad");
                //Consulta 7
                //Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas y su edad es mayor de 30 años ordenados por edad
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")), gt("Edad", 30))).sort(new Document("Edad", 1)).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON con todos los jugadores que juegan en el real y son delanteros o son centrocampistas y su edad es mayor de 30 años ordenados por edad y guardarlo en la carpeta resources/json
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridDelanterosCentrocampistasMayores30OrdenadosEdad.json")));
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")), gt("Edad", 30))).sort(new Document("Edad", 1)).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void consulta8 () {
            try {
                System.out.println("Consulta 8");
                System.out.println("Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas y su edad es mayor de 30 años ordenados por edad y solo mostrar los 3 primeros");
                //Consulta 8
                //Jugadores que juegan en el Real Madrid y son delanteros o son centrocampistas y su edad es mayor de 30 años ordenados por edad y solo mostrar los 3 primeros
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")), gt("Edad", 30))).sort(new Document("Edad", 1)).limit(3).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON con todos los jugadores que juegan en el real y son delanteros o son centrocampistas y su edad es mayor de 30 años ordenados por edad y solo mostrar los 3 primeros y guardarlo en la carpeta resources/json
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridDelanterosCentrocampistasMayores30OrdenadosEdad3Primeros.json")));
                database.getCollection("Jugadores").find(and(eq("Equipo", "Real Madrid"), or(eq("Posicion", "Delantero"), eq("Posicion", "Centrocampista")), gt("Edad", 30))).sort(new Document("Edad", 1)).limit(3).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void menuUpdates () {
            int opcion = 0;
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;
            while (!salir) {
                System.out.println("Menú de actualizaciones");
                System.out.println("1. Update 1");
                System.out.println("2. Update 2");
                System.out.println("3. Update 3");
                System.out.println("4. Update 4");
                System.out.println("5. Update 5");
                System.out.println("6. Update 6");
                System.out.println("0. Salir");
                System.out.println("Elige una opción");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        update1();
                        break;
                    case 2:
                        update2();
                        break;
                    case 3:
                        update3();
                        break;
                    case 4:
                        update4();
                        break;
                    case 5:
                        update5();
                        break;
                    case 6:
                        update6();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 8");
                }
            }
        }

        private void update1 () {
            try {
                System.out.println("Update 1");
                System.out.println("Actualizar la posicion de Cristiano Ronaldo a Portero");
                //Update 1
                //Actualizar la posicion de Cristiano Ronaldo a Portero
                database.getCollection("Jugadores").updateMany(eq("Nombre", "Cristiano Ronaldo"), new Document("$set", new Document("Posicion", "Portero")));
                database.getCollection("Jugadores").find(eq("Nombre", "Cristiano Ronaldo")).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresCristianoRonaldoPortero.json")));
                database.getCollection("Jugadores").find(eq("Nombre", "Cristiano Ronaldo")).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void update2 () {
            try {
                System.out.println("Update 2");
                System.out.println("Actualizar a Cristiano Ronaldo como jugador del Barcelona");
                //Update 2
                //Actualizar a Cristiano Ronaldo como jugador del Barcelona
                database.getCollection("Jugadores").updateMany(eq("Nombre", "Cristiano Ronaldo"), new Document("$set", new Document("Equipo", "Barcelona")));
                database.getCollection("Jugadores").find(eq("Nombre", "Cristiano Ronaldo")).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresCristianoRonaldoBarcelona.json")));
                database.getCollection("Jugadores").find(eq("Nombre", "Cristiano Ronaldo")).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private void update3 () {
            try {
                System.out.println("Update 3");
                System.out.println("Actualizar a Luka Modric a edad de 20 años");
                //Update 3
                //Actualizar a Luka Modric a edad de 20 años
                database.getCollection("Jugadores").updateMany(eq("Nombre", "Luka Modric"), new Document("$set", new Document("Edad", 20)));
                database.getCollection("Jugadores").find(eq("Nombre", "Luka Modric")).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresLukaModricEdad20.json")));
                database.getCollection("Jugadores").find(eq("Nombre", "Luka Modric")).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private void update4 () {
            try {
                System.out.println("Update 4");
                System.out.println("Actualizar a Toni Kroos el nombre por el de Antonio");
                //Update 4
                //Actualizar a Toni Kroos el nombre por el de Antonio
                database.getCollection("Jugadores").updateMany(eq("Nombre", "Toni Kroos"), new Document("$set", new Document("Nombre", "Antonio")));
                database.getCollection("Jugadores").find(eq("Nombre", "Antonio")).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresToniKroosNombreAntonio.json")));
                database.getCollection("Jugadores").find(eq("Nombre", "Antonio")).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void update5 () {
            try {
                System.out.println("Update 5");
                System.out.println("Actualizar a todos los jugadores del Real Madrid a edad de 20 años");
                //Update 5
                //Actualizar a todos los jugadores del Real Madrid a edad de 20 años
                database.getCollection("Jugadores").updateMany(eq("Equipo", "Real Madrid"), new Document("$set", new Document("Edad", 20)));
                database.getCollection("Jugadores").find(eq("Equipo", "Real Madrid")).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridEdad20.json")));
                database.getCollection("Jugadores").find(eq("Equipo", "Real Madrid")).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void update6 () {
            try {
                System.out.println("Update 6");
                System.out.println("Actualizar a todos los jugadores del Real Madrid a edad de 25 años y a la posicion de portero");
                //Update 6
                //Actualizar a todos los jugadores del Real Madrid a edad de 20 años y a la posicion de portero
                database.getCollection("Jugadores").updateMany(eq("Equipo", "Real Madrid"), new Document("$set", new Document("Edad", 25).append("Posicion", "Portero")));
                database.getCollection("Jugadores").find(eq("Equipo", "Real Madrid")).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridEdad25PosicionPortero.json")));
                database.getCollection("Jugadores").find(eq("Equipo", "Real Madrid")).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void menuAgrupaciones () {
            int opcion = 0;
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;
            while (!salir) {
                System.out.println("Menú de actualizaciones");
                System.out.println("1. Agrupacion 1");
                System.out.println("2. Agrupacion 2");
                System.out.println("3. Agrupacion 3");
                System.out.println("0. Salir");
                System.out.println("Elige una opción");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        agrupacion1();
                        break;
                    case 2:
                        agrupacion2();
                        break;
                    case 3:
                        agrupacion3();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 3");
                }
            }
        }

        private void agrupacion1 () {
            try {
                System.out.println("Agrupacion 1");
                System.out.println("Mostrar los jugadores del Real Madrid");
                //Agrupacion 1
                //Mostrar los jugadores del Real Madrid
                database.getCollection("Jugadores").aggregate(Arrays.asList(
                        match(eq("Equipo", "Real Madrid")),
                        group("$Nombre")
                )).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadrid.json")));
                database.getCollection("Jugadores").aggregate(Arrays.asList(
                        match(eq("Equipo", "Real Madrid")),
                        group("$Nombre")
                )).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void agrupacion2 () {
            try {
                System.out.println("Agrupacion 2");
                System.out.println("Mostrar los jugadores del Real Madrid y Barcelona");
                //Agrupacion 2
                //Mostrar los jugadores del Real Madrid y Barcelona
                database.getCollection("Jugadores").aggregate(Arrays.asList(
                        match(or(eq("Equipo", "Real Madrid"), eq("Equipo", "Barcelona"))),
                        group("$Nombre")
                )).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridBarcelona.json")));
                database.getCollection("Jugadores").aggregate(Arrays.asList(
                        match(or(eq("Equipo", "Real Madrid"), eq("Equipo", "Barcelona"))),
                        group("$Nombre")
                )).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void agrupacion3 () {
            try {
                System.out.println("Agrupacion 3");
                System.out.println("Mostrar los jugadores del Real Madrid y Barcelona y su edad");
                //Agrupacion 3
                //Mostrar los jugadores del Real Madrid y Barcelona y su edad
                database.getCollection("Jugadores").aggregate(Arrays.asList(
                        match(or(eq("Equipo", "Real Madrid"), eq("Equipo", "Barcelona"))),
                        group("$Nombre", sum("Edad", "$Edad"))
                )).forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
                //Generar archivo .JSON
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/json/jugadoresRealMadridBarcelonaEdad.json")));
                database.getCollection("Jugadores").aggregate(Arrays.asList(
                        match(or(eq("Equipo", "Real Madrid"), eq("Equipo", "Barcelona"))),
                        group("$Nombre", sum("Edad", "$Edad"))
                )).forEach((Block<? super Document>) document -> {
                    try {
                        bw.write(document.toJson());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }