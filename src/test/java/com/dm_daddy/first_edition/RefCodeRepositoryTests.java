package com.dm_daddy.first_edition;

import com.dm_daddy.first_edition.FirstEditionApplication;
import com.dm_daddy.first_edition.Model.*;
import com.dm_daddy.first_edition.Repositories.ItemRepository;
import com.dm_daddy.first_edition.Repositories.RefCodeRepository;
import com.dm_daddy.first_edition.Repositories.SkillBonusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {FirstEditionTestsConfiguration.class})
@DataJpaTest
public class RefCodeRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RefCodeRepository refRepo;

    @Test()
    public void add_save_ref_code() {
        RefCode r1 = new RefCode();
        r1.setName("Pretend Parent #1");
        r1 = entityManager.persistAndFlush(r1);

        Optional<RefCode> foundRefCode = refRepo.findById(r1.getId());

        assertThat(foundRefCode.isPresent()).isEqualTo(true);

    }

    @Test()
    public void givenParentId_whenRetrieving_thenReturnAllChildRefCodes() {
        RefCode r1 = new RefCode();
        r1.setName("Pretend Parent #1");
        r1 = entityManager.persistAndFlush(r1);

        RefCode r2 = new RefCode();
        r2.setName("Child #1");
        r2.setParentId(r1.getId());
        r2 = entityManager.persistAndFlush(r2);

        RefCode r3 = new RefCode();
        r3.setName("Child #2");
        r3.setParentId(r1.getId());
        r3 = entityManager.persistAndFlush(r3);

        RefCode r4 = new RefCode();
        r4.setName("Parent #2");
        r4 = entityManager.persistAndFlush(r4);

        List<RefCode> parent1Children = refRepo.findByParentId(1L);

        assertThat(parent1Children).isNotNull();
        assertThat(parent1Children.size()).isEqualTo(2);
        assertThat(parent1Children.get(0).getName()).isEqualToIgnoringCase("Child #1");
        assertThat(parent1Children.get(1).getName()).isEqualToIgnoringCase("Child #2");

        List<RefCode> parent2Children = refRepo.findByParentId(4L);

        assertThat(parent2Children.size()).isEqualTo(0);

        List<RefCode> allRefCodes = (List<RefCode>) refRepo.findAll();
        assertThat(allRefCodes.get(3).getName()).isEqualToIgnoringCase("Parent #2");
    }
}
