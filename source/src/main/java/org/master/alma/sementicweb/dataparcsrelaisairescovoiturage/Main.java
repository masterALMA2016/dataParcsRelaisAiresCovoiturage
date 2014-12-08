package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

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
        SPARQLRequest r3 = new SPARQLRequest1();
        r3.execute();
        SPARQLRequest r4 = new SPARQLRequest2();
        r4.execute();

    }

}
