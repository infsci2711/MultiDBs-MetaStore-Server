package metaStoreServerApi;

import edu.pitt.sis.infsci2711.multidbs.utils.JerseyJettyServer;

public class server {
	
	public static void main(final String[] args) throws Exception {
		JerseyJettyServer server = new JerseyJettyServer(7654, "metaStoreRestService");
		server.start();
	}
}
