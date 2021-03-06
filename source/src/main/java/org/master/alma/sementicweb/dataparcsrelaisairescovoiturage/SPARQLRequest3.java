package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.update.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Maxime on 08/12/14.
 */
public class SPARQLRequest3 extends SPARQLRequest {

    @Override
    void execute() {
        Model mobilite = getModel(DataSet.MOBILITE);
        Model covoiturage = getModel(DataSet.AIRES_DE_COVOITURAGE);

        Model m =  ModelFactory.createDefaultModel();
        m.add(covoiturage);
        m.add(mobilite);

        String queryString =
                "PREFIX dbpprop: <http://dbpedia.org/property/>" +
                "PREFIX pdll: <http://lodpaddle.univ-nantes.fr/>" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> "+
                "SELECT ?nameAireDeCovoiturage ?nameLieuMobilite "+
                "WHERE { " +
                "      ?aireDeCovoiturage pdll:id ?idC . " +
                "      ?aireDeCovoiturage rdf:type pdll:airesDeCovoiturage . " +
                "      ?aireDeCovoiturage foaf:name ?nameAireDeCovoiturage ." +
                "      ?aireDeCovoiturage pdll:carCapacity ?carCapacity . "+
                "      ?aireDeCovoiturage geo:long ?long1 . "+
                "      ?aireDeCovoiturage geo:lat ?lat1 . "+
                "      ?mobilite rdf:type pdll:mobilite . " +
                "      ?mobilite foaf:name ?nameLieuMobilite ." +
                "      ?mobilite pdll:id ?idM . " +
                "      ?mobilite geo:long ?long2 . "+
                "      ?mobilite geo:lat ?lat2 . "+
                "      FILTER (?idC != ?idM &&" +
                "      (ABS(?lat1-?lat2)< 0.007) && (ABS(?long1-?long2)<0.007) ) " +
                " } ";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        System.out.println("Liste des équipements de mobilité situé à 1km d'un lieu de covoiturage");
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }
}
