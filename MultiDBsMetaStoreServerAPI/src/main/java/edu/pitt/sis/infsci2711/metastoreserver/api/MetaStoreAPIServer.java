package edu.pitt.sis.infsci2711.metastoreserver.api;

import edu.pitt.sis.infsci2711.multidbs.utils.JerseyJettyServer;

public class MetaStoreAPIServer {
	
	public static void main(final String[] args) throws Exception {
		JerseyJettyServer server = new JerseyJettyServer(7654, "edu.pitt.sis.infsci2711.metastoreserver.rest");
		server.start();
	}
}
