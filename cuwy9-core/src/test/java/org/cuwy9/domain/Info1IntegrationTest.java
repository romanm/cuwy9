package org.cuwy9.domain;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
//@Configurable;
public class Info1IntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
	 
    @Autowired
    Info1DataOnDemand dod;
    
    @Test
    public void testCountInfo1s() {
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", dod.getRandomInfo1());
        long count = Info1.countInfo1s();
        Assert.assertTrue("Counter for 'Info1' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void testFindInfo1() {
        Info1 obj = dod.getRandomInfo1();
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Info1' failed to provide an identifier", id);
        obj = Info1.findInfo1(id);
        Assert.assertNotNull("Find method for 'Info1' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Info1' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void testFindAllInfo1s() {
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", dod.getRandomInfo1());
        long count = Info1.countInfo1s();
        Assert.assertTrue("Too expensive to perform a find all test for 'Info1', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Info1> result = Info1.findAllInfo1s();
        Assert.assertNotNull("Find all method for 'Info1' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Info1' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void testFindInfo1Entries() {
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", dod.getRandomInfo1());
        long count = Info1.countInfo1s();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Info1> result = Info1.findInfo1Entries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Info1' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Info1' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void testFlush() {
        Info1 obj = dod.getRandomInfo1();
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Info1' failed to provide an identifier", id);
        obj = Info1.findInfo1(id);
        Assert.assertNotNull("Find method for 'Info1' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyInfo1(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Info1' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void testMergeUpdate() {
        Info1 obj = dod.getRandomInfo1();
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Info1' failed to provide an identifier", id);
        obj = Info1.findInfo1(id);
        boolean modified =  dod.modifyInfo1(obj);
        Integer currentVersion = obj.getVersion();
        Info1 merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Info1' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", dod.getRandomInfo1());
        Info1 obj = dod.getNewTransientInfo1(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Info1' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Info1' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Info1' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void testRemove() {
        Info1 obj = dod.getRandomInfo1();
        Assert.assertNotNull("Data on demand for 'Info1' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Info1' failed to provide an identifier", id);
        obj = Info1.findInfo1(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Info1' with identifier '" + id + "'", Info1.findInfo1(id));
    }
    

}
