package util;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Args {

    public static void init(Object obj, String[] args) {
        CmdLineParser parser = new CmdLineParser(obj);
        try {
            parser.parseArgument(args);
        } catch( CmdLineException e ) {
            System.err.println(e.getMessage());
            return;
        }
    }
}