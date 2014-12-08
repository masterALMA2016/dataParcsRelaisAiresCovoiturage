package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Created by Maxime on 08/12/14.
 */
public class SPARQLRequest6 extends SPARQLRequest {

    @Override
    void execute() {
        Model mobilite = getModel(DataSet.MOBILITE);
        Model covoiturage = getModel(DataSet.AIRES_DE_COVOITURAGE);
        Model parcs = getModel(DataSet.PARCS_RELAIS);

        Model m =  ModelFactory.createDefaultModel();
        m.add(covoiturage);
        m.add(mobilite);
        m.add(parcs);

        String queryString =
                "PREFIX dbpprop: <http://dbpedia.org/property/>" +
                        "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                        "PREFIX pdll: <http://lodpaddle.univ-nantes.fr/>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                        "SELECT ?name ?carCapacity " +
                        "WHERE {" +
                        "      ?parcRelaisType rdfs:subClassOf pdll:mobilite . " +
                        "      ?parcRelais rdf:type ?parcRelaisType . " +
                        "      ?parcRelais pdll:carCapacity ?carCapacity ."+
                        "      ?parcRelais foaf:name ?name" +
                        "}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        System.out.println("Liste des parcs relais et leur capacité (à partir des objets de type mobilité)");
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }
}
