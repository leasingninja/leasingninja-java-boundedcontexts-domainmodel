/**
 * 
 */
/**
 * @author henning
 *
 */
open module io.leasingninja.webapp {
	exports io.leasingninja.webapp;

//	requires io.hschwentner.dddbits;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.data.commons;
	requires spring.data.jpa;
}