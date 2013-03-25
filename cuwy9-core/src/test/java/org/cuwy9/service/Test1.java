package org.cuwy9.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.domain.Node;
import org.cuwy9.service.Cuwy7Service;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
	protected final static Log log = LogFactory.getLog(Test1.class);
public static void main(String[] args) {
	System.out.println(1);
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"classpath:/META-INF/spring/applicationContext*.xml");
	Cuwy7Service cuwy7service = (Cuwy7Service) context.getBean("cuwy7Service");
//	folder(cuwy7service);
//	drug(cuwy7service);
//	regime(cuwy7service);
	DateTime birthdate = new DateTime();
	birthdate = birthdate.minusYears(25);
	cuwy7service.fiPatient("fn1", "pn1", "F", birthdate);
	System.out.println(2);
}
private static void folder(Cuwy7Service cuwy7service) {
	Node fiFolder = cuwy7service.fiFolder("folder");
	log.debug(fiFolder+"--"+fiFolder.getFolder());
}
private static void drug(Cuwy7Service cuwy7service) {
	Node fiDrug = cuwy7service.fiDrug("superPille1");
	log.debug(fiDrug+"--"+fiDrug.getDrug());
}
private static void regime(Cuwy7Service cuwy7service) {
	Node folderN = cuwy7service.fiFolder("regime");
	log.debug(folderN+"--"+folderN.getFolder());
	Node fiApp = cuwy7service.fiApp(1, "");
	log.debug(fiApp+"--"+fiApp.getApp());
	Node taskN = cuwy7service.makeTestRegime("testRegime1");
	log.debug(taskN+"--"+taskN.getTask());
}
}
