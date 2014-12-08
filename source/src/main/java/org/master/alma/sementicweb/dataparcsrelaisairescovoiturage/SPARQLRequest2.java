package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * Created by Maxime on 08/12/14.
 */
public class SPARQLRequest2 extends SPARQLRequest {

    static final String inputFileName = "parcsRelais.rdf";

    @Override
    void execute() {
        Model model = getModel(inputFileName);

        String queryString =
                "PREFIX pdll: <http://lodpaddle.univ-nantes.fr/>" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "+
                "SELECT ?name ?carCapacity " +
                "WHERE { " +
                "      ?aireDeCovoiturage pdll:carCapacity ?carCapacity . "+
                "      ?aireDeCovoiturage foaf:name ?name . " +
                "      FILTER (xsd:decimal(?carCapacity) > 100) "+
                " } ";

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        System.out.println("Liste parcs relais pouvant accueillir plus de 100 voitures");
        ResultSetFormatter.out(System.out, results, query);
        qe.close();

    }
}
