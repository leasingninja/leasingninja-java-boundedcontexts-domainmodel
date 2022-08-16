/**
 * @author Henning Schwentner
 *
 */
module io.leasingninja.riskmanagement {
	exports io.leasingninja.riskmanagement.application;

	requires org.slf4j;

    requires org.jmolecules.architecture.layered;
    requires org.jmolecules.ddd;
	requires io.hschwentner.dddbits;

	requires spring.beans;
//	requires spring.core;
	requires spring.context;
	requires spring.web;
	requires spring.webmvc;
//	requires spring.boot;
//	requires spring.boot.autoconfigure;
}






//requires org.junit.jupiter.api;
// eine Sache, damit man das auch mit Eclipse nicht braucht: JUnit in Classpath statt in Modulepath.
