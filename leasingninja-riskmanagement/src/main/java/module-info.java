import org.jspecify.annotations.NullMarked;

/**
 * @author Henning Schwentner
 *
 */
@NullMarked
module io.leasingninja.riskmanagement {
	exports io.leasingninja.riskmanagement.application;

	requires org.jspecify;
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
