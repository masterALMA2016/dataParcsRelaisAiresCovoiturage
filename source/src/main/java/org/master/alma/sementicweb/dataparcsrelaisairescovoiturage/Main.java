package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

/**
 * Created by Maxime on 08/12/14.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Etape 1 : requêtes sur nos jeux de données");
        SPARQLRequest r1 = new SPARQLRequest1();
        r1.execute();
        SPARQLRequest r2 = new SPARQLRequest2();
        r2.execute();
        System.out.println("Etape 2 : requêtes sur nos jeux de données liés à d'autres");
        SPARQLRequest r3 = new SPARQLRequest3();
        r3.execute();
        SPARQLRequest r4 = new SPARQLRequest4();
        r4.execute();
        System.out.println("Etape 3 : ajout des ontologies RDFS");
        SPARQLRequest r5 = new SPARQLRequest5();
        r5.execute();
        SPARQLRequest r6 = new SPARQLRequest6();
        r6.execute();
        System.out.println("Etape 4 : requêtes avec services (dbpedia)");
        SPARQLRequest r7 = new SPARQLRequest7();
        r7.execute();
    }

}
