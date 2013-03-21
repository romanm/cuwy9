package org.cuwy9.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.domain.App;
import org.cuwy9.domain.Dose;
import org.cuwy9.domain.Drug;
import org.cuwy9.domain.Folder;
import org.cuwy9.domain.Node;
import org.cuwy9.domain.Task;
import org.cuwy9.domain.TaskDrug;
import org.cuwy9.reference.DoseProType;
import org.cuwy9.reference.DoseType;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Configurable
public class Cuwy7Service {
	protected final Log log = LogFactory.getLog(getClass());
	public static String genericName = "superPill1";
	public EntityManager getEm() {return em;}
	private EntityManager em;
	@PersistenceContext
	public void setEntityManager(EntityManager em) {this.em = em;}
	// --------regime--------------------
	@Transactional
	protected Node makeTestRegime(String regimeName) {
		Node folderN = fiFolder("regime");
		Node taskN = fiRegime("testRegime1");
		List<Node> childs = taskN.getChilds();
		log.debug(taskN+"--"+taskN.getTask()+" childs="+childs);
		if(null==childs||childs.size()==0){
			Drug drug = fiDrug(Cuwy7Service.genericName ).getDrug();
			Dose dose = fiDose(1, "mg", DoseProType.Once, DoseType.Definition).getDose();
			App app = fiApp(1, "").getApp();
			log.debug("app = "+app);
			Node taskDrugN = persistNode();
			taskDrugN.setDad(taskN);
			TaskDrug taskDrug = new TaskDrug();
			taskDrug.setId(taskDrugN.getId());
			taskDrug.setDrug(drug);
			taskDrug.setDose(dose);
			taskDrug.setApp(app);
			em.persist(taskDrug);
		}else{
			log.debug(childs.size());
		}
		return taskN;
	}
	@Transactional
	protected Node fiRegime(String regimeName) {
		log.debug("fiRegime::drugName = "+regimeName);
		Task task = findRegime(regimeName);
		log.debug("fiRegime::task = "+task);
		Node drugN;
		if(null==task)
			drugN=insertRegime(regimeName);
		else
			drugN=task.getNode();
		return drugN;
	}
	private Task findRegime(String regimeName) {
		log.debug("regimeName = "+regimeName);
		Query q = em.createQuery("SELECT o FROM Task o WHERE o.taskName=:taskName");
		q.setParameter("taskName", regimeName);
		List<Task> resultList = q.getResultList();
		log.debug("resultList = "+resultList);
		return resultList.size()>0?resultList.get(0):null;
	}
	private Node insertRegime(String regimeName){
		Node node = persistNode();
		Task task = new Task();
		task.setNode(node);
		task.setId(node.getId());
		task.setTaskName(regimeName);
		em.persist(task);
		log.debug("task = "+task+" node = "+node);
		return node;
	}
	// --------drug--------------------
	@Transactional
	protected Node fiDrug(String drugName) {
		log.debug("fiDrug::drugName = "+drugName);
		Drug drug = findDrug(drugName);
		System.out.println("fiDrug::drug = "+drug);
		Node drugN;
		if(null==drug)
			drugN=insertDrug(drugName);
		else
			drugN=drug.getNode();
		return drugN;
	}
	protected Drug findDrug(String drugName) {
		log.debug("drugName = "+drugName);
		Query q = em.createQuery("SELECT o FROM Drug o WHERE o.drugname=:drugname");
		q.setParameter("drugname", drugName);
		List<Drug> resultList = q.getResultList();
		log.debug("resultList = "+resultList);
		return resultList.size()>0?resultList.get(0):null;
	}
	private Node insertDrug(String drugName){
		Node node = persistNode();
		Drug drug = new Drug();
		drug.setId(node.getId());
		drug.setDrugname(drugName);
		em.persist(drug);
		System.out.println("insertDrug = "+drug);
		System.out.println("node = "+node);
		System.out.println("node = "+node.getDrug());
		return node;
	}
	// --------dose------------------
	@Transactional
	protected Node fiApp(Integer msinfusion, String unit) {
		App app = findApp(msinfusion,unit);
		log.debug(app);
		log.debug(app.getNode());
		Node appN;
		if(null==app)
			appN=insertApp(msinfusion,unit);
		else 
			appN=app.getNode();
		return appN;
	}
	private Node insertApp(Integer msinfusion, String unit) {
		Node node = persistNode();
		App app = new App();
		app.setId(node.getId());
		app.setNode(node);
		app.setMsinfusion(msinfusion);
		app.setUnit(unit);
		log.debug(app);
		em.persist(app);
		log.debug(app);
		return node;
	}
	protected App findApp(Integer msinfusion, String unit) {
		Query q = em.createQuery(" SELECT o FROM App o " 
				+" WHERE o.msinfusion=:msinfusion "
				+" AND o.unit=:unit "
				);
		q.setParameter("msinfusion", msinfusion);
		q.setParameter("unit", unit);
		List<App> resultList = q.getResultList();
		return resultList.size()>0?resultList.get(0):null;
	}
	// --------dose------------------
	@Transactional
	protected Node fiDose(double dosevalue, String doseunit, DoseProType doseProType, DoseType doseType ) {
		Dose dose = findDose(dosevalue, doseunit, doseProType, doseType);
		Node doseN;
		if(null==dose)
			doseN=insertDose(dosevalue, doseunit, doseProType, doseType);
		else 
			doseN=dose.getNode();
		return doseN;
	}
	private Node insertDose(double dosevalue, String doseunit, DoseProType doseProType, DoseType doseType) {
		Node node = persistNode();
		Dose dose = new Dose();
		dose.setId(node.getId());
		dose.setDosevalue(dosevalue);
		dose.setDoseunit(doseunit);
		dose.setDoseType(doseType);
		dose.setDoseProType(doseProType);
		em.persist(dose);
		return node;
	}
	protected Dose findDose(double dosevalue, String doseunit, DoseProType doseProType, DoseType doseType ) {
		Query q = em.createQuery(" SELECT o FROM Dose o " 
				+" WHERE o.dosevalue=:dosevalue "
				+" AND o.doseunit=:doseunit "
				+" AND o.doseProType=:doseProType "
				+" AND o.doseType=:doseType "
				);
		q.setParameter("dosevalue", dosevalue);
		q.setParameter("doseunit", doseunit);
		q.setParameter("doseProType", doseProType);
		q.setParameter("doseType", doseType);
		List<Dose> resultList = q.getResultList();
		return resultList.size()>0?resultList.get(0):null;
	}
	// --------folder------------------
	@Transactional
	public Node fiFolder(String folderName) {
		Node folderN;
		Folder folder = Folder.find(folderName);
		if(null==folder)
			folderN = insertFolder(folderName);
		else
			folderN=folder.getNode();
		return folderN;
	}
	private Node insertFolder( String folderName) {
		Node node = persistNode();
		insertFolder(folderName, node);
		return node;
	}
	private void insertFolder(String folderName, Node node) {
		Folder folder = new Folder();
		folder.setId(node.getId());
		folder.setNode(node);
		folder.setFoldername(folderName);
		em.persist(folder);
	}
	private Node persistNode() {
		Node node = new Node();
		em.persist(node);
		node.setLink(node);
		return node;
	}
	// ----------------educational sector---------------------
	public Node fiFolder3(String folderName) {
		Node folderN;
		Folder folder = Folder.find( folderName);
		log.debug(folder);
		if(null==folder) {
			folderN = insertFolder3(folderName);
		}else{
			folderN=folder.getNode();
		}
		return folderN;
	}
	
	private Node insertFolder3( String folderName) {
		Node node = Node.persistNode();
		System.out.println(node);
		Folder folder = new Folder();
		folder.setId(node.getId());
		folder.setFoldername(folderName);
		System.out.println(folder);
		folder.persist();
		return node;
	}
	
}
