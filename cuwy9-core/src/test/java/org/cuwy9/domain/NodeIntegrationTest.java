package org.cuwy9.domain;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.service.Cuwy9Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class NodeIntegrationTest {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	NodeDataOnDemand dod;
	@Autowired
	Cuwy9Service cuwy7Service;
	
	@Test
	public void persistFolder() {
		log.debug(1);
		Node fiFolder = cuwy7Service.fiFolder("folder");
		log.debug(fiFolder+"  "+fiFolder.getFolder());
		List<Folder> findAllFolder = Folder.findAllFolder();
		log.debug(findAllFolder);
		log.debug(2);
		List<Task> findAllTask = Task.findAllTask();
		for (Task task : findAllTask) {
			log.debug(task);
			Node node = task.getNode();
			log.debug(node);
			for (Node node2 : node.getChilds()) {
				TaskDrug taskDrug = node2.getTaskDrug();
				log.debug(node2+"   "+taskDrug);
				if(null!=taskDrug)
				{
					log.debug(taskDrug.getDrug());
					log.debug(taskDrug.getDose());
					log.debug(taskDrug.getApp());
				}
			}
		}
		log.debug(3);
	}

//	@Test
	public void testCountInfo2s() {
		Node flushNode = dod.persistNode();
		log.debug(flushNode);
		Node persistNode = dod.persistNode();
		log.debug(persistNode);
		log.debug(dod.getRandomNode());
		Assert.assertNotNull("Data on demand for 'Node' failed to initialize correctly", dod.getRandomNode());
		long count = Node.countNodes();
		Assert.assertTrue("Counter for 'Node' incorrectly reported there were no entries "+count, count > 0);
	}
	// --------------education sector----------------------
//	@Test
	public void persistFolder3() {
		Node folderT = cuwy7Service.fiFolder3("folder");
		log.debug(folderT);
		log.debug(folderT.getFolder());
		Long id = folderT.getId();
		log.debug(id);
		Node findNode = Node.findNode(id);
		log.debug(findNode);
		log.debug(findNode.getFolder());
		
	}
}
