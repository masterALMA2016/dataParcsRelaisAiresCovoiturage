package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Created by Maxime on 08/12/14.
 */
public class SPARQLRequest7 extends SPARQLRequest {

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
                        "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"+
                        "PREFIX dbpprop: <http://dbpedia.org/property/>" +
                        "PREFIX bif: <bif:>"+
                        "SELECT ?populationTown ?townName ?parcRelaisName " +
                        "WHERE {" +
                        "      ?parcRelais rdf:type pdll:parcsRelais ."+
                        "      ?parcRelais foaf:name ?parcRelaisName . "+
                        "      ?parcRelais dbpprop:town ?townNameRelais ."+
                        "      SERVICE <http://dbpedia.org/sparql> {" +
                        "            ?town rdf:type dbpedia-owl:Settlement . " +
                        "            ?town dbpprop:population ?populationTown . " +
                        "            ?town dbpprop:name ?townName ." +
                        "            FILTER (STR(?townNameRelais) = STR(?townName) )  "+
                        "       } "+
                        "}";



        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        System.out.println("Liste des parcs relais par commune avec leur nombre d'habitants");
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }
}
