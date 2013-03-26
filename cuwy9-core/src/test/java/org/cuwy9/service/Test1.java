package org.cuwy9.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.domain.App;
import org.cuwy9.domain.Dose;
import org.cuwy9.domain.Drug;
import org.cuwy9.domain.Node;
import org.cuwy9.reference.DoseProType;
import org.cuwy9.reference.DoseType;
import org.cuwy9.service.Cuwy9Service;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
	protected final static Log log = LogFactory.getLog(Test1.class);
public static void main(String[] args) {
	System.out.println(1);
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"classpath:/META-INF/spring/applicationContext*.xml");
	Cuwy9Service cuwy9service = (Cuwy9Service) context.getBean("cuwy9Service");
//	folder(cuwy9service);
//	drug("superDrops2", cuwy9service);
//	drug(Cuwy9Service.genericName, cuwy9service);
//	taskDrug(cuwy9service);
	taskDrug2(cuwy9service);
//	regime(cuwy9service);
//	patient(cuwy9service);
	System.out.println(2);
}
private static void taskDrug2(Cuwy9Service cuwy9service) {
	Node drug = drug("superDrops2", cuwy9service);
	Node dose = cuwy9service.fiDose(2, "mg", DoseProType.Once, DoseType.Definition);
	Node app = cuwy9service.fiApp(1, "po");
	Node fiTaskdrug = cuwy9service.fiTaskDrug(drug.getDrug(), dose.getDose(), app.getApp());
}
private static void taskDrug(Cuwy9Service cuwy9service) {
	Node fiDrug = cuwy9service.fiDrug(Cuwy9Service.genericName );
	Drug drug = fiDrug.getDrug();
	log.debug(drug);
	Node fiDose = cuwy9service.fiDose(1, "mg", DoseProType.Once, DoseType.Definition);
	Dose dose = fiDose.getDose();
	log.debug(dose);
	Node fiApp = cuwy9service.fiApp(1, "");
	App app = fiApp.getApp();
	log.debug("app = "+app);
	Node fiTaskdrug = cuwy9service.fiTaskDrug(drug, dose, app);
	log.debug("fiTaskdrug = "+fiTaskdrug);
	log.debug("fiTaskdrug = "+fiTaskdrug.getTaskDrug());
}
private static void patient(Cuwy9Service cuwy9service) {
	DateTime birthdate = new DateTime().minusYears(25);
	cuwy9service.fiPatient("fn1", "pn1", "F", birthdate);
}
private static void folder(Cuwy9Service cuwy9service) {
	Node fiFolder = cuwy9service.fiFolder("folder");
	log.debug(fiFolder+"--"+fiFolder.getFolder());
}
private static Node drug(String genericName ,Cuwy9Service cuwy9service) {
	Node fiDrug = cuwy9service.fiDrug( genericName);
	log.debug(fiDrug+"--"+fiDrug.getDrug());
	return fiDrug;
}
private static void regime(Cuwy9Service cuwy9service) {
	Node folderN = cuwy9service.fiFolder("regime");
	log.debug(folderN+"--"+folderN.getFolder());
	Node fiApp = cuwy9service.fiApp(1, "");
	log.debug(fiApp+"--"+fiApp.getApp());
	Node taskN = cuwy9service.makeTestRegime("testRegime1");
	log.debug(taskN+"--"+taskN.getTask());
}
}
