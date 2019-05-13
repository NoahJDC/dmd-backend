package com.dm_daddy.first_edition;

import com.dm_daddy.first_edition.Model.RefCode;
import com.dm_daddy.first_edition.Repositories.RefCodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstEditionApplicationTests {

    @Autowired
    private RefCodeRepository refRepo;

//    @Test
//    public void contextLoads() {
//        List<RefCode> rc = (List<RefCode>) refRepo.findAll();
//        assertThat(rc).isEmpty();
//    }

}
