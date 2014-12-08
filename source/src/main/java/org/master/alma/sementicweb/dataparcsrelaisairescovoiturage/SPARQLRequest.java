package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import java.io.InputStream;

/**
 * Created by Maxime on 08/12/14.
 */
public abstract class SPARQLRequest {

    public enum DataSet {
        AIRES_DE_COVOITURAGE("airesDeCovoiturage.rdf"), PARCS_RELAIS("parcsRelais.rdf"), MOBILITE("mobilite.rdf");


        private String rdfFile;
        private DataSet(String rdfFile) {
            this.rdfFile = rdfFile;
        }

        public String getRdfFile() {
            return rdfFile;
        }

    }

    protected Model getModel(DataSet dataset) {
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(dataset.getRdfFile());
        if (in == null) {
            throw new IllegalArgumentException( "File: " + dataset.getRdfFile() + " not found");
        }
        model.read( in, "" );
        return model;
    }

    abstract void execute();
}
