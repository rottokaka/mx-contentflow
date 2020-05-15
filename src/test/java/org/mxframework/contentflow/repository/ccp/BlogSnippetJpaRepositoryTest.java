package org.mxframework.contentflow.repository.ccp;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlogSnippetJpaRepositoryTest {

    @Autowired
    private BlogSnippetJpaRepository blogSnippetJpaRepository;

    @Test
    public void testAofSave() {

    }
}
