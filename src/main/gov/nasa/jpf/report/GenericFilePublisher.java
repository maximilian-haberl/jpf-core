/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.nasa.jpf.report;

import gov.nasa.jpf.Config;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mxl
 */
public class GenericFilePublisher extends Publisher {

    String filename;

    public GenericFilePublisher(Config conf, Reporter reporter) {
        super(conf, reporter);

        filename = getReportFileName("report.file.file");
    }

    @Override
    public String getName() {
        return "GenericFilePublisher";
    }

    @Override
    protected void closeChannel() {
        out.close();
    }

    @Override
    protected void openChannel() {
        try {
            out = new PrintWriter(filename);
        } catch (FileNotFoundException ex) {
            StringBuilder message = new StringBuilder("Cannot open file '");
            message.append(filename).append("'\n").append("Continue output on console");
            Logger.getLogger(GenericFilePublisher.class.getName()).log(Level.SEVERE, message.toString(), ex);
            out = new PrintWriter(System.out);
        }
    }

    private void printImports() {
        out.println("import org.junit.jupiter.api.Test;");
        out.println("org.junit.jupiter.api.Assertions.*;");
    }

}
