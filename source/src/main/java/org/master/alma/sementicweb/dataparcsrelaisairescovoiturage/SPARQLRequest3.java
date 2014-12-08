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
                "SELECT ?nameAireDeCovoiturage ?nameLieuMobilite "+
                "WHERE { " +
                "      ?aireDeCovoiturage rdf:type pdll:airesDeCovoiturage . " +
                "      ?aireDeCovoiturage dbpprop:town ?townC ."+
                "      ?aireDeCovoiturage foaf:name ?nameAireDeCovoiturage ." +
                "      ?aireDeCovoiturage pdll:carCapacity ?carCapacity . "+
                "      ?mobilite rdf:type pdll:mobilite . " +
                "      ?mobilite foaf:name ?nameLieuMobilite ." +
                "      ?mobilite pdll:CategoryLabel ?labelM . " +
                "      ?mobilite dbpprop:town ?townM ."+
                "      FILTER (?labelM != \"Aire de covoiturage\"^^<http://www.w3.org/2001/XMLSchema#string> &&" +
                "      ?townC = ?townM && xsd:decimal(?carCapacity) > 100 ) " +
                " } ";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        System.out.println("Liste des équipements de mobilité dans la même ville qu'une aire de covoiturage ayant plus de 100 places");
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }
}
