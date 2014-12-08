package org.master.alma.sementicweb.dataparcsrelaisairescovoiturage;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import java.io.InputStream;

/**
 * Created by Maxime on 08/12/14.
 */
public abstract class SPARQLRequest {

    protected Model getModel(String name) {
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(name);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + name + " not found");
        }
        model.read( in, "" );
        return model;
    }

    abstract void execute();
}
