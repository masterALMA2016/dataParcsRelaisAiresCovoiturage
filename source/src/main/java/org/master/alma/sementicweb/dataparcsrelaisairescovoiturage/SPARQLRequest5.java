package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Created by Maxime on 08/12/14.
 */
public class SPARQLRequest5 extends SPARQLRequest {

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
                "PREFIX pdll: <http://lodpaddle.univ-nantes.fr/>" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                "SELECT ?subClass "+
                "WHERE { " +
                "      ?subClass rdfs:subClassOf pdll:mobilite . " +
                " } ";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        System.out.println("Liste des sous classes de Mobilité");
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }
}
