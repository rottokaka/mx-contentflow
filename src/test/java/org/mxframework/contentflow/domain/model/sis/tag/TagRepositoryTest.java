package org.mxframework.contentflow.domain.model.sis.tag;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void nextIdentity() {
        TagId tagId = tagRepository.nextIdentity();
        assertTrue(tagId.id().startsWith("SIS-TAG-"));
    }
}