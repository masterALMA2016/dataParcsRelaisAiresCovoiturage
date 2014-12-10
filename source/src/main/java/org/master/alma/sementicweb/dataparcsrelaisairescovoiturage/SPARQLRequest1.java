package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import java.io.InputStream;

/**
 * Created by Maxime on 08/12/14.
 */
public class SPARQLRequest1 extends SPARQLRequest {

    static final String inputFileName = "airesDeCovoiturage.rdf";

    @Override
    void execute() {
        Model model = getModel(DataSet.PARCS_RELAIS);

        String queryString =
                "PREFIX dbpprop: <http://dbpedia.org/property/>" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX pdll: <http://lodpaddle.univ-nantes.fr/>" +
                "SELECT ?name " +
                "WHERE {" +
                "      ?parcRelais rdf:type pdll:parcsRelais . " +
                "      ?parcRelais dbpprop:town \"Saint-Herblain\"^^<http://www.w3.org/2001/XMLSchema#string> ."+
                "      ?parcRelais foaf:name ?name" +
                "}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        System.out.println("Liste des parcs relais situés à St Herblain");
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }
}
