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
public class SPARQLInsertData extends SPARQLRequest {

    @Override
    void execute() {
        Model mobilite = getModel(DataSet.MOBILITE);
        Model covoiturage = getModel(DataSet.AIRES_DE_COVOITURAGE);

        Model m =  ModelFactory.createDefaultModel();
        m.add(covoiturage);
        m.add(mobilite);

        String queryString =
                "PREFIX pdll: <http://lodpaddle.univ-nantes.fr/>" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
                "INSERT DATA { " +
                "      ?mobilite owl:sameAs ?aireDeCovoiturage . " +
                "      ?aireDeCovoiturage owl:sameAs ?mobilite " +
                " } " +
                "WHERE { " +
                "      ?aireDeCovoiturage rdf:type pdll:airesDeCovoiturage . " +
                "      ?aireDeCovoiturage pdll:id ?idAireDeCovoiturage . " +
                "      ?mobilite rdf:type pdll:mobilite . " +
                "      ?mobilite pdll:id ?idMobilite . " +
                "      FILTER (?idMobilite = ?idAireDeCovoiturage) " +
                " } ";

        String t = "INSERT {" +
                "      ?aireDeCovoiturage ?carCapacity ";

        UpdateRequest query = UpdateFactory.create(queryString);
        UpdateProcessor processor = UpdateExecutionFactory.create(query, GraphStoreFactory.create(m));
        processor.execute();
        System.out.println("Liste parcs relais pouvant accueillir plus de 100 voitures");
        try {
            m.write(new FileOutputStream("example.owl"), "RDF/XML");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
